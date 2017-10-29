package com.eexposito.bookcatalogue.utils;

import org.apache.commons.csv.CSVFormat;

public class CatalogueDefinitions {

    public static final Character CSV_DELIMITER = ';';
    public static final CSVFormat mCustomFormat = CSVFormat.DEFAULT.withDelimiter(CSV_DELIMITER).withFirstRecordAsHeader();
}
