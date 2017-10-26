package com.eexposito.bookcatalogue;

import com.eexposito.bookcatalogue.headers.AuthorsHeader;
import com.eexposito.bookcatalogue.models.Author;
import org.apache.commons.csv.CSVParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Set;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class CatalogueImporterTest {

    private CatalogueImporter mCatalogueImporter;

    @Before
    public void setUp() throws Exception {

        mCatalogueImporter = new CatalogueImporter();
    }

    @After
    public void tearDown() throws Exception {

        mCatalogueImporter = null;
    }

    @Test(expected = FileNotFoundException.class)
    public void testImportCatalogueNoDataSource() throws Exception {

        mCatalogueImporter.importCatalogueFromStream("", null);
    }

    @Test(expected = NullPointerException.class)
    public void testImportCatalogueDataSourceNotFound() throws Exception {

        mCatalogueImporter.importCatalogueFromStream("/NO_EXISTING.csv", null);
    }

    @Test
    public void testImportCatalogueSourceFileFound() throws Exception {

        AuthorsHeader authorsHeader = new AuthorsHeader();
        CSVParser parser = mCatalogueImporter.importCatalogueFromStream(authorsHeader.getSourceData(), authorsHeader.getValues());
        assertThat("Parser is null", parser, notNullValue());
    }

    @Test
    public void testImportAuthorsFromDataSource() throws Exception {

        Set<Author> authors = mCatalogueImporter.importModelsFromDataSource(Author.class, AuthorsHeader.class);
        assertThat("Authors is null", authors, notNullValue());
    }
}