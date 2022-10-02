package assetto.afpenalty.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import assetto.afpenalty.web.dao.EventDao;
import assetto.afpenalty.web.model.Event;

@Controller
public class EventController {
    @Autowired
    EventDao dao;

    String seasonId;

    @RequestMapping("/viewevents/{id}")
    public String viewEvents(@PathVariable String id, Model m) {
        List<Event> events = dao.getEvents(id);
        m.addAttribute("list", events);
        m.addAttribute("seasonId", id);
        seasonId = id;
        return "eventsView";
    }

    @RequestMapping(value="/viewevents/eventform/{id}")
    public String save(@PathVariable String id, Model m) {
        m.addAttribute("command", new Event(id));
        m.addAttribute("leagueID", id);
        return "eventsAdd";
    }

    @RequestMapping(value="/saveevent", method=RequestMethod.POST)
    public String save(@ModelAttribute("event") Event event) {
        event.setEventID();
        event.setSeasonID(seasonId);
        dao.save(event);
        return "redirect:/viewevents/" + event.getSeasonID();
    }

    @RequestMapping(value="/viewevents/editevent/{id}")
    public String edit(@PathVariable String id, Model m) {
        Event e = dao.getEveById(id);
        m.addAttribute("leagueID", e.getSeasonID());
        m.addAttribute("command", e);
        return "eventsEdit";
    }

    @RequestMapping(value="/editsaveevent", method = RequestMethod.POST)
    public String editsave(@ModelAttribute("event") Event event) {
        dao.update(event);
        return "redirect:/viewevents/" + seasonId;
    }

    @RequestMapping(value="/viewevents/deleteevent/{id}")
    public String delete(@PathVariable String id) {
        dao.delete(id);
        return "redirect:/viewevents/" + seasonId;
    }
}
