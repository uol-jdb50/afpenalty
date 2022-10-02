package assetto.afpenalty.web.model;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public class Event {
    private String eventID;
    private String seasonID;
    private League season;
    private String name;
    private Date date;
    private List<Report> reports;
    private List<Session> sessions;
    private List<Driver> drivers; //Attendance
    private int driverllpts;

    public Event() {

    }
    public Event(String _leagueID) {
        seasonID = _leagueID;
    }
    public Event(String _eventid, String _leagueID, String _name, Date _date) {
        eventID = _eventid;
        seasonID = _leagueID;
        name = _name;
        date = _date;
    }
    public String getEventID() {
        return eventID;
    }
    public String getSeasonID() {
        return seasonID;
    }
    public League getSeason() {
        return season;
    }
    public String getName() {
        return name;
    }
    public Date getDate() {
        return date;
    }
    public int getDriverllpts() {
        return driverllpts;
    }
    public void setEventID() {
        eventID = UUID.randomUUID().toString();
    }
    public void setEventID(String _id) {
        eventID = _id;
    }
    public void setSeasonID(String _leagueID) {
        seasonID = _leagueID;
    }
    public void setSeason(League _season) {
        season = _season;
    }
    public void setName(String _name) {
        name = _name;
    }
    public void setDate(Date _date) {
        date = _date;
    }
    public void setReports(List<Report> _reports) {
        reports = _reports;
    }
    public void addReport(Report _report) {
        reports.add(_report);
    }
    public void setSessions(List<Session> _sessions) {
        sessions = _sessions;
    }
    public void addSession(Session _session) {
        sessions.add(_session);
    }
    public void setDrivers(List<Driver> _drivers) {
        drivers = _drivers;
    }
    public void addDriver(Driver _driver) {
        drivers.add(_driver);
    }
    public void setDriverllpts(int pts) {
        driverllpts = pts;
    }
}
