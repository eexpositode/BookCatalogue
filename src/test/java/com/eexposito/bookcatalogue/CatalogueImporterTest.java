package com.eexposito.bookcatalogue;

import com.eexposito.bookcatalogue.headers.AuthorsHeader;
import com.eexposito.bookcatalogue.headers.CatalogueHeader;
import com.eexposito.bookcatalogue.models.Author;
import com.eexposito.bookcatalogue.models.CatalogueModel;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Set;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class CatalogueImporterTest {

    private CatalogueImporter mCatalogueImporter;
    private TestHeader mTestHeader;
    private TestModel mTestModel;

    private class TestModel implements CatalogueModel {

        @Override
        public void bind(CSVRecord record) {

        }
    }

    private class TestHeader implements CatalogueHeader {

        @Override
        public String getSourceData() {

            return "/data/testdata.csv";
        }

        @Override
        public String[] getValues() {

            return new String[]{"col1", "col2", "col3"};
        }
    }

    @Before
    public void setUp() throws Exception {

        mCatalogueImporter = new CatalogueImporter();
        mTestHeader = new TestHeader();
        mTestModel = new TestModel();
    }

    @After
    public void tearDown() throws Exception {

        mCatalogueImporter = null;
        mTestHeader = null;
        mTestModel = null;
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
        CSVParser parser = mCatalogueImporter.importCatalogueFromStream(mTestHeader.getSourceData(), mTestHeader.getValues());
        assertThat("Parser is null", parser, notNullValue());
    }

    @Test
    public void testImportAuthorsFromDataSource() throws Exception {

        Set<Author> authors = mCatalogueImporter.importModelsFromDataSource(Author.class, AuthorsHeader.class);
        assertThat("Authors is null", authors, notNullValue());
    }
}