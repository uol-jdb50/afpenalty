package assetto.afpenalty.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import assetto.afpenalty.web.model.Event;

@Repository
public class EventDao {
    JdbcTemplate template;

    @Autowired
    public void setTemplate(JdbcTemplate _template) {
        template = _template;
    }
    public int save(Event eve) {
        String sql = "INSERT INTO Event(EventID, SeasonID, Name, Date) VALUES ('" + eve.getEventID() + "', '" + eve.getSeasonID() + "', '" + eve.getName() + "', '" + eve.getDate() + "');";
        return template.update(sql);
    }
    public int update(Event eve) {
        String sql = "UPDATE Event SET Name='" + eve.getName() + "', Date='" + eve.getDate() + "' WHERE EventID='" + eve.getEventID() + "';";
        return template.update(sql);
    }
    public int delete(String id) {
        String sql = "DELETE FROM Event WHERE EventID='" + id + "';";
        return template.update(sql);
    }
    public Event getEveById(String id) {
        String sql = "SELECT * FROM Event WHERE EventID='" + id + "';";
        return (Event) template.queryForObject(sql, new BeanPropertyRowMapper<Event>(Event.class));
    }
    public List<Event> getEvents(String leagueId) {
        return (List<Event>) template.query("SELECT * FROM Event WHERE SeasonID='" + leagueId + "' ORDER BY Date ASC;", new RowMapper<Event>(){    
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
}
