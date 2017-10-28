package com.eexposito.bookcatalogue;

public class BookCatalogue {

    public static void main(String[] args) {

        CatalogueImporter catalogueImporter = new CatalogueImporter();
        catalogueImporter.importCatalogue();
        catalogueImporter.showAllPublications();
        catalogueImporter.findPublicationAfterISBN();
        catalogueImporter.showAllPublicationsSorted();
    }
}
