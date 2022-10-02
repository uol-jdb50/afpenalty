package assetto.afpenalty.web.model;

import java.util.UUID;

public class Report {
    private String reportID;
    private Session session;
    private String timestamp;
    private String description;

    public Report() {

    }
    public Report(String _reportid, Session _session, String _timestamp, String _description) {
        reportID = _reportid;
        session = _session;
        timestamp = _timestamp;
        description = _description;
    }
    public String getReportID() {
        return reportID;
    }
    public Session getSession() {
        return session;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public String getDescription() {
        return description;
    }
    public void setReportID() {
        reportID = UUID.randomUUID().toString();
    }
    public void setReportID(String _reportid) {
        reportID = _reportid;
    }
    public void setSession(Session _session) {
        session = _session;
    }
    public void setTimestamp(String _timestamp) {
        timestamp = _timestamp;
    }
    public void setDescription(String _description) {
        description = _description;
    }
}
