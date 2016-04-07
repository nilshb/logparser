package nhb.logparser;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class RepositoryJsonTest {

    private Repository repository;
    private String file;

    @Before
    public void setup() {
        repository = new RepositoryJson();
        file = getClass().getClassLoader().getResource("sample.mini.log").getFile();
    }

    @Test
    public void saveAndload_shouldSaveAndLoadLogEntries() {
        LogEntry logEntry1 = new LogEntry();
        logEntry1.remoteUser = "foo";
        LogEntry logEntry2 = new LogEntry();
        logEntry2.request = "bar";

        List<LogEntry> logEntries = new ArrayList<>();
        logEntries.add(logEntry1);
        logEntries.add(logEntry2);

        repository.save(logEntries, file);

        List<LogEntry> logEntriesFromPersistence = repository.load(file);

        Assert.assertEquals(2, logEntriesFromPersistence.size());
        Assert.assertEquals("foo", logEntriesFromPersistence.get(0).remoteUser);
        Assert.assertEquals("bar", logEntriesFromPersistence.get(1).request);
    }

    @After
    public void tearDown() {
        File fileToDelete = new File(file + ".json");
        if (fileToDelete.exists()) {
            fileToDelete.delete();
        }
    }
}
