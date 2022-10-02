package assetto.afpenalty.web.model;

import java.sql.Date;
import java.util.UUID;

public class Penalty {
    private String penaltyID;
    private String reportID;
    private Driver penalisedDriver;
    private Date date;
    private Regulation regulation;
    private Decision decision;
    private int seconds;
    private int llpts;
    private Event eventID;

    public Penalty() {

    }
    public Penalty(String _penaltyid, String _reportid, Driver _penalisedDriver, Date _date, Regulation _regulation, Decision _decision, int _seconds, int _llpts, Event _eventId) {
        penaltyID = _penaltyid;
        reportID = _reportid;
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
    public Driver getPenalisedDriver() {
        return penalisedDriver;
    }
    public Date getDate() {
        return date;
    }
    public Regulation getRegulation() {
        return regulation;
    }
    public Decision getDecision() {
        return decision;
    }
    public int getSeconds() {
        return seconds;
    }
    public int getLlpts() {
        return llpts;
    }
    public Event getEventID() {
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
    public void setPenalisedDriver(Driver _penalisedDriver) {
        penalisedDriver = _penalisedDriver;
    }
    public void setDate(Date _date) {
        date = _date;
    }
    public void setRegulation(Regulation _regulation) {
        regulation = _regulation;
    }
    public void setDecision(Decision _decision) {
        decision = _decision;
    }
    public void setSeconds(int _seconds) {
        seconds = _seconds;
    }
    public void setLlpts(int _llpts) {
        llpts = _llpts;
    }
    public void setEventID(Event _eventId) {
        eventID = _eventId;
    }
}
