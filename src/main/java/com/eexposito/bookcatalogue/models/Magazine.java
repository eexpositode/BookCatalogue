package com.eexposito.bookcatalogue.models;

public class Magazine extends Publication {

    private String mPublicationDate;

    public Magazine(String title, String isbn, String[] authors, String date) {

        setTitle(title);
        setISBN(isbn);
        setAuthors(authors);
        setPublicationDate(date);
    }

    public String getPublicationDate() {

        return mPublicationDate;
    }

    private void setPublicationDate(String mPublicationDate) {

        this.mPublicationDate = mPublicationDate;
    }
}
