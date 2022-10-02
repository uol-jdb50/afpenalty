package assetto.afpenalty.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import assetto.afpenalty.web.model.Decision;
import assetto.afpenalty.web.model.Driver;
import assetto.afpenalty.web.model.Event;
import assetto.afpenalty.web.model.League;
import assetto.afpenalty.web.model.Penalty;
import assetto.afpenalty.web.model.PenaltyPre;
import assetto.afpenalty.web.model.Regulation;
import assetto.afpenalty.web.model.Report;
import assetto.afpenalty.web.model.ReportPre;
import assetto.afpenalty.web.model.Session;

@Repository
public class ReportDao {
    JdbcTemplate template;

    @Autowired
    public void setTemplate(JdbcTemplate _template) {
        template = _template;
    }
    public String nameCheck(String field) {
        String sorted = "";
        while (true) {
            if (field.equals("")) {
                return sorted;
            }
            if (field.charAt(0) == 39) {
                sorted += "''";
            } else {
                sorted += field.charAt(0);
            }
            field = field.substring(1);
        }
    }
    public int save(ReportPre rep, String eventId) {
        String sql = "INSERT INTO Report(ReportID, SessionID, Timestamp, Description, EventID) VALUES ('" + rep.getReportID() + "', '" + rep.getSession() + "', '" + rep.getTimestamp() + "', '" + nameCheck(rep.getDescription()) + "', '" + eventId + "');";
        return template.update(sql);
    }
    public int update(Report rep) {
        String sql = "UPDATE Report SET SessionID='" + rep.getSession().getSessionID() + "', Timestamp='" + rep.getTimestamp() + "', Description='" + nameCheck(rep.getDescription()) + "' WHERE ReportID='" + rep.getReportID() + "';";
        return template.update(sql);
    }
    public int delete(String id) {
        String sql = "DELETE FROM Report WHERE ReportID='" + id + "';";
        return template.update(sql);
    }
    public Report getRepById(String id) {
        String sql = "SELECT * FROM Report WHERE ReportID='" + id + "';";
        return (Report) template.queryForObject(sql, new BeanPropertyRowMapper<Report>(Report.class));
    }
    public List<Report> getReports(String id) {
        return (List<Report>) template.query("SELECT * FROM Report WHERE EventID='" + id + "' ORDER BY Timestamp ASC;", new RowMapper<Report>(){    
            public Report mapRow(ResultSet rs, int row) throws SQLException {    
                Report r = new Report();    
                r.setReportID(rs.getString(1));
                r.setSession(getSesById(rs.getString(2)));
                r.setTimestamp(rs.getString(5));
                r.setDescription(rs.getString(6));
                return r;
            }    
        });
    }
    public Session getSesById(String id) {
        String sql = "SELECT * FROM Session WHERE SessionID='" + id + "';";
        return (Session) template.queryForObject(sql, new BeanPropertyRowMapper<Session>(Session.class));
    }
    public Session getSesForRep(String reportid) {
        String sql = "SELECT A.SessionID, A.Name FROM Session A INNER JOIN Report B ON A.SessionID=B.SessionID WHERE ReportID='" + reportid + "';";
        return (Session) template.queryForObject(sql, new BeanPropertyRowMapper<Session>(Session.class));
    }
    public List<Driver> getDriversForReport(String id) {
        return (List<Driver>) template.query("SELECT * FROM Driver A INNER JOIN ReportedDriver B ON A.DriverID=B.DriverID WHERE B.ReportID='" + id + "';", new RowMapper<Driver>(){    
            public Driver mapRow(ResultSet rs, int row) throws SQLException {    
                Driver r = new Driver();    
                r.setDriverID(rs.getString(1));
                r.setName(rs.getString(2));
                r.setHasll(rs.getBoolean(3));
                r.setLicensedate(rs.getDate(4));
                return r;
            }    
        });
    }
    public List<Driver> getAllDrivers() {
        return (List<Driver>) template.query("SELECT * FROM Driver ORDER BY Name ASC;", new RowMapper<Driver>(){    
            public Driver mapRow(ResultSet rs, int row) throws SQLException {    
                Driver r = new Driver();    
                r.setDriverID(rs.getString(1));
                r.setName(rs.getString(2));
                r.setHasll(rs.getBoolean(3));
                r.setLicensedate(rs.getDate(4));
                return r;
            }    
        });
    }
    public List<Penalty> getPenaltiesForReport(String id) {
        return (List<Penalty>) template.query("SELECT * FROM Penalty WHERE ReportID='" + id + "';", new RowMapper<Penalty>(){    
            public Penalty mapRow(ResultSet rs, int row) throws SQLException {    
                Penalty r = new Penalty();
                r.setPenaltyID(rs.getString(1));
                r.setPenalisedDriver(getDriverForPen(rs.getString(3)));
                r.setDate(rs.getDate(4));
                r.setRegulation(getRegulationForPen(rs.getString(5)));
                r.setDecision(getDecisionForPen(rs.getString(6)));
                r.setSeconds(rs.getInt(7));
                r.setLlpts(rs.getInt(8));
                r.setEventID(getEventForPen(rs.getString(9)));
                return r;
            }    
        });
    }
    public Driver getDriverForPen(String id) {
        String sql = "SELECT * FROM Driver WHERE DriverID='" + id + "';";
        return (Driver) template.queryForObject(sql, new BeanPropertyRowMapper<Driver>(Driver.class));
    }
    public Regulation getRegulationForPen(String id) {
        String sql = "SELECT * FROM Violation WHERE ViolationID='" + id + "';";
        return (Regulation) template.queryForObject(sql, new BeanPropertyRowMapper<Regulation>(Regulation.class));
    }
    public Decision getDecisionForPen(String id) {
        String sql = "SELECT * FROM Decision WHERE DecisionID='" + id + "';";
        return (Decision) template.queryForObject(sql, new BeanPropertyRowMapper<Decision>(Decision.class));
    }
    public Event getEventForPen(String id) {
        String sql = "SELECT * FROM Event WHERE EventID='" + id + "';";
        return (Event) template.queryForObject(sql, new BeanPropertyRowMapper<Event>(Event.class));
    }
    public List<Driver> getAttendance(String id) {
        return (List<Driver>) template.query("SELECT A.DriverID, A.Name, A.HasLL, A.LicenseDate FROM Driver A INNER JOIN Attendance B ON A.DriverID=B.DriverID WHERE B.EventID='" + id + "' AND B.Present=1 ORDER BY A.Name ASC;", new RowMapper<Driver>(){    
            public Driver mapRow(ResultSet rs, int row) throws SQLException {    
                Driver r = new Driver();    
                r.setDriverID(rs.getString(1));
                r.setName(rs.getString(2));
                r.setHasll(rs.getBoolean(3));
                r.setLicensedate(rs.getDate(4));
                return r;
            }    
        });
    }
    public int deleteAttendance(String driver, String event) {
        String sql = "DELETE FROM Attendance WHERE DriverID='" + driver + "' AND EventID='" + event + "';";
        return template.update(sql);
    }
    public String getSeasonIdFromEventId(String id) {
        String sql = "SELECT * FROM Event WHERE EventID='" + id + "';";
        return ((League) template.queryForObject(sql, new BeanPropertyRowMapper<League>(League.class))).getSeasonID();
    }
    public int saveSession(Session session) {
        String sql = "INSERT INTO Session(SessionID, EventID, Name) VALUES ('" + session.getSessionID() + "', '" + session.getEventID() + "', '" + session.getName() + "');";
        return template.update(sql);
    }
    public List<Session> getSessions(String id) {
        return (List<Session>) template.query("SELECT * FROM Session WHERE EventID='" + id + "' ORDER BY Name ASC;", new RowMapper<Session>(){    
            public Session mapRow(ResultSet rs, int row) throws SQLException {    
                Session r = new Session();    
                r.setSessionID(rs.getString(1));
                r.setEventID(rs.getString(2));
                r.setName(rs.getString(3));
                return r;
            }    
        });
    }
    public int updateSession(Session session) {
        String sql = "UPDATE Session SET Name='" + session.getName() + "' WHERE SessionID='" + session.getSessionID() + "';";
        return template.update(sql);
    }
    public int deleteSession(String id) {
        String sql = "DELETE FROM Session WHERE SessionID='" + id + "';";
        return template.update(sql);
    }
    public List<Driver> fillDriverList(List<Driver> drivers) {
        List<Driver> newdrivers = new ArrayList<Driver>();
        for (Driver d1: drivers) {
            String sql = "SELECT TOP 1 * FROM Driver WHERE Guid='" + d1.getGuid() + "' OR Name='" + d1.getName() + "';";
            List<Driver> dtemp = template.query(sql, new RowMapper<Driver>() {
                public Driver mapRow(ResultSet rs, int row) throws SQLException {
                    Driver d = d1;
                    d.setDriverID(rs.getString(1));
                    d.setName(rs.getString(2));
                    d.setHasll(rs.getBoolean(3));
                    d.setLicensedate(rs.getDate(4));
                    d.setGuid(rs.getString(5));
                    return d;
                }
            });
            if (!dtemp.isEmpty()) newdrivers.add(dtemp.get(0));
            else newdrivers.add(d1);
        }
        return newdrivers;
    }
    public Driver fillDriver(Driver driver) {
        Driver d1 = driver;
        String sql = "SELECT TOP 1 * FROM Driver WHERE Guid='" + driver.getGuid() + "' OR Name='" + driver.getName() + "';";
        List<Driver> dtemp = template.query(sql, new RowMapper<Driver>() {
            public Driver mapRow(ResultSet rs, int row) throws SQLException {
                Driver d = d1;
                d.setDriverID(rs.getString(1));
                d.setName(rs.getString(2));
                d.setHasll(rs.getBoolean(3));
                d.setLicensedate(rs.getDate(4));
                d.setGuid(rs.getString(5));
                return d;
            }
        });
        if (!dtemp.isEmpty()) return dtemp.get(0);
        else return d1;
    }
    public void addNewDrivers(List<Driver> drivers) {
        for (Driver d: drivers) {
            d.setDriverID();
            String sql = "INSERT INTO Driver VALUES ('" + d.getDriverID() + "', '" + d.getName() + "', 0, '', '" + d.getGuid() + "');";
            template.update(sql);
        }
    }
    public void updateDrivers(List<Driver> drivers) {
        for (Driver d: drivers) {
            String sql = "UPDATE Driver SET Guid='" + d.getGuid() + "' WHERE DriverID='" + d.getDriverID() + "';";
            template.update(sql);
        }
    }
    public void addAttendance(List<Driver> drivers, String eventid) {
        for (Driver d: drivers) {
            String sql = "IF NOT EXISTS (SELECT * FROM Attendance WHERE EventID='" + eventid + "' AND DriverID='" + d.getDriverID() + "') INSERT INTO Attendance VALUES ('" + eventid + "', '" + d.getDriverID() + "', 1);";
            template.update(sql);
        }
    }
    public Driver getDriverByID(String id) {
        String sql = "SELECT TOP 1 * FROM Driver WHERE DriverID='" + id + "';";
        return (Driver) template.queryForObject(sql, new BeanPropertyRowMapper<Driver>(Driver.class));
    }
    public int saveInvolve(Driver driver, String reportid) {
        String sql = "INSERT INTO ReportedDriver VALUES ('" + reportid + "', '" + driver.getDriverID() + "');";
        return template.update(sql);
    }
    public int deleteInvolve(String driverid, String reportid) {
        String sql = "DELETE FROM ReportedDriver WHERE ReportID='" + reportid + "' AND DriverID='" + driverid + "';";
        return template.update(sql);
    }
    public List<Decision> getDecisions() {
        return (List<Decision>) template.query("SELECT * FROM Decision ORDER BY Text ASC;", new RowMapper<Decision>(){    
            public Decision mapRow(ResultSet rs, int row) throws SQLException {    
                Decision r = new Decision();    
                r.setDecisionID(rs.getString(1));
                r.setText(rs.getString(2));
                return r;
            }    
        });
    }
    public List<Regulation> getRegulations() {
        return (List<Regulation>) template.query("SELECT * FROM Violation ORDER BY Text ASC;", new RowMapper<Regulation>(){    
            public Regulation mapRow(ResultSet rs, int row) throws SQLException {    
                Regulation r = new Regulation();    
                r.setViolationID(rs.getString(1));
                r.setText(rs.getString(2));
                return r;
            }    
        });
    }
    public int savePenalty(PenaltyPre p, String report) {
        String sql = "INSERT INTO Penalty VALUES ('" + p.getPenaltyID() + "', '" + report + "', '" + p.getPenalisedDriver() + "', '', '" + p.getRegulation() + "', '" + p.getDecision() + "', " + p.getSeconds() + ", " + p.getLlpts() + ", '" + p.getEventID() + "');";
        return template.update(sql);
    }
    public PenaltyPre getPenById(String id) {
        String sql = "SELECT * FROM Penalty WHERE PenaltyID='" + id + "';";
        return (PenaltyPre) template.queryForObject(sql, new BeanPropertyRowMapper<PenaltyPre>(PenaltyPre.class));
    }
    public int updatePenalty(PenaltyPre p) {
        String sql = "UPDATE Penalty SET PenalisedDriver='" + p.getPenalisedDriver() + "', Violation='" + p.getRegulation() + "', Decision='" + p.getDecision() + "', Seconds=" + p.getSeconds() + ", LicensePts=" + p.getLlpts() + " WHERE PenaltyID='" + p.getPenaltyID() + "';";
        return template.update(sql);
    }
    public int deletePenalty(String id) {
        String sql = "DELETE FROM Penalty WHERE PenaltyID='" + id + "';";
        return template.update(sql);
    }
}
