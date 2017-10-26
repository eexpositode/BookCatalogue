package com.eexposito.bookcatalogue.models;

import org.apache.commons.csv.CSVRecord;

public class Book extends Publication {

    public static final String[] FILE_HEADER_MAPPING = {"Titel", "ISBN-Nummer", "Autoren", "Kurzbeschreibung"};
    private String mDescription;

    public Book(CSVRecord record) {

        setTitle(record.get(FILE_HEADER_MAPPING[0]));
        setISBN(record.get(FILE_HEADER_MAPPING[1]));
//        setAuthorList(record.get(FILE_HEADER_MAPPING[2]));
        setDescription(record.get(FILE_HEADER_MAPPING[3]));
    }

    //////////////////////////////////////////////////////////////////////
    // Getters & Setters
    //////////////////////////////////////////////////////////////////////
    public String getDescription() {

        return mDescription;
    }

    private void setDescription(String mDescription) {

        this.mDescription = mDescription;
    }

    //////////////////////////////////////////////////////////////////////
    // Implementations
    //////////////////////////////////////////////////////////////////////
//    @Override
//    public String[] getFileHeaderMapping() {
//
//        return FILE_HEADER_MAPPING;
//    }
}
