package com.eexposito.bookcatalogue.fixtures;

import com.eexposito.bookcatalogue.headers.CatalogueHeader;

public class TestHeader implements CatalogueHeader {

    @Override
    public String getSourceData() {

        return "/data/testdata.csv";
    }

    @Override
    public String[] getValues() {

        return new String[]{"col1", "col2", "col3"};
    }
}