package com.eexposito.bookcatalogue;

import org.apache.commons.csv.CSVRecord;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Collection;

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

        mCatalogueImporter.importCatalogueFromStream("");
    }

    @Test(expected = NullPointerException.class)
    public void testImportCatalogueDataSourceNotFound() throws Exception {

        mCatalogueImporter.importCatalogueFromStream("/NO_EXISTING.csv");
    }

    @Test
    public void testImportCatalogueSourceFileFound() throws Exception {

        Collection<CSVRecord> records = mCatalogueImporter.importCatalogueFromStream(CatalogueImporter.AUTHOR_DATA);
        assertThat("Records cannot be null", records, notNullValue());
    }
}