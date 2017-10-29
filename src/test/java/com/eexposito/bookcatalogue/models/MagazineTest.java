package com.eexposito.bookcatalogue.models;

import com.eexposito.bookcatalogue.fixtures.FixtureModels;
import com.eexposito.bookcatalogue.headers.MagazineHeader;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;

public class MagazineTest {

    @Test
    public void testConstructor() throws Exception {

        Magazine magazine = new Magazine();
        assertThat("magazine cannot be null", magazine, notNullValue());
        assertThat("Title has to be null", magazine.getTitle(), nullValue());
        assertThat("Description name has to be null", magazine.getPublicationDate(), nullValue());
        assertThat("ISBN has to be null", magazine.getISBN(), nullValue());
        assertThat("Authors has to be null", magazine.getAuthors(), nullValue());
    }

    @Test
    public void testMagazineValidValues() throws Exception {

        String title = "Treasure Island";
        String authors = "robert.louis.stevenson@mail.com, ";
        String date = "1111";
        String isbn = "0000";

        List<CSVRecord> records = FixtureModels.buildCSVStream(new MagazineHeader().getValues(),
                                                               new String[]{title, isbn, authors, date});
        List<Magazine> magazineList = FixtureModels.fixtureModel(Magazine.class, records);
        assertThat("magazineList cannot be null", magazineList, notNullValue());
        assertThat("magazineList has to have 1 record", magazineList, hasSize(1));

        Magazine magazine = magazineList.stream().findAny().orElse(null);
        assertThat("No Magazine was created", magazine, notNullValue());

        assertThat("Title has to be null", magazine.getTitle(), equalTo(title));
        assertThat("Description name has to be null", magazine.getPublicationDate(), equalTo(date));
        assertThat("ISBN has to be null", magazine.getISBN(), equalTo(isbn));
        List<String> authorList = magazine.getAuthors();
        assertThat("There should be 2 authors", authorList, hasSize(2));
        assertThat("Authors has to be null", authorList.get(0), equalTo("robert.louis.stevenson@mail.com"));
        assertThat("Authors has to be null", authorList.get(1), equalTo(" "));
    }
}
