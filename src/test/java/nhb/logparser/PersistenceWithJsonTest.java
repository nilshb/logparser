package nhb.logparser;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class PersistenceWithJsonTest {

    private Persistence persistence;
    private String file;

    @Before
    public void init() {
        persistence = new PersistenceWithJson();
        file = getClass().getClassLoader().getResource("sample.mini.log").getFile();
    }

    @Test
    public void saveAndload_shouldSaveAndLoadLogEntries() {
        LogEntry toPersistence1 = new LogEntry();
        toPersistence1.setRemoteUser("foo");
        LogEntry toPersistence2 = new LogEntry();
        toPersistence2.setRequest("bar");

        List<LogEntry> logEntries = new ArrayList<>();
        logEntries.add(toPersistence1);
        logEntries.add(toPersistence2);

        persistence.save(logEntries, file);

        List<LogEntry> logEntriesFromPersistence = persistence.load(file);
        Assert.assertTrue(logEntriesFromPersistence.size() == 2);
        LogEntry fromPersistence1 = logEntriesFromPersistence.get(0);
        LogEntry fromPersistence2 = logEntriesFromPersistence.get(1);

        Assert.assertEquals("foo", fromPersistence1.getRemoteUser());
        Assert.assertEquals("bar", fromPersistence2.getRequest());
    }

    @After
    public void tearDown() {
        File fileToDelete = new File(file + ".json");
        if (fileToDelete.exists()) {
            fileToDelete.delete();
        }
    }
}
