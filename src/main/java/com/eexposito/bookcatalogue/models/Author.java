package com.eexposito.bookcatalogue.models;

import com.eexposito.bookcatalogue.headers.AuthorsHeader;
import com.eexposito.bookcatalogue.visitors.Visitor;
import org.apache.commons.csv.CSVRecord;

public class Author implements VisitableCatalogueModel {

    private String mFirstName;
    private String mLastName;
    private String mEmail;

    //////////////////////////////////////////////////////////////////////
    // Getters & Setters
    //////////////////////////////////////////////////////////////////////
    public String getFirstName() {

        return mFirstName;
    }

    private void setFirstName(String firstname) {

        this.mFirstName = firstname;
    }

    public String getLastName() {

        return mLastName;
    }

    private void setLastName(String lastname) {

        this.mLastName = lastname;
    }

    public String getEmail() {

        return mEmail;
    }

    private void setEmail(String email) {

        this.mEmail = email;
    }

    //////////////////////////////////////////////////////////////////////
    // Implemented methods
    //////////////////////////////////////////////////////////////////////
    @Override
    public void mapRecord(CSVRecord record) {

        setEmail(record.get(AuthorsHeader.EMAIL));
        setFirstName(record.get(AuthorsHeader.FIRSTNAME));
        setLastName(record.get(AuthorsHeader.LASTNAME));
    }

    @Override
    public void accept(Visitor visitor) {

        visitor.visit(this);
    }
}
