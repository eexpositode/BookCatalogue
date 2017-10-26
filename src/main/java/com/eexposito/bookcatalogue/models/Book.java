package com.eexposito.bookcatalogue.models;

public class Book extends Publication {

    private String mDescription;

    public Book(String title, String isbn, String[] authors, String description) {

        setTitle(title);
        setISBN(isbn);
        setAuthors(authors);
        setDescription(description);
    }

    //////////////////////////////////////////////////////////////////////
    // Getters & Setters
    //////////////////////////////////////////////////////////////////////
    public String getDescription() {

        return mDescription;
    }

    private void setDescription(String mDescription) {

        this.mDescription = mDescription;
    }
}
