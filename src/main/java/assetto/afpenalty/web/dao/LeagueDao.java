package assetto.afpenalty.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import assetto.afpenalty.web.model.League;

@Repository
public class LeagueDao {
    JdbcTemplate template;

    @Autowired
    public void setTemplate(JdbcTemplate _template) {
        template = _template;
    }
    public int save(League leag) {
        String sql = "INSERT INTO Season(SeasonID, Name) VALUES ('" + leag.getSeasonID() + "', '" + leag.getName() + "');";
        return template.update(sql);
    }
    public int update(League leag) {
        String sql = "UPDATE Season SET Name='" + leag.getName() + "' WHERE SeasonID='" + leag.getSeasonID() + "';";
        return template.update(sql);
    }
    public int delete(String id) {
        String sql = "DELETE FROM Season WHERE SeasonID='" + id + "';";
        return template.update(sql);
    }
    public League getLeagueById(String id) {
        String sql = "SELECT * FROM Season WHERE SeasonID='" + id + "';";
        return (League) template.queryForObject(sql, new BeanPropertyRowMapper<League>(League.class));
    }
    public List<League> getLeagues() {
        return (List<League>) template.query("SELECT * FROM Season ORDER BY Name ASC;", new RowMapper<League>(){    
            public League mapRow(ResultSet rs, int row) throws SQLException {    
                League r = new League();    
                r.setSeasonID(rs.getString(1));
                r.setName(rs.getString(2));
                return r;
            }    
        });
    }
}
