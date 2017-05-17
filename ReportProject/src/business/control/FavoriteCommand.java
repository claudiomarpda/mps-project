package business.control;

import java.io.IOException;

public class FavoriteCommand implements ReportCommand {

    private ReportConnection reportConnection;
    private String[][] result;

    FavoriteCommand(ReportConnection reportConnection) {
        this.reportConnection = reportConnection;
    }

    @Override
    public void execute() {
        try {
            result = reportConnection.requestFavorite();
        } catch (IOException e) {
            result = null;
        }
    }

    public String[][] getResult() {
        return result;
    }
}
