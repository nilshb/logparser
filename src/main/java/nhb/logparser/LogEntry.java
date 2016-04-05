package nhb.logparser;


public class LogEntry {

    public String remoteHost;
    public String serverName;
    public String remoteUser;
    public String timestamp;
    public String request;
    public int status;
    public int responseSize;
    public String referer;
    public String userAgent;
    public String cookie;

    @Override
    public String toString() {
        return "LogEntry{" +
                "remoteHost='" + remoteHost + '\'' +
                ", serverName='" + serverName + '\'' +
                ", remoteUser='" + remoteUser + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", request='" + request + '\'' +
                ", status=" + status +
                ", responseSize=" + responseSize +
                ", referer='" + referer + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", cookie='" + cookie + '\'' +
                '}';
    }
}
