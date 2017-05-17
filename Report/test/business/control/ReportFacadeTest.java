package business.control;

import mock.MockReportData;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ReportFacadeTest {

    private ReportFacade reportFacade;

    @Before
    public void setUp() throws Exception {
        reportFacade = new ReportFacade();
    }

    /**
     * Tests the result of most viewed data after request with the mock data.
     */
    @Test
    public void matchMostViewedDataWithMockData() {
        String[][] result = reportFacade.requestMostViewed();
        assertTrue(result != null);
        assertThat(result, equalTo(MockReportData.MOST_VIEWED));
    }

    /**
     * Tests the result of most compared data after request with the mock data.
     */
    @Test
    public void matchMostComparedDataWithMockData() {
        String[][] result = reportFacade.requestMostCompared();
        assertTrue(result != null);
        assertThat(result, equalTo(MockReportData.MOST_COMPARED));
    }

    /**
     * Tests the result of most compared data after request with the mock data.
     */
    @Test
    public void matchFavoriteDataWithMockData() {
        String[][] result = reportFacade.requestFavorite();
        assertTrue(result != null);
        assertThat(result, equalTo(MockReportData.FAVORITE));
    }

}