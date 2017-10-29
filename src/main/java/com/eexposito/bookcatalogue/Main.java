package com.eexposito.bookcatalogue;

import com.eexposito.bookcatalogue.models.Author;

import static com.eexposito.bookcatalogue.utils.CatalogueException.NO_AUTHOR_FOUND;

public class Main {

    private static final String APP_USAGE = "usage: bookcatalogue [1] [2 <isbn>] [3] [4 <email>]";
    private static final int SHOW_PUBLICATIONS = 1;
    private static final int FIND_PUBLICATION = 2;
    private static final int ALL_PUBLICATIONS_FOR_AUTHOR = 3;
    private static final int SHOW_PUBLICATIONS_SORTED = 4;

    private static final String PRINT_ALL_PUBLICATIONS = "Print all publications\n";
    private static final String FIND_PUBLICATION_WITH_ISBN = "Find publication with ISBN %s\n";
    private static final String PUBLICATION_NOT_FOUND = "No publication with isbn %s found\n";
    private static final String FIND_PUBLICATIONS_FROM_AUTHOR = "Find all publications from %s,%s (%s)\n";
    private static final String SORT_PUBLICATIONS_BY_TITLE = "Print all publications sorted by title\n";

    public static void main(String[] args) {

        new Main().run(args);
    }

    void run(String[] args) {

        if (args.length == 0 || args.length > 2) {
            showAppUsage();
        } else {
            try {
                String value = "";
                if (args.length > 1) {
                    value = args[1];
                }
                executeOption(Integer.valueOf(args[0]), value);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                showAppUsage();
            }
        }
    }

    void executeOption(final int option, final String value) {

        switch (option) {
            case SHOW_PUBLICATIONS:
                showAllPublications();
                break;
            case FIND_PUBLICATION:
                findPublicationAfterISBN(value);
                break;
            case ALL_PUBLICATIONS_FOR_AUTHOR:
                getAllPublicationsFromAuthor(value);
                break;
            case SHOW_PUBLICATIONS_SORTED:
                getAllPublicationsSorted();
                break;
            default:
                showAppUsage();
                break;
        }
    }

    void showAllPublications() {

        System.out.println("================================================================================================");
        System.out.println(PRINT_ALL_PUBLICATIONS);
        String publications = new CatalogueImporter().showAllPublications();
        System.out.println(publications);
        System.out.println("================================================================================================");
    }

    void findPublicationAfterISBN(String value) {

        System.out.println("================================================================================================");
        System.out.println(String.format(FIND_PUBLICATION_WITH_ISBN, value));
        String publication = new CatalogueImporter().findPublicationAfterISBN(value);

        if (publication == null) {
            System.out.println(String.format(PUBLICATION_NOT_FOUND, value));
        } else {
            System.out.println(publication);
        }
        System.out.println("================================================================================================");
    }

    void getAllPublicationsFromAuthor(String value) {

        System.out.println("================================================================================================");

        CatalogueImporter catalogueImporter = new CatalogueImporter();
        Author author = catalogueImporter.findAuthorAfterEmail(value);
        if (author == null) {
            System.out.println(NO_AUTHOR_FOUND);
        } else {
            System.out.println(String.format(FIND_PUBLICATIONS_FROM_AUTHOR, author.getLastName(), author.getFirstName(), author.getEmail()));
            String publications = catalogueImporter.findAllPublicationsFromAuthor(author);
            System.out.println(publications);
        }
        System.out.println("================================================================================================");
    }

    void getAllPublicationsSorted() {

        System.out.println("================================================================================================");
        System.out.println(SORT_PUBLICATIONS_BY_TITLE);
        String publications = new CatalogueImporter().showAllPublicationsSorted();
        System.out.println(publications);
        System.out.println("================================================================================================");
    }

   void showAppUsage() {

        System.out.print(APP_USAGE);
    }
}
