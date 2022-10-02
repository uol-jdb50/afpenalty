package assetto.afpenalty.web.dao;

import assetto.afpenalty.web.model.Regulation;

import java.util.List;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RegDao {

    JdbcTemplate template;

    @Autowired
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public int save(Regulation reg) {
        String sql = "INSERT INTO Violation(ViolationID, Text) VALUES ('" + reg.getViolationID() + "', '" + reg.getText() + "');";
        return template.update(sql);
    }

    public int update(Regulation reg) {    
        String sql="UPDATE Violation set Text='"+reg.getText()+"' where ViolationID='"+reg.getViolationID()+"';";    
        return template.update(sql);    
    }

    public int delete(String id) {
        String sql = "DELETE FROM Violation WHERE ViolationID='" + id + "';";
        return template.update(sql);
    }

    public Regulation getRegById(String id) {
        String sql = "SELECT * FROM Violation WHERE ViolationID='" + id + "';";
        return (Regulation) template.queryForObject(sql, new BeanPropertyRowMapper<Regulation>(Regulation.class));
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
}
