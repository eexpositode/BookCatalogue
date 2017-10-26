package com.eexposito.bookcatalogue.headers;

public class BooksHeader implements CatalogueHeader {

    public static final String TITLE = "Titel";
    public static final String ISBN = "ISBN-Nummer";
    public static final String AUTHORS = "Autoren";
    public static final String DESCRIPTION = "Kurzbeschreibung";
    public static final String DATA_FILE = "/data/buecher.csv";

    @Override
    public String getSourceData() {

        return DATA_FILE;
    }

    @Override
    public String[] getValues() {

        return new String[]{TITLE, ISBN, AUTHORS, DESCRIPTION};
    }
}
