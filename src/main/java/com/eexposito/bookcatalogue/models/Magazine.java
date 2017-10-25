package com.eexposito.bookcatalogue.models;

public class Magazine extends Publication {

    private static final String[] FILE_HEADER_MAPPING = {"Titel", "ISBN-Nummer", "Autor", "Erscheinungsdatum"};

    private String mPublicationDate;

    public String getPublicationDate() {

        return mPublicationDate;
    }

    public void setPublicationDate(String mPublicationDate) {

        this.mPublicationDate = mPublicationDate;
    }

    //////////////////////////////////////////////////////////////////////
    // Implementations
    //////////////////////////////////////////////////////////////////////
    @Override
    public String[] getFileHeaderMapping() {

        return FILE_HEADER_MAPPING;
    }
}
