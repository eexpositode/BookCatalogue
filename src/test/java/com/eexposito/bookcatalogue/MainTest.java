package com.eexposito.bookcatalogue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.mockito.Mockito.*;

public class MainTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private Main mockMain;

    @Before
    public void setUp() throws Exception {

        mockMain = spy(Main.class);
    }

    @After
    public void tearDown() throws Exception {

        mockMain = null;
    }

    /////////////////////////////////////////////////////////////////////
    // Check call
    /////////////////////////////////////////////////////////////////////
    @Test
    public void runNoArguments() throws Exception {

        String[] args = {};
        mockMain.run(args);
        verify(mockMain, times(1)).showAppUsage();
    }

    @Test
    public void runTooMuchArguments() throws Exception {

        String[] args = {"a", "b", "c"};
        mockMain.run(args);
        verify(mockMain, times(1)).showAppUsage();
    }

    @Test
    public void runWrongOptionFormat() throws Exception {

        String[] args = new String[]{"Hello world"};
        mockMain.run(args);
        verify(mockMain, times(1)).showAppUsage();
    }

    @Test
    public void runRightOption() throws Exception {

        String[] args = new String[]{String.valueOf(1)};
        mockMain.run(args);
        verify(mockMain, times(1)).executeOption(anyInt(), anyString());
    }

    /////////////////////////////////////////////////////////////////////
    // Check call
    /////////////////////////////////////////////////////////////////////
    @Test
    public void executeWrongOption() throws Exception {

        mockMain.executeOption(5, "");
        verify(mockMain, times(1)).showAppUsage();
    }

    @Test
    public void executeShowAll() throws Exception {

        mockMain.executeOption(1, "");
        verify(mockMain, times(1)).showAllPublications();
    }

    @Test
    public void executeFindPublications() throws Exception {

        mockMain.executeOption(2, "");
        verify(mockMain, times(1)).findPublicationAfterISBN(anyString());
    }
    @Test
    public void executeFindPublicationsForAuthor() throws Exception {

        mockMain.executeOption(3, "");
        verify(mockMain, times(1)).getAllPublicationsFromAuthor(anyString());
    }

    @Test
    public void executeSortPublications() throws Exception {

        mockMain.executeOption(4, "");
        verify(mockMain, times(1)).getAllPublicationsSorted();
    }
}