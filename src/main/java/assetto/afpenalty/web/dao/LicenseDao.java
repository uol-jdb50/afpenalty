package assetto.afpenalty.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import assetto.afpenalty.web.model.Driver;
import assetto.afpenalty.web.model.Event;
import assetto.afpenalty.web.model.League;
import assetto.afpenalty.web.model.Penalty;

@Repository
public class LicenseDao {
    JdbcTemplate template;

    @Autowired
    public void setTemplate(JdbcTemplate _template) {
        template = _template;
    }
    public List<Driver> getAllDrivers() {
        return (List<Driver>) template.query("SELECT * FROM Driver WHERE HasLL=1;", new RowMapper<Driver>(){    
            public Driver mapRow(ResultSet rs, int row) throws SQLException {    
                Driver r = new Driver();    
                r.setDriverID(rs.getString(1));
                r.setName(rs.getString(2));
                r.setHasll(rs.getBoolean(3));
                r.setLicensedate(rs.getDate(4));
                r.setGuid(rs.getString(5));
                return r;
            }    
        });
    }
    public List<Event> getRecentEvents(String id) {
        String sql = "SELECT DISTINCT TOP (8) A.EventID, A.SeasonID, A.Name, A.Date FROM Event A INNER JOIN Attendance B ON A.EventID=B.EventID INNER JOIN Driver C ON B.DriverID=C.DriverID WHERE B.DriverID='" + id + "' AND B.Present=1 AND C.LicenseDate<A.Date ORDER BY A.Date DESC;";
        return (List<Event>) template.query(sql, new RowMapper<Event>(){    
            public Event mapRow(ResultSet rs, int row) throws SQLException {    
                Event r = new Event();    
                r.setEventID(rs.getString(1));
                r.setSeasonID(rs.getString(2));
                r.setName(rs.getString(3));
                r.setDate(rs.getDate(4));
                return r;
            }    
        });
    }
    public List<Penalty> getPenaltyForEvent(String event, String driver) {
        String sql = "SELECT Seconds, LicensePts FROM Penalty WHERE PenalisedDriver='" + driver + "' AND EventID='" + event + "';";
        return (List<Penalty>) template.query(sql, new RowMapper<Penalty>(){    
            public Penalty mapRow(ResultSet rs, int row) throws SQLException {    
                Penalty r = new Penalty();    
                r.setSeconds(rs.getInt(1));
                r.setLlpts(rs.getInt(2));
                return r;
            }    
        });
    }
    public League getSeasonForEvent(String id) {
        String sql = "SELECT * FROM Season WHERE SeasonID='" + id + "';";
        return (League) template.queryForObject(sql, new BeanPropertyRowMapper<League>(League.class));
    }
    public Driver getDriver(String id) {
        String sql = "SELECT * FROM Driver WHERE DriverID='" + id + "';";
        return (Driver) template.queryForObject(sql, new BeanPropertyRowMapper<Driver>(Driver.class));
    }
}
