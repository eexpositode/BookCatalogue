package com.eexposito.bookcatalogue.models;

import com.eexposito.bookcatalogue.headers.BooksHeader;
import com.eexposito.bookcatalogue.visitors.Visitor;
import org.apache.commons.csv.CSVRecord;

import java.util.Arrays;

public class Book extends Publication {

    private String mDescription;

    //////////////////////////////////////////////////////////////////////
    // Getters & Setters
    //////////////////////////////////////////////////////////////////////
    public String getDescription() {

        return mDescription;
    }

    private void setDescription(String mDescription) {

        this.mDescription = mDescription;
    }

    //////////////////////////////////////////////////////////////////////
    // Implemented methods
    //////////////////////////////////////////////////////////////////////

    @Override
    public void mapRecord(CSVRecord record) {

        setTitle(record.get(BooksHeader.TITLE));
        setISBN(record.get(BooksHeader.ISBN));
        setAuthors(Arrays.asList(record.get(BooksHeader.AUTHORS).split(",")));
        setDescription(record.get(BooksHeader.DESCRIPTION));
    }

    @Override
    public void accept(Visitor visitor) {

        visitor.visit(this);
    }
}
