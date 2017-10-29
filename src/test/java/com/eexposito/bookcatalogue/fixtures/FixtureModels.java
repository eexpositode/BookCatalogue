package com.eexposito.bookcatalogue.fixtures;

import com.eexposito.bookcatalogue.models.VisitableCatalogueModel;
import com.eexposito.bookcatalogue.utils.CatalogueDefinitions;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

public class FixtureModels {

    public static List<CSVRecord> buildCSVStream(String[] headerValues, String[] infoValues) throws Exception {

        String header = StringUtils.join(headerValues, CatalogueDefinitions.CSV_DELIMITER);
        String data = StringUtils.join(infoValues, CatalogueDefinitions.CSV_DELIMITER);
        String csvString = StringUtils.join(Arrays.asList(header, data), "\n");
        return CSVParser
                .parse(csvString,
                       CatalogueDefinitions.mCustomFormat.withHeader(headerValues))
                .getRecords();
    }

    public static <M extends VisitableCatalogueModel> List<M> fixtureModel(final Class<M> modelClass, final List<CSVRecord> records)
            throws Exception {

        assertThat(records, notNullValue());

        return records.stream()
                .map(record -> {
                    M model = null;
                    try {
                        model = modelClass.newInstance();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (model != null) {
                        model.mapRecord(record);
                    }
                    return model;
                })
                .collect(Collectors.toList());
    }
}
