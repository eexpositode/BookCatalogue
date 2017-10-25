package com.eexposito.bookcatalogue.models;

public class Author implements CSVMappable {

    private static final String[] FILE_HEADER_MAPPING = {"Emailadresse", "Vorname", "Nachname"};

    private String mFirstName;
    private String mLastName;
    private String mEmail;

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
    @Override
    public String[] getFileHeaderMapping() {

        return FILE_HEADER_MAPPING;
    }
}
