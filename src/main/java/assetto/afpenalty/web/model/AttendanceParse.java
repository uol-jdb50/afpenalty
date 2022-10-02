package assetto.afpenalty.web.model;

import java.util.ArrayList;
import java.util.List;

import org.json.*;

public class AttendanceParse {
    public static List<Driver> parseAttendance(String str) throws JSONException {
        List<Driver> drivers = new ArrayList<>();
        JSONObject obj = new JSONObject(str);
        JSONArray cars = obj.getJSONArray("Result");

        for (int i = 0; i < cars.length(); i++) {
            JSONObject curr = cars.getJSONObject(i);
            String veh = curr.getString("CarModel");
            if (!veh.equals("mixx_corvette_c7_stingray")) {
                drivers.add(new Driver(curr.getString("DriverName"), curr.getString("DriverGuid")));
            }
        }
        return drivers;
    }

    public static List<Driver> splitDriversFound(List<Driver> drivers) {
        List<Driver> found = new ArrayList<>();
        for (Driver d: drivers) {
            if (d.getDriverID() != null) {
                found.add(d);
            }
        }
        return found;
    }

    public static List<Driver> splitDriversNotFound(List<Driver> drivers) {
        List<Driver> notfound = new ArrayList<>();
        for (Driver d: drivers) {
            if (d.getDriverID() == null) {
                notfound.add(d);
            }
        }
        return notfound;
    }
}
