package com.eexposito.bookcatalogue;

import com.eexposito.bookcatalogue.models.TestHeader;
import com.eexposito.bookcatalogue.models.TestModel;
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
    private TestHeader mTestHeader;
    private TestModel mTestModel;

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

    @Test(expected = RuntimeException.class)
    public void testImportCatalogueDataSourceNotFound() throws Exception {

        mCatalogueImporter.importCatalogueFromStream("/NO_EXISTING.csv", null);
    }

    @Test
    public void testImportCatalogueSourceFileFound() throws Exception {

        CSVParser parser = mCatalogueImporter.importCatalogueFromStream(mTestHeader.getSourceData(), mTestHeader.getValues());
        assertThat("Parser is null", parser, notNullValue());
    }

    @Test
    public void testImportAuthorsFromDataSource() throws Exception {

        Set<TestModel> models = mCatalogueImporter.importModelsFromDataSource(TestModel.class, TestHeader.class);
        assertThat("Models is null", models, notNullValue());
    }
}