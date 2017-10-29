package com.eexposito.bookcatalogue;

public class Main {

    private static final String APP_USAGE = "usage: bookcatalogue [1] [2 <isbn>] [3] [4 <email>]";
    private static final int SHOW_PUBLICATIONS = 1;
    private static final int FIND_PUBLICATION = 2;
    private static final int ALL_PUBLICATIONS_FOR_AUTHOR = 3;
    private static final int SHOW_PUBLICATIONS_SORTED = 4;

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println(APP_USAGE);
        } else {
            try {
                int option = Integer.valueOf(args[0]);
                String value = "";
                if (args.length > 1) {
                    value = args[1];
                }
                CatalogueImporter catalogueImporter = new CatalogueImporter();
                catalogueImporter.importCatalogue();
                switch (option) {
                    case SHOW_PUBLICATIONS:
                        catalogueImporter.showAllPublications();
                        break;
                    case FIND_PUBLICATION:
                        catalogueImporter.findPublicationAfterISBN(value);
                        break;
                    case ALL_PUBLICATIONS_FOR_AUTHOR:
                        catalogueImporter.findAllPublicationsFromAuthor(value);
                        break;
                    case SHOW_PUBLICATIONS_SORTED:
                        catalogueImporter.showAllPublicationsSorted();
                        break;
                    default:
                        System.out.println(APP_USAGE);
                        break;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                System.out.println(APP_USAGE);
            }
        }
    }
}
