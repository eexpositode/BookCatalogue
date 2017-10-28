package com.eexposito.bookcatalogue.models;

import com.eexposito.bookcatalogue.visitors.CatalogueVisitor;
import com.sun.deploy.util.StringUtils;
import org.apache.commons.csv.CSVRecord;

import java.util.stream.Collectors;

public class TestModelVisitable implements VisitableCatalogueModel {

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

    @Override
    public void accept(CatalogueVisitor visitor) {

    }
}
