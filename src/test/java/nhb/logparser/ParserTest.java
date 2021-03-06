package nhb.logparser;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;


public class ParserTest {

    private Parser parser;
    private String file;

    @Before
    public void setup() {
        parser = new Parser();
        file = getClass().getClassLoader().getResource("sample.mini.log").getFile();
    }

    @Test
    public void readFile_shouldReturnListOfEntries() {
        List<LogEntry> logEntries = parser.readFile(file);
        Assert.assertEquals(14, logEntries.size());
    }

    @Test
    public void readFile_shouldParseLogToLogEntry() {
        List<LogEntry> logEntries = parser.readFile(file);
        LogEntry firstLogEntry = logEntries.get(0);
        Assert.assertEquals("10.230.128.140", firstLogEntry.remoteHost);
        Assert.assertEquals("medlem.tine.no", firstLogEntry.serverName);
        Assert.assertEquals("-", firstLogEntry.remoteUser);
        Assert.assertEquals("14/Feb/2013:01:21:27 +0100", firstLogEntry.timestamp);
        Assert.assertEquals("GET /api/internal/rest/v1/utils/hash?list= HTTP/1.1", firstLogEntry.request);
        Assert.assertEquals(200, firstLogEntry.status);
        Assert.assertEquals(101, firstLogEntry.responseSize);
        Assert.assertEquals("https://medlem.tine.no/minedata-kk/#/animals/animal/2455698", firstLogEntry.referer);
        Assert.assertEquals("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)", firstLogEntry.userAgent);
        Assert.assertEquals("__utma=196210227.402036472.1353454017.1353454017.1353454017.1; __utmz=196210227.1353454017.1.1.utmcsr=1885.no|utmccn=(referral)|utmcmd=referral|utmcct=/info/tine-sa; uid=10.230.128.140.1329643255562347; Inseminering=no.tine.reports.helse.fjoesstatus.beans.Inseminering%3D40%2C15; DrektighetsKontroll=no.tine.reports.helse.fjoesstatus.beans.DrektighetsKontroll%3D43; Avsining=no.tine.reports.helse.fjoesstatus.beans.Avsining%3D60; BakterielleProever=no.tine.reports.helse.fjoesstatus.beans.BakterielleProever%3D60%2C21; VurderingSinBehandling=no.tine.reports.helse.fjoesstatus.beans.VurderingSinBehandling%3D60; VurderingBehandlaDyr=no.tine.reports.helse.fjoesstatus.beans.VurderingBehandlaDyr%3D30; produsent=16246176; __utma=54892727.1753266847.1350512896.1360538206.1360801254.27; __utmz=54892727.1350512896.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); JSESSIONID=F5CD3DD673088A5D5CB7C16427D4A938; IV_JCT=%2FFIM; __utmb=54892727.2.10.1360801254; __utmc=54892727", firstLogEntry.cookie);
    }
}
