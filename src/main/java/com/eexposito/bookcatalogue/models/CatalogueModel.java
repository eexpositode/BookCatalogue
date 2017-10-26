package com.eexposito.bookcatalogue.models;

import org.apache.commons.csv.CSVRecord;

public interface CatalogueModel {

    void bind(CSVRecord record);
}
