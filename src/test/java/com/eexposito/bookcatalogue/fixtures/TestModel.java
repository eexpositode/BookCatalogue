package com.eexposito.bookcatalogue.fixtures;

import com.eexposito.bookcatalogue.models.VisitableCatalogueModel;
import com.eexposito.bookcatalogue.visitors.Visitor;
import com.sun.deploy.util.StringUtils;
import org.apache.commons.csv.CSVRecord;

import java.util.stream.Collectors;

public class TestModel implements VisitableCatalogueModel {

    private String mValues;

    public String getValues() {

        return mValues;
    }

    @Override
    public void mapRecord(CSVRecord record) {

        mValues = StringUtils.join(record.toMap().values().stream()
                                           .sorted(String::compareTo)
                                           .collect(Collectors.toList()), ",");
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
