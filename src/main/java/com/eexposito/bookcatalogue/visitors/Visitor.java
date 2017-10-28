package com.eexposito.bookcatalogue.visitors;

import com.eexposito.bookcatalogue.models.Author;
import com.eexposito.bookcatalogue.models.Book;
import com.eexposito.bookcatalogue.models.Magazine;

public interface Visitor {

    void visit(Book book);

    void visit(Magazine magazine);

    void visit(Author author);
}
