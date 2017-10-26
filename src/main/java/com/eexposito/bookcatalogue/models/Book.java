package com.eexposito.bookcatalogue.models;

import com.eexposito.bookcatalogue.headers.BooksHeader;
import org.apache.commons.csv.CSVRecord;

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

    @Override
    public void bind(CSVRecord record) {

        setTitle(record.get(BooksHeader.TITLE));
        setISBN(record.get(BooksHeader.ISBN));
//        setAuthors(record.get(BooksHeader.AUTHORS));
        setDescription(record.get(BooksHeader.DESCRIPTION));
    }
}
