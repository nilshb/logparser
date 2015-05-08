package nhb.logparser;


public class LogEntry {

    private String remoteHost;
    private String serverName;
    private String remoteUser;
    private String timestamp;
    private String request;
    private int status;
    private int responseSize;
    private String referer;
    private String userAgent;
    private String browser;
    private String os;
    private String cookie;


    public String getRemoteHost() {
        return remoteHost;
    }

    public void setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getRemoteUser() {
        return remoteUser;
    }

    public void setRemoteUser(String remoteUser) {
        this.remoteUser = remoteUser;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getResponseSize() {
        return responseSize;
    }

    public void setResponseSize(int responseSize) {
        this.responseSize = responseSize;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

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
