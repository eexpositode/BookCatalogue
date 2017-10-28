package com.eexposito.bookcatalogue.models;

import com.eexposito.bookcatalogue.visitors.Visitor;
import org.apache.commons.csv.CSVRecord;

public interface VisitableCatalogueModel {

    void bind(CSVRecord record);

    void accept(Visitor visitor);
}
