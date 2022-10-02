package assetto.afpenalty.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import assetto.afpenalty.web.dao.LicenseDao;
import assetto.afpenalty.web.model.Driver;
import assetto.afpenalty.web.model.DriverComparator;
import assetto.afpenalty.web.model.Event;
import assetto.afpenalty.web.model.Penalty;

@Controller
public class LicenseController {
    
    @Autowired
    LicenseDao dao;

    List<Driver> full;

    @RequestMapping("/viewlicenses")
    public String viewLicenses(Model m) {
        List<Driver> drivers = dao.getAllDrivers();
        List<Event> recent = new ArrayList<>();
        List<Penalty> penalties = new ArrayList<>();
        int pts = 0;
        for (Driver d: drivers) {
            recent = dao.getRecentEvents(d.getDriverID());
            penalties = new ArrayList<>();
            for (Event e: recent) {
                penalties.addAll(dao.getPenaltyForEvent(e.getEventID(), d.getDriverID()));
            }
            pts = 0;
            for (Penalty p: penalties) {
                pts += p.getLlpts();
            }
            d.setLlpts(pts);
        }
        Collections.sort(drivers, new DriverComparator());
        drivers.removeIf((n) -> n.getLlpts() == 0);
        m.addAttribute("drivers", drivers);
        full = drivers;
        return "licenseView";
    }

    @RequestMapping("/viewlicensescache")
    public String viewCached(Model m) {
        m.addAttribute("drivers", full);
        return "licenseView";
    }

    @RequestMapping("/viewattendance/{id}")
    public String viewAttendance(@PathVariable String id, Model m) {
        Driver d = dao.getDriver(id);
        List<Event> recent = dao.getRecentEvents(id);
        int overall = 0;
        for (Event e: recent) {
            e.setSeason(dao.getSeasonForEvent(e.getSeasonID()));
            List<Penalty> pens = dao.getPenaltyForEvent(e.getEventID(), d.getDriverID());
            int pts = 0;
            for (Penalty p: pens) {
                pts += p.getLlpts();
            }
            e.setDriverllpts(pts);
            overall += pts;
        }
        m.addAttribute("driver", d);
        m.addAttribute("events", recent);
        m.addAttribute("points", overall);
        return "oneLicenseView";
    }
}
