package business.control;

/**
 * Follows Facade pattern and makes use of commands to access data trough ReportConnection.
 */
public class ReportFacade {

    private ReportConnection reportConnection;

    public ReportFacade() {
        reportConnection = new ReportConnection();
    }

    public String[][] requestMostViewed() {
        MostViewedCommand viewCommand = new MostViewedCommand(reportConnection);
        new ReportInvoker(viewCommand).execute();
        return viewCommand.getResult();
    }

    public String[][] requestMostCompared() {
        MostComparedCommand comparisonCommand = new MostComparedCommand(reportConnection);
        new ReportInvoker(comparisonCommand).execute();
        return comparisonCommand.getResult();
    }

    public String[][] requestFavorite() {
        FavoriteCommand favoriteCommand = new FavoriteCommand(reportConnection);
        new ReportInvoker(favoriteCommand).execute();
        return favoriteCommand.getResult();
    }
}
