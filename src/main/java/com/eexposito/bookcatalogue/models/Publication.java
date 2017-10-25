package com.eexposito.bookcatalogue.models;

public abstract class Publication implements CSVMappable {

    private String mTitle;
    private Author[] mAuthorList;
    private String mISBN;

    //////////////////////////////////////////////////////////////////////
    // Getters & Setters
    //////////////////////////////////////////////////////////////////////
    public String getTitle() {

        return mTitle;
    }

    private void setTitle(String mTitle) {

        this.mTitle = mTitle;
    }

    public Author[] getAuthorList() {

        return mAuthorList;
    }

    private void setAuthorList(Author[] mAuthorList) {

        this.mAuthorList = mAuthorList;
    }

    public String getISBN() {

        return mISBN;
    }

    private void setISBN(String mISBN) {

        this.mISBN = mISBN;
    }
}
