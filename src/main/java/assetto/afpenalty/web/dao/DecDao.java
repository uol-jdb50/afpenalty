package assetto.afpenalty.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import assetto.afpenalty.web.model.Decision;

@Repository
public class DecDao {
    JdbcTemplate template;

    @Autowired
    public void setTemplate(JdbcTemplate _template) {
        template = _template;
    }
    public int save(Decision dec) {
        String sql = "INSERT INTO Decision(DecisionID, Text, Variable) VALUES ('" + dec.getDecisionID() + "', '" + dec.getText() + "', " + (dec.getVariable() ? 1 : 0) + ");";
        return template.update(sql);
    }
    public int update(Decision dec) {
        String sql = "UPDATE Decision SET Text='" + dec.getText() + "', Variable=" + (dec.getVariable() ? 1 : 0) + " WHERE DecisionID='" + dec.getDecisionID() + "';";
        return template.update(sql);
    }
    public int delete(String id) {
        String sql = "DELETE FROM Decision WHERE DecisionID='" + id + "';";
        return template.update(sql);
    }
    public Decision getDecById(String id) {
        String sql = "SELECT * FROM Decision WHERE DecisionID='" + id + "';";
        return (Decision) template.queryForObject(sql, new BeanPropertyRowMapper<Decision>(Decision.class));
    }
    public List<Decision> getDecisions() {
        return (List<Decision>) template.query("SELECT * FROM Decision;", new RowMapper<Decision>(){    
            public Decision mapRow(ResultSet rs, int row) throws SQLException {    
                Decision r = new Decision();
                r.setDecisionID(rs.getString(1));
                r.setText(rs.getString(2));
                r.setVariable(rs.getBoolean(3));
                return r;
            }
        });
    }
}
