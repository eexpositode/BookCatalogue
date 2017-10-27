package com.eexposito.bookcatalogue;

import com.eexposito.bookcatalogue.headers.CatalogueHeader;
import com.eexposito.bookcatalogue.models.Author;
import com.eexposito.bookcatalogue.models.Book;
import com.eexposito.bookcatalogue.models.CatalogueModel;
import com.eexposito.bookcatalogue.models.Magazine;
import com.sun.istack.internal.NotNull;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.net.URL;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import static com.eexposito.bookcatalogue.CatalogueException.*;

public class CatalogueImporter {

    private static final CSVFormat CSV_FORMAT_SEMICOLON = CSVFormat.newFormat(';');
    public static final String MODEL_CLASS_NOT_INSTANTIATED = "Model class %s not instantiated";

    private Set<Author> mAuthors;
    private Set<Book> mBooks;
    private Set<Magazine> mMagazines;

    /////////////////////////////////////////////////////////////////////
    // Accessors
    /////////////////////////////////////////////////////////////////////

    /**
     * Import all data from the given csv data sources
     */
    public void importCatalogue() {

        mAuthors = importModelsFromDataSource(Author.class, getHeaderClass(Author.class));
        mBooks = importModelsFromDataSource(Book.class, getHeaderClass(Book.class));
        mMagazines = importModelsFromDataSource(Magazine.class, getHeaderClass(Magazine.class));
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
    @NotNull
    Class<? extends CatalogueHeader> getHeaderClass(@NotNull Class<? extends CatalogueModel> modelClass) {

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
     * Generified method to read any kind of {@link CatalogueModel} associated to a {@link CatalogueHeader} from a
     * {@link CSVParser}
     *
     * @param modelClass  Class implementing CatalogueModel
     * @param headerClass Class implementing CatalogueHeader
     * @param <M>         Generic type representing CatalogueModel
     * @param <H>         Generic type representing CatalogueHeader
     * @return a Collection of CatalogueModels
     */
    <M extends CatalogueModel, H extends CatalogueHeader> Set<M> importModelsFromDataSource(Class<M> modelClass, Class<H> headerClass) {

        try {
            H header = headerClass.newInstance();
            CSVParser parser = importCatalogueFromStream(header.getSourceData(), header.getValues());
            Collection<CSVRecord> records = parser.getRecords();
            return records.stream()
                    .map(record -> {
                        try {
                            M model = modelClass.newInstance();
                            model.bind(record);
                            return model;
                        } catch (Exception e) {
                            throw new RuntimeException(String.format(MODEL_CLASS_NOT_INSTANTIATED, modelClass));
                        }
                    })
                    .collect(Collectors.toSet());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
        return CSV_FORMAT_SEMICOLON.withHeader(headers).parse(in);
    }
}
