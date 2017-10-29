package com.eexposito.bookcatalogue.utils;

/**
 * Exceptions used while parsing
 */
public class CatalogueException {

    public static final String HEADER_NOT_FOUND = "Header for class %s not found. Did you maybe forgot to mapRecord it in the CatalogueMapper?";
    public static final String FILE_NOT_FOUND = "File %s not found";
    public static final String MODEL_CLASS_CANNOT_BE_NULL = "Model class cannot be null";
    public static final String MODEL_CLASS_NOT_INSTANTIATED = "Model class %s not instantiated";
    public static final String NO_AUTHOR_FOUND = "Something went wrong: No author was found.";
}
