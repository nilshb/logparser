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
    private static final String logRegex = "^([\\d\\.]+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d+) " +
            "(\\d+|\\-) \"(.+?)\" \"(.+?)\" \"(.+?)\"";
    private static final Pattern logPattern = Pattern.compile(logRegex);

    private LogEntry createLogEntry(String line) {
        Matcher matcher = logPattern.matcher(line);
        if (!matcher.matches() || FIELD_COUNT != matcher.groupCount()) {
            throw new IllegalArgumentException("log line has unexpected format: \n" + line);
        }

        LogEntry logEntry = new LogEntry();
        logEntry.remoteHost = matcher.group(1);
        logEntry.serverName = matcher.group(2);
        logEntry.remoteUser = matcher.group(3);
        logEntry.timestamp = matcher.group(4);
        logEntry.request = matcher.group(5);
        logEntry.status = Integer.parseInt(matcher.group(6));
        logEntry.responseSize = matcher.group(7).equals("-") ? 0 : Integer.parseInt(matcher.group(7));
        logEntry.referer = matcher.group(8);
        logEntry.userAgent = matcher.group(9);
        logEntry.cookie = matcher.group(10);

        return logEntry;
    }

    public List<LogEntry> readFile(String filename) {
        List<LogEntry> logEntries = new ArrayList<>();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                logEntries.add(createLogEntry(line));
            }
        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);
        } finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
            } catch (IOException ignored) {
            }
        }

        return logEntries;
    }
}
