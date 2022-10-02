package assetto.afpenalty.web.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Driver {
    private String driverID;
    private String name;
    private boolean hasll;
    private Date licensedate;
    private String guid;

    private List<Event> recent;
    private int llpts;

    public Driver() {
        recent = new ArrayList<>();
    }
    public Driver(String _name, String _guid) {
        name = _name;
        guid = _guid;
        recent = new ArrayList<>();
    }
    public Driver(String _id, String _name, boolean _hasll, Date _licensedate) {
        driverID = _id;
        name = _name;
        hasll = _hasll;
        licensedate = _licensedate;
        recent = new ArrayList<>();
    }
    public Driver(String _id, String _name, boolean _hasll, Date _licensedate, String _guid) {
        driverID = _id;
        name = _name;
        hasll = _hasll;
        licensedate = _licensedate;
        guid = _guid;
        recent = new ArrayList<>();
    }
    public String getDriverID() {
        return driverID;
    }
    public String getName() {
        return name;
    }
    public boolean getHasll() {
        return hasll;
    }
    public Date getLicensedate() {
        return licensedate;
    }
    public String getGuid() {
        return guid;
    }
    public int getLlpts() {
        return llpts;
    }
    public List<Event> getEvents() {
        return recent;
    }
    public void setDriverID() {
        driverID = UUID.randomUUID().toString();
    }
    public void setDriverID(String _id) {
        if (_id == null) return;
        driverID = _id;
    }
    public void setName(String _name) {
        if (_name == null) return;
        name = _name;
    }
    public void setHasll(boolean _hasll) {
        hasll = _hasll;
    }
    public void setLicensedate(Date _licensedate) {
        licensedate = _licensedate;
    }
    public void setGuid(String _guid) {
        if (_guid == null) return;
        guid = _guid;
    }
    public void setLlpts(int _llpts) {
        llpts = _llpts;
    }
    public void addEvent(Event event) {
        recent.add(event);
    }
    public void setEvents(List<Event> _recent) {
        recent = _recent;
    }
}
