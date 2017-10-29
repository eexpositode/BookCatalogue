package com.eexposito.bookcatalogue;

import com.eexposito.bookcatalogue.models.TestHeader;
import com.eexposito.bookcatalogue.models.TestModel;
import org.apache.commons.csv.CSVParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static com.eexposito.bookcatalogue.utils.CatalogueException.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class CatalogueImporterTest {

    private CatalogueImporter mCatalogueImporter;
    private static final String mHeaderForChecking = "col1,col2,col3";
    private static final List<String> mValuesForChecking = Arrays.asList(
            "val11,val12,val13",
            "val21,val22,val23",
            "val31,val32,val33");

    @Before
    public void setUp() throws Exception {

        mCatalogueImporter = new CatalogueImporter();
    }


    @After
    public void tearDown() throws Exception {

        mCatalogueImporter = null;
    }

    //////////////////////////////////////////////////////////////////////////
    // Test CatalogueImporterTest::getHeaderClass
    //////////////////////////////////////////////////////////////////////////
    @Test(expected = RuntimeException.class)
    public void testGetHeaderClassModelClassIsNull() throws Exception {

        try {
            mCatalogueImporter.getHeaderClass(null);
        } catch (RuntimeException e) {
            assertThat("Unexpected exception message", e.getMessage(), equalTo(MODEL_CLASS_CANNOT_BE_NULL));
            throw e;
        }
    }

    @Test(expected = RuntimeException.class)
    public void testGetHeaderClassHeaderNotFound() throws Exception {

        try {
            mCatalogueImporter.getHeaderClass(TestModel.class);
        } catch (RuntimeException e) {
            assertThat("Unexpected exception message", e.getMessage(), equalTo(String.format(HEADER_NOT_FOUND, TestModel.class)));
            throw e;
        }
    }

    //////////////////////////////////////////////////////////////////////////
    // Test CatalogueImporterTest::importCatalogueFromStream
    //////////////////////////////////////////////////////////////////////////
    @Test(expected = FileNotFoundException.class)
    public void testImportCatalogueNoDataSource() throws Exception {

        mCatalogueImporter.importCatalogueFromStream("", null);
    }

    @Test(expected = RuntimeException.class)
    public void testImportCatalogueDataSourceNotFound() throws Exception {

        String filename = "/NO_EXISTING.csv";
        try {
            mCatalogueImporter.importCatalogueFromStream(filename, null);
        } catch (RuntimeException e) {
            assertThat("Unexpected exception message", e.getMessage(), equalTo(String.format(FILE_NOT_FOUND, filename)));
            throw e;
        }
    }

    @Test
    public void testImportCatalogueSourceFileFound() throws Exception {

        TestHeader header = new TestHeader();
        CSVParser parser = mCatalogueImporter.importCatalogueFromStream(header.getSourceData(), header.getValues());
        assertThat("Parser is null", parser, notNullValue());
    }

    //////////////////////////////////////////////////////////////////////////
    // Test CatalogueImporterTest::importModelsFromDataSource
    //////////////////////////////////////////////////////////////////////////
    @Test
    public void testImportAuthorsFromDataSource() throws Exception {

        Set<TestModel> models = mCatalogueImporter.importModelsFromDataSource(TestModel.class, TestHeader.class);
        assertThat("Models is null", models, notNullValue());
        assertThat("Models should have 3 items", models, hasSize(3));

        // Test the right values were parsed
        mValuesForChecking.forEach(stringValues -> checkModelValues(models, stringValues));
    }

    //////////////////////////////////////////////////////////////////////////
    // Test utils
    //////////////////////////////////////////////////////////////////////////
    private void checkModelValues(Set<TestModel> models, final String valuesToCheck) {

        TestModel foundModel = models.stream()
                .filter(model -> model.getValues().equals(valuesToCheck))
                .findFirst().orElse(null);
        assertThat("Model values doesn't match", foundModel, notNullValue());
    }
}