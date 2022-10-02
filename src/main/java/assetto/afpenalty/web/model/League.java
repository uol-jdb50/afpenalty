package assetto.afpenalty.web.model;

import java.util.UUID;

public class League {
    private String seasonID;
    private String name;

    public League() {

    }
    public League(String _id, String _name) {
        seasonID = _id;
        name = _name;
    }
    public String getSeasonID() {
        return seasonID;
    }
    public String getName() {
        return name;
    }
    public void setSeasonID() {
        seasonID = UUID.randomUUID().toString();
    }
    public void setSeasonID(String _id) {
        seasonID = _id;
    }
    public void setName(String _name) {
        name = _name;
    }
}
