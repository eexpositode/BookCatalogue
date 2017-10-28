package com.eexposito.bookcatalogue;

import com.eexposito.bookcatalogue.headers.CatalogueHeader;
import com.eexposito.bookcatalogue.models.*;
import com.eexposito.bookcatalogue.utils.CatalogueMapper;
import com.eexposito.bookcatalogue.visitors.PrintModelVisitor;
import com.eexposito.bookcatalogue.visitors.Visitor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.eexposito.bookcatalogue.utils.CatalogueException.*;

class CatalogueImporter {

    private static final String PRINT_ALL_PUBLICATIONS = "Print all publications";
    private static final String FIND_PUBLICATION_WITH_ISBN = "Find publication with ISBN %s";
    private static final String PUBLICATION_NOT_FOUND = "No publication with %s found";
    private static final String FIND_PUBLICATIONS_FROM_AUTHOR = "Find all publications from %s,%s";
    private static final String SORT_PUBLICATIONS_BY_TITLE = "Print all publications sorted by title";
    private Collection<Author> mAuthors;
    private Collection<Book> mBooks;
    private Collection<Magazine> mMagazines;

    /////////////////////////////////////////////////////////////////////
    // Accessors
    /////////////////////////////////////////////////////////////////////

    /**
     * Lesen aller Daten aus mehreren CSV-Dateien im Ordner "data".
     * Diese finden Sie im Anhang als ZIP-Archiv. Der Aufbau der Dateien sollte selbsterklärend sein.
     */
    void importCatalogue() {

        mAuthors = importModelsFromDataSource(Author.class, getHeaderClass(Author.class));
        mBooks = importModelsFromDataSource(Book.class, getHeaderClass(Book.class));
        mMagazines = importModelsFromDataSource(Magazine.class, getHeaderClass(Magazine.class));
    }

    /**
     * Alle Bücher / Zeitschriften mit allen Details ausgeben
     */
    void showAllPublications() {

        System.out.println("================================================================================================");
        System.out.println(PRINT_ALL_PUBLICATIONS);
        PrintModelVisitor printVisitor = new PrintModelVisitor();
        visitPublications(mBooks, printVisitor);
        visitPublications(mMagazines, printVisitor);

        System.out.println(printVisitor.getPublications());
        System.out.println("================================================================================================");
    }

    /**
     * Anhand einer ISBN-Nummer ein Buch / eine Zeitschrift finden und ausgeben
     */
    void findPublicationAfterISBN() {

        System.out.println("================================================================================================");
        List<Publication> publications = Stream.concat(mBooks.stream(), mMagazines.stream()).collect(Collectors.toList());

        String randomISBN = getRandomValueFromList(publications.stream().map(Publication::getISBN).collect(Collectors.toList()));
        System.out.println(String.format(FIND_PUBLICATION_WITH_ISBN, randomISBN));

        Publication found = publications.stream()
                .filter(publication -> publication.getISBN().equals(randomISBN))
                .findFirst().orElse(null);

        if (found == null) {
            System.out.println(String.format(PUBLICATION_NOT_FOUND, randomISBN));
        } else {
            PrintModelVisitor printVisitor = new PrintModelVisitor();
            found.accept(printVisitor);
            System.out.println(printVisitor.getPublications());
        }
        System.out.println("================================================================================================");
    }

    void findAllPublicationsFromAuthor() {

        System.out.println("================================================================================================");

        Author randomAuthor = getRandomValueFromList(new ArrayList<>(mAuthors));
        if (randomAuthor == null) {
            throw new RuntimeException(NO_AUTHOR_FOUND);
        }
        System.out.println(String.format(FIND_PUBLICATIONS_FROM_AUTHOR, randomAuthor.getLastName(), randomAuthor.getFirstName()));

        List<Publication> publications = Stream.concat(mBooks.stream(), mMagazines.stream()).collect(Collectors.toList());
        Set<Publication> foundPublications = publications.stream()
                .filter(publication -> publication.getAuthors().contains(randomAuthor.getEmail()))
                .collect(Collectors.toSet());

        PrintModelVisitor printVisitor = new PrintModelVisitor();
        visitPublications(foundPublications, printVisitor);
        System.out.println(printVisitor.getPublications());

        System.out.println("================================================================================================");
    }

    /**
     * Alle Bücher / Zeitschriften nach Titel sortieren und ausgeben
     */
    void showAllPublicationsSorted() {

        System.out.println("================================================================================================");
        System.out.println(SORT_PUBLICATIONS_BY_TITLE);
        PrintModelVisitor printVisitor = new PrintModelVisitor();
        visitPublications(sortPublicationsAfterTitle(mBooks), printVisitor);
        visitPublications(sortPublicationsAfterTitle(mMagazines), printVisitor);

        System.out.println(printVisitor.getPublications());
        System.out.println("================================================================================================");
    }

    /////////////////////////////////////////////////////////////////////
    // Private methods
    /////////////////////////////////////////////////////////////////////

    /**
     * Get the header from the given model class
     *
     * @param modelClass which header we need
     * @return found header
     */
    Class<? extends CatalogueHeader> getHeaderClass(Class<? extends VisitableCatalogueModel> modelClass) {

        if (modelClass == null) {
            throw new RuntimeException(MODEL_CLASS_CANNOT_BE_NULL);
        }
        Class<? extends CatalogueHeader> authorHeader = CatalogueMapper.getHeaderForModelClass(modelClass);
        if (authorHeader == null) {
            throw new RuntimeException(
                    String.format(HEADER_NOT_FOUND, modelClass));
        }
        return authorHeader;
    }

    /**
     * Generified method to read any kind of {@link VisitableCatalogueModel} associated to a {@link CatalogueHeader} from a
     * {@link CSVParser}
     *
     * @param modelClass  Class implementing VisitableCatalogueModel
     * @param headerClass Class implementing CatalogueHeader
     * @param <M>         Generic type representing VisitableCatalogueModel
     * @param <H>         Generic type representing CatalogueHeader
     * @return a Collection of CatalogueModels
     */
    <M extends VisitableCatalogueModel, H extends CatalogueHeader> Set<M> importModelsFromDataSource(Class<M> modelClass, Class<H> headerClass) {

        try {
            H header = headerClass.newInstance();
            CSVParser parser = importCatalogueFromStream(header.getSourceData(), header.getValues());
            Collection<CSVRecord> records = parser.getRecords();
            return records.stream()
                    .map(record -> createModel(modelClass, record))
                    .collect(Collectors.toSet());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Instantiates the given model class and mapRecord a record to it.
     *
     * @param modelClass to be instantiated
     * @param record     to be mapped in the class
     * @param <M>        Generic for a {@link VisitableCatalogueModel}
     * @return a {@link VisitableCatalogueModel}
     */
    private <M extends VisitableCatalogueModel> M createModel(Class<M> modelClass, CSVRecord record) {

        try {
            M model = modelClass.newInstance();
            model.mapRecord(record);
            return model;
        } catch (Exception e) {
            throw new RuntimeException(String.format(MODEL_CLASS_NOT_INSTANTIATED, modelClass));
        }
    }

    /**
     * Get a resource file and parses it using {@link CSVFormat}
     *
     * @param filename path to file to read
     * @param headers  specification of the header row
     * @return a {@link CSVParser}
     * @throws Exception in case no valid path was found
     */
    CSVParser importCatalogueFromStream(final String filename, String[] headers) throws Exception {

        URL fileURL = getClass().getResource(filename);
        if (fileURL == null) {
            throw new RuntimeException(String.format(FILE_NOT_FOUND, filename));
        }
        String filePath = fileURL.getFile();
        FileReader in = new FileReader(filePath);
        return CSVFormat.DEFAULT.withHeader(headers).withDelimiter(';').withFirstRecordAsHeader().parse(in);
    }

    /**
     * Applies the given visitor to each publication on the list
     *
     * @param publicationList List of publications to be visited
     * @param visitor         to be used on each publication
     */
    private void visitPublications(Collection<? extends Publication> publicationList, Visitor visitor) {

        publicationList.forEach(publication -> publication.accept(visitor));
    }

    /**
     * Sort publication list alphabetically
     *
     * @param publicationList List of publications to be sorted
     * @return sorted publication list
     */
    private List<? extends Publication> sortPublicationsAfterTitle(Collection<? extends Publication> publicationList) {

        return publicationList.stream().sorted(Comparator.comparing(Publication::getTitle)).collect(Collectors.toList());
    }

    private <T> T getRandomValueFromList(List<T> values) {

        int randomIndex = new Random().nextInt(values.size());
        return values.get(randomIndex);
    }
}
