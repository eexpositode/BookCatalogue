package com.eexposito.bookcatalogue.models;

import java.util.List;

public abstract class Publication implements VisitableCatalogueModel {

    private String mTitle;
    private List<String> mAuthors;
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

    public List<String> getAuthors() {

        return mAuthors;
    }

    void setAuthors(List<String> mAuthorList) {

        this.mAuthors = mAuthorList;
    }

    public String getISBN() {

        return mISBN;
    }

    void setISBN(String mISBN) {

        this.mISBN = mISBN;
    }
}
