package com.eexposito.bookcatalogue.models;

import com.eexposito.bookcatalogue.visitors.Visitor;
import org.apache.commons.csv.CSVRecord;

public interface VisitableCatalogueModel {

    void mapRecord(CSVRecord record);

    void accept(Visitor visitor);
}
