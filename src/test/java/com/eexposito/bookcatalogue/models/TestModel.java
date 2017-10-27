package com.eexposito.bookcatalogue.models;

import com.sun.deploy.util.StringUtils;
import org.apache.commons.csv.CSVRecord;

import java.util.stream.Collectors;

public class TestModel implements CatalogueModel {

    private String mValues;

    public String getValues() {

        return mValues;
    }

    @Override
    public void bind(CSVRecord record) {

        mValues = StringUtils.join(record.toMap().values().stream()
                                           .sorted(String::compareTo)
                                           .collect(Collectors.toList()), ",");
    }
}
