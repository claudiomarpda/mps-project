package business.control;

import java.io.IOException;

/**
 * Requests through ReportConnection for the list of most viewed items.
 */
public class MostViewedCommand implements ReportCommand {

    private ReportConnection reportConnection;
    private String[][] result;

    MostViewedCommand(ReportConnection reportConnection) {
        this.reportConnection = reportConnection;
    }

    @Override
    public void execute() {
        try {
            result = reportConnection.requestMostViewed();
        } catch (IOException e) {
            result = null;
        }
    }

    public String[][] getResult() {
        return result;
    }
}
