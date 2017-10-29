package com.eexposito.bookcatalogue.models;

import com.eexposito.bookcatalogue.fixtures.FixtureModels;
import com.eexposito.bookcatalogue.headers.BooksHeader;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;

public class BookTest {

    @Test
    public void testConstructor() throws Exception {

        Book book = new Book();
        assertThat("book cannot be null", book, notNullValue());
        assertThat("Title has to be null", book.getTitle(), nullValue());
        assertThat("Description name has to be null", book.getDescription(), nullValue());
        assertThat("ISBN has to be null", book.getISBN(), nullValue());
        assertThat("Authors has to be null", book.getAuthors(), nullValue());
    }

    @Test
    public void testBookValidValues() throws Exception {

        String title = "Treasure Island";
        String authors = "robert.louis.stevenson@mail.com, ";
        String description = "Adventures";
        String isbn = "0000";

        List<CSVRecord> records = FixtureModels.buildCSVStream(new BooksHeader().getValues(),
                                                               new String[]{title, isbn, authors, description});
        List<Book> bookList = FixtureModels.fixtureModel(Book.class, records);
        assertThat("bookList cannot be null", bookList, notNullValue());
        assertThat("bookList has to have 1 record", bookList, hasSize(1));

        Book book = bookList.stream().findAny().orElse(null);
        assertThat("No Book was created", book, notNullValue());

        assertThat("Title has to be null", book.getTitle(), equalTo(title));
        assertThat("Description name has to be null", book.getDescription(), equalTo(description));
        assertThat("ISBN has to be null", book.getISBN(), equalTo(isbn));
        List<String> authorList = book.getAuthors();
        assertThat("There should be 2 authors", authorList, hasSize(2));
        assertThat("Authors has to be null", authorList.get(0), equalTo("robert.louis.stevenson@mail.com"));
        assertThat("Authors has to be null", authorList.get(1), equalTo(" "));
    }
}
