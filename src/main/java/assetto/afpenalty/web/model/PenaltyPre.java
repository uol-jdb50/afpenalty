package assetto.afpenalty.web.model;

import java.sql.Date;
import java.util.UUID;

public class PenaltyPre {
    
    private String penaltyID;
    private String reportID;
    private String sessionID;
    private String penalisedDriver;
    private Date date;
    private String regulation;
    private String decision;
    private int seconds;
    private int llpts;
    private String eventID;

    public PenaltyPre() {

    }
    public PenaltyPre(String _penaltyid, String _reportid, String _sessionid, String _penalisedDriver, Date _date, String _regulation, String _decision, int _seconds, int _llpts, String _eventId) {
        penaltyID = _penaltyid;
        reportID = _reportid;
        sessionID = _sessionid;
        penalisedDriver = _penalisedDriver;
        date = _date;
        regulation = _regulation;
        decision = _decision;
        seconds = _seconds;
        llpts = _llpts;
        eventID = _eventId;
    }
    public String getPenaltyID() {
        return penaltyID;
    }
    public String getReportID() {
        return reportID;
    }
    public String getSessionID() {
        return sessionID;
    }
    public String getPenalisedDriver() {
        return penalisedDriver;
    }
    public Date getDate() {
        return date;
    }
    public String getRegulation() {
        return regulation;
    }
    public String getDecision() {
        return decision;
    }
    public int getSeconds() {
        return seconds;
    }
    public int getLlpts() {
        return llpts;
    }
    public String getEventID() {
        return eventID;
    }
    public void setPenaltyID() {
        penaltyID = UUID.randomUUID().toString();
    }
    public void setPenaltyID(String _id) {
        penaltyID = _id;
    }
    public void setReportID(String _id) {
        reportID = _id;
    }
    public void setSessionID(String _sessionid) {
        sessionID = _sessionid;
    }
    public void setPenalisedDriver(String _penalisedDriver) {
        penalisedDriver = _penalisedDriver;
    }
    public void setDate(Date _date) {
        date = _date;
    }
    public void setRegulation(String _regulation) {
        regulation = _regulation;
    }
    public void setDecision(String _decision) {
        decision = _decision;
    }
    public void setSeconds(int _seconds) {
        seconds = _seconds;
    }
    public void setLlpts(int _llpts) {
        llpts = _llpts;
    }
    public void setEventID(String _eventId) {
        eventID = _eventId;
    }
}
