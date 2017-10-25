package com.eexposito.bookcatalogue.models;

import org.apache.commons.csv.CSVRecord;

public interface CSVMappable {

    void setRecord(CSVRecord record);
    String[] getFileHeaderMapping();
}