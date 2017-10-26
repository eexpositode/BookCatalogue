package com.eexposito.bookcatalogue.headers;

public class MagazineHeader extends CatalogueHeader{

    public static String getTitle() {

        return "Title";
    }

    public static String getISBN() {

        return "ISBN-Nummer";
    }

    public static String getAuthor() {

        return "Autor";
    }

    public static String getPublicationDate() {

        return "Erscheinungsdatum";
    }

    public static String[] getValues() {

        return new String[]{
                getTitle(),
                getISBN(),
                getAuthor(),
                getPublicationDate()};
    }
}
