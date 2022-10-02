package assetto.afpenalty.web.model;

import java.util.UUID;

public class Regulation { //Violation in DB
    private String violationID;
    private String text;

    public Regulation() {
        
    }
    public Regulation(String _id, String _text) {
        violationID = _id;
        text = _text;
    }

    public String getViolationID() {
        return violationID;
    }

    public String getText() {
        return text;
    }

    public void setViolationID(String _id) {
        violationID = _id;
    }

    public void setViolationID() {
        violationID = UUID.randomUUID().toString();
    }

    public void setText(String _text) {
        text = _text;
    }
}
