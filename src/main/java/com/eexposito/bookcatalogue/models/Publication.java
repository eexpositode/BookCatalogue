package com.eexposito.bookcatalogue.models;

public abstract class Publication {

    private String mTitle;
    private Author[] mAuthorList;
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

    public Author[] getAuthorList() {

        return mAuthorList;
    }

    void setAuthorList(Author[] mAuthorList) {

        this.mAuthorList = mAuthorList;
    }

    public String getISBN() {

        return mISBN;
    }

    void setISBN(String mISBN) {

        this.mISBN = mISBN;
    }
}
