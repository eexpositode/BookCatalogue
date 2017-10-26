package com.eexposito.bookcatalogue;

import com.eexposito.bookcatalogue.headers.AuthorsHeader;
import com.eexposito.bookcatalogue.headers.CatalogueHeader;
import com.eexposito.bookcatalogue.models.Author;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class CatalogueImporter {

    public static final CSVFormat CSV_FORMAT_SEMICOLON = CSVFormat.newFormat(';');
    public static final String AUTHOR_DATA = "/data/autoren.csv";
    public static final String BOOK_DATA = "/data/buecher.csv";
    public static final String MAGAZINE_DATA = "/data/zeitschriften.csv";

    private Set<Author> mAuthors;

    public void importCatalogue() {

        mAuthors = importAuthorsFromDataSource(Author.class, CSVMapper.modelToHeaderMap.get(Author.class));
    }

    Set<Author> importAuthorsFromDataSource(Class<Author> modelClass, Class<? extends CatalogueHeader> headerClass) {

        try {
            CSVParser parser = importCatalogueFromStream(AUTHOR_DATA);
            Collection<CSVRecord> records = parser.getRecords();
            return records.stream()
                    .map(record -> new Author(record.get(AuthorsHeader.getEmail()),
                                          record.get(AuthorsHeader.getFirstName()),
                                          record.get(AuthorsHeader.getLastName())))
                    .collect(Collectors.toSet());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    Set<Author> importAuthorsFromDataSource() {

        try {
            CSVParser parser = importCatalogueFromStream(AUTHOR_DATA);
            Collection<CSVRecord> records = parser.getRecords();
            return records.stream()
                    .map(record -> new Author(record.get(AuthorsHeader.getEmail()),
                                          record.get(AuthorsHeader.getFirstName()),
                                          record.get(AuthorsHeader.getLastName())))
                    .collect(Collectors.toSet());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    CSVParser importCatalogueFromStream(final String filename) throws Exception {

        String filePath = getClass().getResource(filename).getFile();
        FileReader in = new FileReader(filePath);
        return CSV_FORMAT_SEMICOLON.withHeader(AuthorsHeader.getValues()).parse(in);
    }
}
