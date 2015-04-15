package nhb.logparser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {

    private static final int FIELD_COUNT = 10;
    private static final String logRegex = "^([\\d\\.]+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d+) (\\d+|\\-) \"(.+?)\" \"(.+?)\" \"(.+?)\"";
    private static final Pattern logPattern = Pattern.compile(logRegex);

    private LogEntry splitt(String line) {
        Matcher matcher = logPattern.matcher(line);
        if (!matcher.matches() || FIELD_COUNT != matcher.groupCount()) {
            throw new IllegalArgumentException("log line has unexpected format: \n" + line);
        }

        LogEntry logEntry = new LogEntry();
        logEntry.setRemoteHost(matcher.group(1));
        logEntry.setServerName(matcher.group(2));
        logEntry.setRemoteUser(matcher.group(3));
        logEntry.setTimestamp(matcher.group(4));
        logEntry.setRequest(matcher.group(5));
        logEntry.setStatus(Integer.parseInt(matcher.group(6)));
        logEntry.setResponseSize(matcher.group(7).equals("-") ? 0 : Integer.parseInt(matcher.group(7)));
        logEntry.setReferer(matcher.group(8));
        logEntry.setUserAgent(matcher.group(9));
        logEntry.setCookie(matcher.group(10));

        return logEntry;
    }

    public List<LogEntry> readFile(String filename) {
        List<LogEntry> logEntries = new ArrayList<>();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                LogEntry logEntry = splitt(line);
                logEntries.add(logEntry);
            }
        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
        finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
            } catch (IOException ignored) {}
        }

        return logEntries;
    }
}
