package com.eexposito.bookcatalogue.models;

import com.eexposito.bookcatalogue.headers.AuthorsHeader;
import org.apache.commons.csv.CSVRecord;

public class Author implements CatalogueModel {

    private String mFirstName;
    private String mLastName;
    private String mEmail;

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

    @Override
    public void bind(CSVRecord record) {

        setEmail(record.get(AuthorsHeader.EMAIL));
        setFirstName(record.get(AuthorsHeader.FIRSTNAME));
        setLastName(record.get(AuthorsHeader.LASTNAME));
    }
}
