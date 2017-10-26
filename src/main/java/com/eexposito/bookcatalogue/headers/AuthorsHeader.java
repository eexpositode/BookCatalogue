package com.eexposito.bookcatalogue.headers;

public class AuthorsHeader implements CatalogueHeader {

    public static final String FIRSTNAME = "Vorname";
    public static final String EMAIL = "Emailadresse";
    public static final String LASTNAME = "Nachname";
    private static final String DATA_FILE = "/data/autoren.csv";

    public String getSourceData() {

        return DATA_FILE;
    }

    public String[] getValues() {

        return new String[]{EMAIL, FIRSTNAME, LASTNAME};
    }
}
