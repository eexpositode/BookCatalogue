package com.eexposito.bookcatalogue.headers;

public class BooksHeader extends CatalogueHeader {

    public static String getTitel() {

        return "Titel";
    }

    public static String getISBN() {

        return "ISBN-Nummer";
    }

    public static String getAuthors() {

        return "Autoren";
    }

    public static String getDescription() {

        return "Kurzbeschreibung";
    }

    public static String[] getValues() {

        return new String[]{
                getTitel(),
                getISBN(),
                getAuthors(),
                getDescription()};
    }
}
