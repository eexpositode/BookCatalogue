package com.eexposito.bookcatalogue;

import com.eexposito.bookcatalogue.headers.AuthorsHeader;
import com.eexposito.bookcatalogue.headers.BooksHeader;
import com.eexposito.bookcatalogue.headers.CatalogueHeader;
import com.eexposito.bookcatalogue.headers.MagazineHeader;
import com.eexposito.bookcatalogue.models.Author;
import com.eexposito.bookcatalogue.models.Book;
import com.eexposito.bookcatalogue.models.CatalogueModel;
import com.eexposito.bookcatalogue.models.Magazine;

import java.util.HashMap;

/**
 * Factory class that binds a {@link CatalogueModel} with its {@link CatalogueHeader}
 */
class CatalogueMapper {

    private static final HashMap<Class<? extends CatalogueModel>, Class<? extends CatalogueHeader>> map =
            new HashMap<>();

    static {
        map.put(Author.class, AuthorsHeader.class);
        map.put(Book.class, BooksHeader.class);
        map.put(Magazine.class, MagazineHeader.class);
    }

    /**
     * Search for the header of the given model class
     *
     * @param modelClass
     * @return a header Class
     */
    static Class<? extends CatalogueHeader> getHeaderForModelClass(final Class<? extends CatalogueModel> modelClass) {

        return map.get(modelClass);
    }
}
