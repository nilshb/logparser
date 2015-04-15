package nhb.logparser;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StatsTest {

    @Test
    public void stats_shouldReturnFilename() {
        Stats stats = createDummyStats();
        Assert.assertTrue(stats.getFilename().equals("dummy.log"));
    }

    @Test
    public void stats_shouldReturnRequestCount() {
        Stats stats = createDummyStats();
        Assert.assertTrue(stats.getRequestCount() == 2);
    }

    @Test
    public void getBrowserUsage_shouldReturnBrowserCount() {
        Stats stats = createDummyStats();
        Map<String, Integer> browsers = stats.getBrowserUsage();
        Assert.assertTrue(browsers.size() == 2);
        Assert.assertTrue(browsers.get("Firefox") == 1);
        Assert.assertTrue(browsers.get("MSIE") == 1);
    }

    @Test
    public void getOsUsage_shoulReturnOsCount() {
        Stats stats = createDummyStats();
        Map<String, Integer> oses = stats.getOsUsage();
        Assert.assertTrue(oses.size() == 1);
        Assert.assertTrue(oses.get("Windows NT 6.1") == 2);
    }

    @Test
    public void getResourceUsage_shouldReturnResourceCount() {
        Stats stats = createDummyStats();
        Map<String, Integer> resources = stats.getResourceUsage();
        Assert.assertTrue(resources.size() == 2);
        Assert.assertTrue(resources.get("/api/internal/rest/v1/utils/hash") == 1);
        Assert.assertTrue(resources.get("/web/produsent/velg.page") == 1);
    }

    private Stats createDummyStats() {
        LogEntry stat1 = new LogEntry();
        stat1.setUserAgent("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");
        stat1.setRequest("GET /api/internal/rest/v1/utils/hash?list= HTTP/1.1");
        LogEntry stat2 = new LogEntry();
        stat2.setUserAgent("Mozilla/5.0 (Windows NT 6.1; rv:18.0) Gecko/20100101 Firefox/18.0");
        stat2.setRequest("POST /web/produsent/velg.page?pageId=190 HTTP/1.1");
        List<LogEntry> logEntries = new ArrayList<>();
        logEntries.add(stat1);
        logEntries.add(stat2);

        Stats stats = new Stats(logEntries, "dummy.log");

        return stats;

    }

}
