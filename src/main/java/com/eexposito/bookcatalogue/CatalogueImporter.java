package com.eexposito.bookcatalogue;

import com.eexposito.bookcatalogue.models.Author;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class CatalogueImporter {

    public static final String AUTHOR_DATA = "/data/autoren.csv";
    public static final String BOOK_DATA = "/data/buecher.csv";
    public static final String MAGAZINE_DATA = "/data/zeitschriften.csv";

    private Set<Author> mAuthors;

    public void importCatalogue() {

        importAuthorsFromDataSource();
    }

    void importAuthorsFromDataSource() {

        Collection<CSVRecord> authorRecords = null;
        try {
            authorRecords = importCatalogueFromStream(AUTHOR_DATA);
            mAuthors = authorRecords.stream()
                    .map(Author::new)
                    .collect(Collectors.toSet());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Collection<CSVRecord> importCatalogueFromStream(final String filename) throws Exception {

        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(Author.FILE_HEADER_MAPPING);
        String filePath = getClass().getResource(filename).getFile();
        FileReader in = new FileReader(filePath);
        return new CSVParser(in, csvFileFormat).getRecords();
    }
}
