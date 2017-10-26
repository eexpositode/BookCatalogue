package com.eexposito.bookcatalogue;

import com.eexposito.bookcatalogue.headers.CatalogueHeader;
import com.eexposito.bookcatalogue.models.Author;
import com.eexposito.bookcatalogue.models.Book;
import com.eexposito.bookcatalogue.models.CatalogueModel;
import com.eexposito.bookcatalogue.models.Magazine;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.net.URL;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class CatalogueImporter {

    private static final CSVFormat CSV_FORMAT_SEMICOLON = CSVFormat.newFormat(';');

    private Set<Author> mAuthors;
    private Set<Book> mBooks;
    private Set<Magazine> mMagazines;

    public void importCatalogue() {

        mAuthors = importModelsFromDataSource(Author.class, CSVMapper.map.get(Author.class));
        mBooks = importModelsFromDataSource(Book.class, CSVMapper.map.get(Book.class));
        mMagazines = importModelsFromDataSource(Magazine.class, CSVMapper.map.get(Magazine.class));
    }

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
                            e.printStackTrace();
                            return null;
                        }
                    })
                    .collect(Collectors.toSet());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    CSVParser importCatalogueFromStream(final String filename, String[] headers) throws Exception {

        URL fileURL = getClass().getResource(filename);
        if (fileURL == null) {
            throw new RuntimeException(String.format("File %s not found", filename));
        }
        String filePath = fileURL.getFile();
        FileReader in = new FileReader(filePath);
        return CSV_FORMAT_SEMICOLON.withHeader(headers).parse(in);
    }
}
