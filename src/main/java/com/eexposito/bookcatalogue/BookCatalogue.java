package com.eexposito.bookcatalogue;

import com.eexposito.bookcatalogue.models.Book;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class BookCatalogue {

    public static final String AUTHOR_DATA = "/data/autoren.csv";
    public static final String BOOK_DATA = "/data/buecher.csv";
    public static final String MAGAZINE_DATA = "/data/zeitschriften.csv";
    //Author CSV file header

    public static void main(String[] args) {

        System.out.println("Hello world");

        newInstance().readFile(BOOK_DATA);
    }

    private static BookCatalogue newInstance() {

        return new BookCatalogue();
    }

    // TODO: 25/10/17 Generify this to be able to pass the Mapable class and extract the info from that
    private Set<Book> readFile(final String filePath) {

        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(Book.FILE_HEADER_MAPPING);
        try {
            FileReader in = new FileReader(filePath);
            Collection<CSVRecord> records = new CSVParser(in, csvFileFormat).getRecords();
            return records.stream()
                    .map(Book::new)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
