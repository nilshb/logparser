package nhb.logparser;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StatsTest {

    @Test
    public void stats_shouldReturnFilename() {
        Assert.assertTrue(dummyStats().getFilename().equals("dummy.log"));
    }

    @Test
    public void stats_shouldReturnRequestCount() {
        Assert.assertEquals(2, dummyStats().getRequestCount());
    }

    @Test
    public void getBrowserUsage_shouldReturnBrowserCount() {
        Map<String, Integer> browsers = dummyStats().getBrowserUsage();
        Assert.assertEquals(2, browsers.size());
        Assert.assertEquals(1, (int) browsers.get("Firefox"));
        Assert.assertEquals(1, (int) browsers.get("MSIE"));
    }

    @Test
    public void getOsUsage_shoulReturnOsCount() {
        Map<String, Integer> oses = dummyStats().getOsUsage();
        Assert.assertEquals(1, oses.size());
        Assert.assertEquals(2, (int) oses.get("Windows NT 6.1"));
    }

    @Test
    public void getResourceUsage_shouldReturnResourceCount() {
        Map<String, Integer> resources = dummyStats().getResourceUsage();
        Assert.assertEquals(2, resources.size());
        Assert.assertEquals(1, (int) resources.get("/api/internal/rest/v1/utils/hash"));
        Assert.assertEquals(1, (int) resources.get("/web/produsent/velg.page"));
    }

    private static Stats dummyStats() {
        LogEntry stat1 = new LogEntry();
        stat1.userAgent = "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)";
        stat1.request = "GET /api/internal/rest/v1/utils/hash?list= HTTP/1.1";
        LogEntry stat2 = new LogEntry();
        stat2.userAgent = "Mozilla/5.0 (Windows NT 6.1; rv:18.0) Gecko/20100101 Firefox/18.0";
        stat2.request = "POST /web/produsent/velg.page?pageId=190 HTTP/1.1";

        List<LogEntry> logEntries = new ArrayList<>();
        logEntries.add(stat1);
        logEntries.add(stat2);

        return new Stats(logEntries, "dummy.log");
    }

}
