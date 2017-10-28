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
 */
public class CatalogueMapper {

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
