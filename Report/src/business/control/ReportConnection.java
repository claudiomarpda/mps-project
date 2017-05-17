package business.control;

import mock.MockReportData;

import java.io.IOException;

/**
 * Makes use of mock data to simulate a requisition to a server
 * and return the requested data.
 */
public class ReportConnection {

    public String[][] requestMostViewed() throws IOException {
        return MockReportData.MOST_VIEWED;
    }

    public String[][] requestMostCompared() throws IOException {
        return MockReportData.MOST_COMPARED;
    }

    public String[][] requestFavorite() throws IOException {
        return MockReportData.FAVORITE;
    }
}
