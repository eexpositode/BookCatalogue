package com.eexposito.bookcatalogue.models;

public class Author extends CatalogueModel {

    private String mFirstName;
    private String mLastName;
    private String mEmail;

    public Author(String email, String firstName, String lastName) {

        setEmail(email);
        setFirstName(firstName);
        setLastName(lastName);
    }
    //////////////////////////////////////////////////////////////////////
    // Getters & Setters
    //////////////////////////////////////////////////////////////////////
    public String getFirstName() {

        return mFirstName;
    }

    private void setFirstName(String mName) {

        this.mFirstName = mName;
    }

    public String getLastName() {

        return mLastName;
    }

    private void setLastName(String mFamilyName) {

        this.mLastName = mFamilyName;
    }

    public String getEmail() {

        return mEmail;
    }

    private void setEmail(String mEmail) {

        this.mEmail = mEmail;
    }
}
