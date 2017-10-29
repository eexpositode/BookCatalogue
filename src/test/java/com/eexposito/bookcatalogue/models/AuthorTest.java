package com.eexposito.bookcatalogue.models;

import com.eexposito.bookcatalogue.fixtures.FixtureModels;
import com.eexposito.bookcatalogue.headers.AuthorsHeader;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;

public class AuthorTest {

    @Test
    public void testConstructor() throws Exception {

        Author author = new Author();
        assertThat("Author cannot be null", author, notNullValue());
        assertThat("First name has to be null", author.getFirstName(), nullValue());
        assertThat("Last name has to be null", author.getLastName(), nullValue());
        assertThat("Email has to be null", author.getEmail(), nullValue());
    }

    @Test
    public void testAuthorValidValues() throws Exception {

        String mail = "John";
        String firstname = "Doe";
        String lastname = "john.doe@mail.com";

        List<CSVRecord> records = FixtureModels.buildCSVStream(new AuthorsHeader().getValues(), new String[]{mail, firstname, lastname});
        List<Author> authors = FixtureModels.fixtureModel(Author.class, records);
        assertThat("Authors cannot be null", authors, notNullValue());
        assertThat("Authors has to have 1 record", authors, hasSize(1));

        Author author = authors.stream().findAny().orElse(null);
        assertThat("No author was created", author, notNullValue());

        assertThat(String.format("First name has to be %s", firstname), author.getFirstName(), equalTo(firstname));
        assertThat(String.format("Last name has to be %s", lastname), author.getLastName(), equalTo(lastname));
        assertThat(String.format("Email has to be %s", mail), author.getEmail(), equalTo(mail));
    }
}