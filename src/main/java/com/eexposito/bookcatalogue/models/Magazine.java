package com.eexposito.bookcatalogue.models;

import com.eexposito.bookcatalogue.headers.MagazineHeader;
import com.eexposito.bookcatalogue.visitors.Visitor;
import org.apache.commons.csv.CSVRecord;

import java.util.Arrays;

public class Magazine extends Publication {

    private String mPublicationDate;

    //////////////////////////////////////////////////////////////////////
    // Getters & Setters
    //////////////////////////////////////////////////////////////////////
    public String getPublicationDate() {

        return mPublicationDate;
    }

    private void setPublicationDate(String mPublicationDate) {

        this.mPublicationDate = mPublicationDate;
    }

    //////////////////////////////////////////////////////////////////////
    // Implemented methods
    //////////////////////////////////////////////////////////////////////

    @Override
    public void bind(CSVRecord record) {

        setTitle(record.get(MagazineHeader.TITLE));
        setISBN(record.get(MagazineHeader.ISBN));
        setAuthors(Arrays.asList(record.get(MagazineHeader.AUTHOR).split(",")));
        setPublicationDate(record.get(MagazineHeader.DATE));
    }

    @Override
    public void accept(Visitor visitor) {

        visitor.visit(this);
    }
}
