package view;

import business.control.ReportFacade;

public class Main {

    public static void main(String[] args) {
        ReportFacade reportFacade = new ReportFacade();
        String[][] viewResult = reportFacade.requestMostViewed();
        String[][] comparisonResult = reportFacade.requestMostCompared();
        String[][] favoriteResult = reportFacade.requestFavorite();

        for (String[] s : viewResult) {
            for (String t : s) {
                System.out.print(t + ", ");
            }
            System.out.println();
        }
        System.out.println();

        for (String[] s : comparisonResult) {
            for (String t : s) {
                System.out.print(t + ", ");
            }
            System.out.println();
        }
        System.out.println();

        for (String[] s : favoriteResult) {
            for (String t : s) {
                System.out.print(t + ", ");
            }
            System.out.println();
        }
    }
}
