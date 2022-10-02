package assetto.afpenalty.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import assetto.afpenalty.web.dao.ReportDao;
import assetto.afpenalty.web.model.Attendance;
import assetto.afpenalty.web.model.AttendanceParse;
import assetto.afpenalty.web.model.Driver;
import assetto.afpenalty.web.model.Penalty;
import assetto.afpenalty.web.model.PenaltyPre;
import assetto.afpenalty.web.model.Report;
import assetto.afpenalty.web.model.ReportPre;
import assetto.afpenalty.web.model.Session;

@Controller
public class ReportController {
    
    @Autowired
    ReportDao dao;
    
    String eventid;
    String seasonid;

    String reportid;

    List<Driver> founddrivers;
    List<Driver> newdrivers;

    List<Session> sessions;
    List<Driver> attendance;

    @RequestMapping("/viewreports/{id}")
    public String viewReports(@PathVariable String id, Model m) {
        eventid = id;
        List<Driver> allDrivers = dao.getAllDrivers();
        List<Report> reps = dao.getReports(id);
        sessions = dao.getSessions(id);
        attendance = dao.getAttendance(id);
        seasonid = dao.getSeasonIdFromEventId(eventid);
        m.addAttribute("season", seasonid);
        m.addAttribute("event", eventid);
        m.addAttribute("sessions", sessions);
        m.addAttribute("alldrivers", allDrivers);
        m.addAttribute("list", reps);
        m.addAttribute("attend", attendance);
        m.addAttribute("command", new Driver());
        return "reportsView";
    }

    @RequestMapping("/viewreports/reportform/{id}")
    public String showForm(@PathVariable String id, Model m) {
        m.addAttribute("command", new ReportPre());
        m.addAttribute("sessions", sessions);
        return "reportsAdd";
    }

    @RequestMapping(value="/savereport", method=RequestMethod.POST)
    public String save(@ModelAttribute("report") ReportPre report) {
        report.setReportID();
        dao.save(report, eventid);
        return "redirect:/viewreports/" + eventid;
    }

    @RequestMapping(value="/editreport/{id}")
    public String edit(@PathVariable String id, Model m) {
        Report r = dao.getRepById(id);
        m.addAttribute("command", r);
        return "reportsEdit";
    }

    @RequestMapping(value="/editsavereport", method=RequestMethod.POST)
    public String editsave(@ModelAttribute("report") Report report) {
        dao.update(report);
        return "redirect:/viewreports/" + eventid;
    }

    @RequestMapping(value="/deletereport/{id}")
    public String delete(@PathVariable String id) {
        dao.delete(id);
        return "redirect:/viewreports/" + eventid;
    }

    @RequestMapping(value="/judge/{id}")
    public String viewSingleReport(@PathVariable String id, Model m) {
        Report rep = dao.getRepById(id);
        reportid = id;
        rep.setSession(dao.getSesForRep(id));
        List<Driver> drivers = dao.getDriversForReport(id);
        List<Penalty> penalties = dao.getPenaltiesForReport(id);
        //List<Appeal> appeals;
        m.addAttribute("event", eventid);
        m.addAttribute("report", rep);
        m.addAttribute("driversinv", drivers);
        m.addAttribute("penalties", penalties);
        return "onereportView";
    }

    @RequestMapping(value="/viewreports/uploadattend/{id}")
    public String uploadAttendanceFile(@PathVariable String id, Model m) {
        m.addAttribute("command", new Attendance());
        m.addAttribute("event", eventid);
        return "attendanceUpload";
    }

    @RequestMapping(value="/uploadattendsave", method=RequestMethod.POST)
    public String uploadFile(@ModelAttribute("file") Attendance att, Model m) {
        List<Driver> drivers = new ArrayList<>();
        try {
            drivers = AttendanceParse.parseAttendance(att.getFile());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        drivers = dao.fillDriverList(drivers);
        founddrivers = AttendanceParse.splitDriversFound(drivers);
        newdrivers = AttendanceParse.splitDriversNotFound(drivers);
        return "redirect:/viewreports/verifyattend";
    }

    @RequestMapping(value="/viewreports/verifyattend")
    public String verifyAttend(Model m) {
        m.addAttribute("event", eventid);
        m.addAttribute("attendfound", founddrivers);
        m.addAttribute("attendnotfound", newdrivers);
        return "attendanceVerify";
    }

    @RequestMapping(value="/editattend/{guid}")
    public String editAttend(@PathVariable String guid, Model m) {
        for (Driver d: newdrivers) {
            if (d.getGuid().equals(guid)) {
                m.addAttribute("command", d);
            }
        }
        m.addAttribute("event", eventid);
        return "editAttend";
    }

    @RequestMapping(value="/editsaveattend", method=RequestMethod.POST)
    public String editsaveAttend(@ModelAttribute("driver") Driver driver, Model m) {
        Driver d = dao.fillDriver(driver);
        if (d.getDriverID() != null) {
            founddrivers.add(d);
            newdrivers.removeIf((n) -> n.getGuid().equals(d.getGuid()));
        }
        m.addAttribute("event", eventid);
        return "redirect:/viewreports/verifyattend";
    }

    @RequestMapping(value="/confirmattend")
    public String confirmAttend(Model m) {
        dao.addNewDrivers(newdrivers);
        dao.updateDrivers(founddrivers);
        dao.addAttendance(newdrivers, eventid);
        dao.addAttendance(founddrivers, eventid);
        return "redirect:/viewreports/" + eventid;
    }

    @RequestMapping(value="/viewreports/addoneattend/{id}")
    public String addDriverToAttend(@PathVariable String id, Model m) {
        m.addAttribute("command", new Driver());
        m.addAttribute("drivers", dao.getAllDrivers());
        m.addAttribute("event", eventid);
        return "attendOneEdit";
    }

    @RequestMapping(value="/editsaveoneattend", method=RequestMethod.POST)
    public String addAttend(@ModelAttribute("driver") Driver driver, Model m) {
        List<Driver> d = new ArrayList<>();
        d.add(driver);
        dao.addAttendance(d, eventid);
        return "redirect:/viewreports/" + eventid;
    }

    @RequestMapping(value="/removeattend/{id}")
    public String removeAttend(@PathVariable String id, Model m) {
        dao.deleteAttendance(id, eventid);
        return "redirect:/viewreports/" + eventid;
    }

    @RequestMapping(value="/viewreports/sessionform/{id}")
    public String showSessionForm(@PathVariable String id, Model m) {
        m.addAttribute("command", new Session(id));
        return "sessionAdd";
    }

    @RequestMapping(value="/savesession", method=RequestMethod.POST)
    public String saveSession(@ModelAttribute("session") Session session) {
        session.setSessionID();
        session.setEventID(eventid);
        dao.saveSession(session);
        return "redirect:/viewreports/" + eventid;
    }

    @RequestMapping(value="/viewreports/editsession/{id}")
    public String editSession(@PathVariable String id, Model m) {
        Session r = dao.getSesById(id);
        m.addAttribute("command", r);
        return "sessionEdit";
    }

    @RequestMapping(value="/saveeditsession", method=RequestMethod.POST)
    public String editsaveSession(@ModelAttribute("session") Session session) {
        dao.updateSession(session);
        return "redirect:/viewreports/" + eventid;
    }

    @RequestMapping(value="/deletesession/{id}")
    public String deleteSession(@PathVariable String id) {
        dao.deleteSession(id);
        return "redirect:/viewreports/" + eventid;
    }

    @RequestMapping(value="/judge/addinvolve")
    public String addInvolve(Model m) {
        m.addAttribute("command", new Driver());
        m.addAttribute("attend", attendance);
        m.addAttribute("reportID", reportid);
        return "involveAdd";
    }

    @RequestMapping(value="/saveinvolve", method=RequestMethod.POST)
    public String saveInvolve(@ModelAttribute("driver") Driver driver) {
        Driver d = dao.getDriverByID(driver.getDriverID());
        dao.saveInvolve(d, reportid);
        return "redirect:/judge/" + reportid;
    }

    @RequestMapping(value="/removeinvolve/{id}")
    public String deleteInvolve(@PathVariable String id, Model m) {
        dao.deleteInvolve(id, reportid);
        return "redirect:/judge/" + reportid;
    }

    @RequestMapping(value="/judge/addpenalty")
    public String addPenalty(Model m) {
        m.addAttribute("command", new Penalty());
        m.addAttribute("drivers", dao.getDriversForReport(reportid));
        m.addAttribute("viols", dao.getRegulations());
        m.addAttribute("decs", dao.getDecisions());
        return "penaltyAdd";
    }

    @RequestMapping(value="/savepenalty", method=RequestMethod.POST)
    public String savePenalty(@ModelAttribute("penalty") PenaltyPre penalty) {
        penalty.setPenaltyID();
        penalty.setEventID(eventid);
        dao.savePenalty(penalty, reportid);
        return "redirect:/judge/" + reportid;
    }

    @RequestMapping(value="/judge/editpenalty/{id}")
    public String editPenalty(@PathVariable String id, Model m) {
        PenaltyPre r = dao.getPenById(id);
        m.addAttribute("command", r);
        return "penaltyEdit";
    }

    @RequestMapping(value="/saveeditpenalty", method=RequestMethod.POST)
    public String editsavePenalty(@ModelAttribute("session") PenaltyPre session) {
        dao.updatePenalty(session);
        return "redirect:/viewreports/" + eventid;
    }

    @RequestMapping(value="/judge/deletepenalty/{id}")
    public String deletePenalty(@PathVariable String id, Model m) {
        dao.deletePenalty(id);
        return "redirect:/judge/" + reportid;
    }

}
