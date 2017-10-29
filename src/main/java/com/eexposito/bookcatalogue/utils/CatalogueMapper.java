package com.eexposito.bookcatalogue.utils;

import com.eexposito.bookcatalogue.headers.AuthorsHeader;
import com.eexposito.bookcatalogue.headers.BooksHeader;
import com.eexposito.bookcatalogue.headers.CatalogueHeader;
import com.eexposito.bookcatalogue.headers.MagazineHeader;
import com.eexposito.bookcatalogue.models.Author;
import com.eexposito.bookcatalogue.models.Book;
import com.eexposito.bookcatalogue.models.VisitableCatalogueModel;
import com.eexposito.bookcatalogue.models.Magazine;

import java.util.HashMap;

/**
 * Factory class that binds a {@link VisitableCatalogueModel} with its {@link CatalogueHeader}
 *
 * This way we can ensure the parsing is done without mismatching data source and header. Another way to accomplish that,
 * could have been creating an Importer Class for each data source where we could bind a Model with its Header. Those
 * classes would inherit from a parent class that use generics to refer to its children.
 */
public class CatalogueMapper {

    /**
     * Static mapping from data source and header. In order to support other data, remember doing the mapping here
     */
    private static final HashMap<Class<? extends VisitableCatalogueModel>, Class<? extends CatalogueHeader>> map =
            new HashMap<>();

    static {
        map.put(Author.class, AuthorsHeader.class);
        map.put(Book.class, BooksHeader.class);
        map.put(Magazine.class, MagazineHeader.class);
    }

    /**
     * Search for the header of the given model class
     *
     * @param modelClass which header we want to find
     * @return a header Class
     */
    public static Class<? extends CatalogueHeader> getHeaderForModelClass(final Class<? extends VisitableCatalogueModel> modelClass) {

        return map.get(modelClass);
    }
}
