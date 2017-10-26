package com.eexposito.bookcatalogue.models;

import com.eexposito.bookcatalogue.headers.MagazineHeader;
import org.apache.commons.csv.CSVRecord;

public class Magazine extends Publication {

    private String mPublicationDate;

    @Override
    public void bind(CSVRecord record) {

        setTitle(record.get(MagazineHeader.TITLE));
        setISBN(record.get(MagazineHeader.ISBN));
//        setAuthors(record.get(MagazineHeader.AUTHOR));
        setPublicationDate(record.get(MagazineHeader.DATE));
    }

    //////////////////////////////////////////////////////////////////////
    // Getters & Setters
    //////////////////////////////////////////////////////////////////////
    public String getPublicationDate() {

        return mPublicationDate;
    }

    private void setPublicationDate(String mPublicationDate) {

        this.mPublicationDate = mPublicationDate;
    }
}
