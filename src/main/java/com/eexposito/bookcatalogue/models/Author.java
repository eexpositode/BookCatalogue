package com.eexposito.bookcatalogue.models;

import org.apache.commons.csv.CSVRecord;

public class Author {

    public static final String[] FILE_HEADER_MAPPING = {"Emailadresse", "Vorname", "Nachname"};

    private String mFirstName;
    private String mLastName;
    private String mEmail;

    public Author(CSVRecord record) {

        setEmail(record.get(FILE_HEADER_MAPPING[0]));
        setFirstName(record.get(FILE_HEADER_MAPPING[1]));
        setLastName(record.get(FILE_HEADER_MAPPING[2]));
    }

    //////////////////////////////////////////////////////////////////////
    // Getters & Setters
    //////////////////////////////////////////////////////////////////////
    public String getFirstName() {

        return mFirstName;
    }

    public void setFirstName(String mName) {

        this.mFirstName = mName;
    }

    public String getLastName() {

        return mLastName;
    }

    public void setLastName(String mFamilyName) {

        this.mLastName = mFamilyName;
    }

    public String getEmail() {

        return mEmail;
    }

    public void setEmail(String mEmail) {

        this.mEmail = mEmail;
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
