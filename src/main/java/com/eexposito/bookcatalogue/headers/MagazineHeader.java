package com.eexposito.bookcatalogue.headers;

public class MagazineHeader implements CatalogueHeader {

    public static final String TITLE = "Titel";
    public static final String ISBN = "ISBN-Nummer";
    public static final String AUTHOR = "Autor";
    public static final String DATE = "Erscheinungsdatum";
    public static final String DATA_FILE = "/data/zeitschriften.csv";

    @Override
    public String getSourceData() {

        return DATA_FILE;
    }

    public String[] getValues() {

        return new String[]{TITLE, ISBN, AUTHOR, DATE};
    }
}
