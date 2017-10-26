package com.eexposito.bookcatalogue.headers;

public class AuthorsHeader extends CatalogueHeader {

    public static String getEmail() {

        return "Emailadresse";
    }

    public static String getFirstName() {

        return "Vorname";
    }

    public static String getLastName() {

        return "Nachname";
    }

    public static String getSourceData() {

        return "/data/autoren.csv";
    }

    public static String[] getValues() {

        return new String[]{
                getEmail(),
                getFirstName(),
                getLastName()};
    }
}
