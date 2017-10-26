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

public class CSVMapper {

    public static final HashMap<Class<? extends CatalogueModel>, Class<? extends CatalogueHeader>> modelToHeaderMap =
            new HashMap<>();

    static {
        modelToHeaderMap.put(Author.class, AuthorsHeader.class);
        modelToHeaderMap.put(Book.class, BooksHeader.class);
        modelToHeaderMap.put(Magazine.class, MagazineHeader.class);
    }
}
