package com.eexposito.bookcatalogue.models;

import com.eexposito.bookcatalogue.visitors.Visitor;
import org.apache.commons.csv.CSVRecord;

/**
 * Interface for the csv file data definition
 */
public interface VisitableCatalogueModel {

    void mapRecord(CSVRecord record);

    void accept(Visitor visitor);
}
