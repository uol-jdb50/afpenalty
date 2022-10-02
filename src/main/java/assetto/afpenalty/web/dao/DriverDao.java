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

@Repository
public class DriverDao {
    JdbcTemplate template;

    @Autowired
    public void setTemplate(JdbcTemplate _template) {
        template = _template;
    }
    public int save(Driver d) {
        String sql = "INSERT INTO Driver(DriverID, Name, HasLL, LicenseDate) VALUES ('" + d.getDriverID() + "', '" + d.getName() + "', " + (d.getHasll() ? 1 : 0) + ", '" + (d.getLicensedate().toString()) + "');";
        return template.update(sql);
    }
    public int update(Driver dec) {
        String sql = "UPDATE Driver SET Name='" + dec.getName() + "', HasLL=" + (dec.getHasll() ? 1 : 0) + ", LicenseDate='" + dec.getLicensedate() + "' WHERE DriverID='" + dec.getDriverID() + "';";
        return template.update(sql);
    }
    public int delete(String id) {
        String sql = "DELETE FROM Driver WHERE DriverID='" + id + "';";
        return template.update(sql);
    }
    public Driver getDecById(String id) {
        String sql = "SELECT * FROM Driver WHERE DriverID='" + id + "';";
        return (Driver) template.queryForObject(sql, new BeanPropertyRowMapper<Driver>(Driver.class));
    }
    public List<Driver> getDrivers() {
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
}
