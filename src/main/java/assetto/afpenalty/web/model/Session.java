package assetto.afpenalty.web.model;

import java.util.UUID;

public class Session {
    private String sessionID;
    private String eventID;
    private String name;

    public Session() {

    }
    public Session(String _eventID) {
        eventID = _eventID;
    }
    public Session(String _id, String _eventID, String _name) {
        sessionID = _id;
        eventID = _eventID;
        name = _name;
    }
    public String getSessionID() {
        return sessionID;
    }
    public String getEventID() {
        return eventID;
    }
    public String getName() {
        return name;
    }
    public void setSessionID() {
        sessionID = UUID.randomUUID().toString();
    }
    public void setSessionID(String _id) {
        sessionID = _id;
    }
    public void setEventID(String _id) {
        eventID = _id;
    }
    public void setName(String _name) {
        name = _name;
    }
}
