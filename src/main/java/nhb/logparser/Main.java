package nhb.logparser;

import java.util.Arrays;
import java.util.List;


public class Main {

    private static String logFile = "sample.log";
    private static Integer port;

    private static void printHelpExit() {
        System.out.println("Usage: java -jar <bundle>.jar [file=<filename>] [port=<server port>] ");
        System.out.println("file: logfile (default sample.log)");
        System.out.println("port: start http server on this port");
        System.exit(0);
    }

    private static Stats createStats(String logFile) {
        Repository repository = new RepositoryJson();
        List<LogEntry> logEntries;

        if (repository.exists(logFile)) {
            logEntries = repository.load(logFile);
        } else {
            logEntries = new Parser().readFile(logFile);
            repository.save(logEntries, logFile);
        }

        return new Stats(logEntries, logFile);
    }

    private static void parseArgs(String[] args) {
        Arrays.stream(args).filter(x -> x.matches("\\S+=\\S+")).forEach(x -> {
            String[] keyval = x.split("=");
            switch (keyval[0]) {
                case "port":
                    port = Integer.valueOf(keyval[1]);
                    break;
                case "file":
                    logFile = keyval[1];
                    break;
            }
        });
    }

    public static void main(String[] args) {
        if (args.length == 1 && args[0].endsWith("help")) {
            printHelpExit();
        }

        if (args.length > 0) {
            parseArgs(args);
        }

        Stats stats = createStats(logFile);
        if (port != null) {
            StatsServer statsServer = new StatsServer(stats);
            statsServer.start(port);
        }
    }
}
