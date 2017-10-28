package com.eexposito.bookcatalogue;

class BookCatalogue {

    void run() {

        CatalogueImporter catalogueImporter = new CatalogueImporter();
        catalogueImporter.importCatalogue();
        catalogueImporter.showAllPublications();
        catalogueImporter.findPublicationAfterISBN();
        catalogueImporter.findAllPublicationsFromAuthor();
        catalogueImporter.showAllPublicationsSorted();
    }
}
