package assetto.afpenalty.web.model;

import java.util.Comparator;

public class DriverComparator implements Comparator<Driver> {
    @Override
    public int compare(Driver o1, Driver o2) {
        return o2.getLlpts() - o1.getLlpts();
    }
}
