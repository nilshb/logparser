package nhb.logparser;

import java.util.List;


public interface Persistence {
    boolean exists(String fileName);
    void save(List<LogEntry> logEntries, String fileName);
    List<LogEntry> load(String fileName);

}
