package com.eexposito.bookcatalogue.visitors;

import com.eexposito.bookcatalogue.models.Author;
import com.eexposito.bookcatalogue.models.Book;
import com.eexposito.bookcatalogue.models.Magazine;
import org.apache.commons.lang3.StringUtils;

/**
 * Visitor pattern used to print and keep this logic away from the models.
 */
public class PrintModelVisitor implements Visitor {

    private String mModelString = "";

    public String getPublications() {

        return mModelString;
    }

    @Override
    public void visit(Book book) {

        mModelString = mModelString.concat(String.format("Title: %s\n", book.getTitle()));
        mModelString = mModelString.concat(String.format("Description: %s\n", book.getDescription()));
        mModelString = mModelString.concat(String.format("Authors: %s\n", StringUtils.join(book.getAuthors(), ", ")));
        mModelString = mModelString.concat(String.format("ISBN: %s\n", book.getISBN()));
    }

    @Override
    public void visit(Magazine magazine) {

        mModelString = mModelString.concat(String.format("Title: %s\n", magazine.getTitle()));
        mModelString = mModelString.concat(String.format("Publication date: %s\n", magazine.getPublicationDate()));
        mModelString = mModelString.concat(String.format("Author: %s\n", StringUtils.join(magazine.getAuthors(), ", ")));
        mModelString = mModelString.concat(String.format("ISBN: %s\n", magazine.getISBN()));
    }

    @Override
    public void visit(Author author) {

        mModelString = mModelString.concat(String.format("Firstname: %s\n", author.getFirstName()));
        mModelString = mModelString.concat(String.format("Lastname: %s\n", author.getLastName()));
        mModelString = mModelString.concat(String.format("Email: %s\n", author.getEmail()));
    }

}
