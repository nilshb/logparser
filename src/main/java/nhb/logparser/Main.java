package nhb.logparser;

import java.util.List;


public class Main {

    private static final String DEFAULT_FILE = "sample.log";

    private static void printHelpExit() {
        System.out.println("Usage: java -jar <bundle>.jar [file=<filename>] [port=<server port>] ");
        System.out.println("file: logfile (default sample.log)");
        System.out.println("port: start http server on this port");
        System.exit(0);
    }

    public static Stats generateStats(String logFile) {
        Persistence persistence = PersistenceFactory.create();
        List<LogEntry> logEntries;

        if (persistence.exists(logFile)) {
            logEntries = persistence.load(logFile);
        } else {
            Parser parser = new Parser();
            logEntries = parser.readFile(logFile);
            persistence.save(logEntries, logFile);
        }

        return new Stats(logEntries, logFile);
    }

    public static void main(String[] args) {
        String logFile = DEFAULT_FILE;
        Integer port = null;

        if (args.length == 1 && args[0].endsWith("help")) {
            printHelpExit();
        }

        if (args.length > 0) {
            for (String arg : args) {
                if (arg.contains("=")) {
                    String[] incomming = arg.split("=");
                    if ("file".equals(incomming[0])) logFile = incomming[1];
                    try {
                        if ("port".equals(incomming[0])) port = Integer.valueOf(incomming[1]);
                    } catch (NumberFormatException nfe) {
                        printHelpExit();
                    }
                } else {
                    printHelpExit();
                }
            }
        }

        try {
            Stats stats = generateStats(logFile);
            StatsServer statsServer = new StatsServer();
            statsServer.setStats(stats);
            if (port != null) statsServer.start(port);

        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
}
