package com.eexposito.bookcatalogue.models;

public abstract class Publication extends CatalogueModel {

    private String mTitle;
    private String[] mAuthors;
    private String mISBN;

    //////////////////////////////////////////////////////////////////////
    // Getters & Setters
    //////////////////////////////////////////////////////////////////////
    public String getTitle() {

        return mTitle;
    }

    void setTitle(String mTitle) {

        this.mTitle = mTitle;
    }

    public String[] getAuthors() {

        return mAuthors;
    }

    void setAuthors(String[] mAuthorList) {

        this.mAuthors = mAuthorList;
    }

    public String getISBN() {

        return mISBN;
    }

    void setISBN(String mISBN) {

        this.mISBN = mISBN;
    }
}
