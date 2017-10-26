package com.eexposito.bookcatalogue;

import com.eexposito.bookcatalogue.headers.CatalogueHeader;
import com.eexposito.bookcatalogue.models.Author;
import com.eexposito.bookcatalogue.models.CatalogueModel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class CatalogueImporter {

    private static final CSVFormat CSV_FORMAT_SEMICOLON = CSVFormat.newFormat(';');

    private Set<Author> mAuthors;

    public void importCatalogue() {

        mAuthors = importModelsFromDataSource(Author.class, CSVModelToHeaderMapper.map.get(Author.class));
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
//
//    Set<Author> importModelsFromDataSource() {
//
//        try {
//            CSVParser parser = importCatalogueFromStream(AUTHOR_DATA, header.getValues());
//            Collection<CSVRecord> records = parser.getRecords();
//            return records.stream()
//                    .map(record -> new Author(record.get(AuthorsHeader.getEmail()),
//                                          record.get(AuthorsHeader.getFirstName()),
//                                          record.get(AuthorsHeader.getLastName())))
//                    .collect(Collectors.toSet());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    CSVParser importCatalogueFromStream(final String filename, String[] headers) throws Exception {

        String filePath = getClass().getResource(filename).getFile();
        FileReader in = new FileReader(filePath);
        return CSV_FORMAT_SEMICOLON.withHeader(headers).parse(in);
    }
}
