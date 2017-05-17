package business.control;

import java.io.IOException;

/**
 * Requests through ReportConnection for the list of most compared items.
 */
public class MostComparedCommand implements ReportCommand {

    private ReportConnection reportConnection;
    private String[][] result;

    MostComparedCommand(ReportConnection reportConnection) {
        this.reportConnection = reportConnection;
    }

    @Override
    public void execute() {
        try {
            result = reportConnection.requestMostCompared();
        } catch (IOException e) {
            result = null;
        }
    }

    public String[][] getResult() {
        return result;
    }
}