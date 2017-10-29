package com.eexposito.bookcatalogue;

import com.eexposito.bookcatalogue.headers.CatalogueHeader;
import com.eexposito.bookcatalogue.models.*;
import com.eexposito.bookcatalogue.utils.CatalogueDefinitions;
import com.eexposito.bookcatalogue.utils.CatalogueMapper;
import com.eexposito.bookcatalogue.visitors.PrintModelVisitor;
import com.eexposito.bookcatalogue.visitors.Visitor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.net.URL;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.eexposito.bookcatalogue.utils.CatalogueException.*;

/**
 *
 */
class CatalogueImporter {

    private Collection<Author> mAuthors;
    private Collection<Book> mBooks;
    private Collection<Magazine> mMagazines;

    /////////////////////////////////////////////////////////////////////
    // Accessors
    /////////////////////////////////////////////////////////////////////

    CatalogueImporter() {

        importCatalogue();
    }

    /**
     * Lesen aller Daten aus mehreren CSV-Dateien im Ordner "data".
     * Diese finden Sie im Anhang als ZIP-Archiv. Der Aufbau der Dateien sollte selbsterkla&#x308;rend sein.
     */
    private void importCatalogue() {

        mAuthors = importModelsFromDataSource(Author.class, getHeaderClass(Author.class));
        mBooks = importModelsFromDataSource(Book.class, getHeaderClass(Book.class));
        mMagazines = importModelsFromDataSource(Magazine.class, getHeaderClass(Magazine.class));
    }

    /**
     * Alle Bu&#x308;cher / Zeitschriften mit allen Details ausgeben
     */
    String showAllPublications() {

        PrintModelVisitor printVisitor = new PrintModelVisitor();
        visitPublications(mBooks, printVisitor);
        visitPublications(mMagazines, printVisitor);

        return printVisitor.getPublications();
    }

    /**
     * Anhand einer ISBN-Nummer ein Buch / eine Zeitschrift finden und ausgeben
     *
     * @param isbn from publication to find
     */
    String findPublicationAfterISBN(String isbn) {

        List<Publication> publications = Stream.concat(mBooks.stream(), mMagazines.stream()).collect(Collectors.toList());

        final String finalISBN = isbn;
        Publication found = publications.stream()
                .filter(publication -> publication.getISBN().equals(finalISBN))
                .findFirst().orElse(null);

        if (found == null) {
            return null;
        } else {
            PrintModelVisitor printVisitor = new PrintModelVisitor();
            found.accept(printVisitor);
            return printVisitor.getPublications();
        }
    }

    /**
     * Alle Bu&#x308;cher / Zeitschriften eines Autors finden und ausgeben
     *
     * @param author whose publications to show
     */
    String findAllPublicationsFromAuthor(final Author author) {

        List<Publication> publications = Stream.concat(mBooks.stream(), mMagazines.stream()).collect(Collectors.toList());
        Set<Publication> foundPublications = publications.stream()
                .filter(publication -> publication.getAuthors().contains(author.getEmail()))
                .collect(Collectors.toSet());

        PrintModelVisitor printVisitor = new PrintModelVisitor();
        visitPublications(foundPublications, printVisitor);
        return printVisitor.getPublications();
    }

    /**
     * Alle Bu&#x308;cher / Zeitschriften nach Titel sortieren und ausgeben
     */
    String showAllPublicationsSorted() {

        PrintModelVisitor printVisitor = new PrintModelVisitor();
        visitPublications(sortPublicationsAfterTitle(mBooks), printVisitor);
        visitPublications(sortPublicationsAfterTitle(mMagazines), printVisitor);

        return printVisitor.getPublications();
    }

    /////////////////////////////////////////////////////////////////////
    // Utility methods
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
        return CatalogueDefinitions.mCustomFormat.withHeader(headers).parse(in);
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

    Author findAuthorAfterEmail(final String email) {

        return mAuthors.stream()
                .filter(author -> author.getEmail().equals(email))
                .findAny()
                .orElse(null);
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
}
