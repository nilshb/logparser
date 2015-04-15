package nhb.logparser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;


public class PersistenceWithJson implements Persistence {

    public final static String FILE_SUFFIX = ".json";

    public boolean exists(String fileName) {
        return new File(fileName + FILE_SUFFIX).exists();
    }

    public void save(List<LogEntry> logEntries, String fileName) {
            Gson gson = new Gson();
            String jsonString = gson.toJson(logEntries);
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(fileName + FILE_SUFFIX);
                fileWriter.write(jsonString);
            } catch (IOException ioe) {
                throw new UncheckedIOException(ioe);
            } finally {
                try {
                    if (fileWriter != null) fileWriter.close();
                } catch (IOException ignored) {}
            }

    }

    public List<LogEntry> load(String fileName) {
        Gson gson = new Gson();
        BufferedReader bufferedReader = null;
        List<LogEntry> logEntries = null;
        try {
            Type type = new TypeToken<List<LogEntry>>() {}.getType();
            bufferedReader = new BufferedReader(new FileReader(fileName + FILE_SUFFIX));
            logEntries = gson.fromJson(bufferedReader, type);
        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);
        } finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
            } catch (IOException ignored) {}
        }

        return logEntries;
    }
}
