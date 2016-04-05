package nhb.logparser;

import com.google.code.useragent.UserAgentParser;
import java.util.*;


public class Stats {

    private List<LogEntry> logEntries;
    private String filename;

    public Stats(List<LogEntry> logEntries, String filename) {
        this.logEntries = logEntries;
        this.filename = filename;
    }

    public String getFilename() {
        return this.filename;
    }

    public int getRequestCount() {
        return logEntries.size();
    }

    public Map<String, Integer> getBrowserUsage() {
        HashMap<String, Integer> browsers = new HashMap<>();
        for (LogEntry entry : logEntries) {
            String browserName = new UserAgentParser(entry.userAgent).getBrowserName();
            if (browserName == null) browserName = "-";
            if (browsers.containsKey(browserName)) {
                browsers.put(browserName, browsers.get(browserName)+1);
            } else {
                browsers.put(browserName, 1);
            }
        }
        return sortByValue(browsers);
    }

    public Map<String, Integer> getOsUsage() {
        HashMap<String, Integer> os = new HashMap<>();
        for (LogEntry entry : logEntries) {
            String osName = new UserAgentParser(entry.userAgent).getBrowserOperatingSystem();
            if (osName == null) osName = "-";
            if (os.containsKey(osName)) {
                os.put(osName, os.get(osName)+1);
            } else {
                os.put(osName, 1);
            }
        }
        return sortByValue(os);
    }

    public Map<String, Integer> getResourceUsage() {
        HashMap<String, Integer> resources = new HashMap<>();
        for (LogEntry entry : logEntries) {
            String request = entry.request;
            String resFound = "-";
            String[] resArray = request.split(" ");
            if (resArray.length == 3) {
                resFound = resArray[1];
                if (resFound.indexOf('?') != -1) resFound = resFound.substring(0, resFound.indexOf('?'));
            }

            if (resources.containsKey(resFound)) {
                resources.put(resFound, resources.get(resFound)+1);
            } else {
                resources.put(resFound, 1);
            }
        }
        return sortByValue(resources);
    }

    private static Map<String, Integer> sortByValue(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, (m1, m2) -> (m2.getValue()).compareTo(m1.getValue()));
        Map<String, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

}
