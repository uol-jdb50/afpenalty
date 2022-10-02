package assetto.afpenalty.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import assetto.afpenalty.web.dao.LeagueDao;
import assetto.afpenalty.web.model.League;

@Controller
public class LeagueController {
    @Autowired
    LeagueDao dao;

    @RequestMapping("/viewleagues")
    public String viewLeagues(Model m) {
        List<League> leagues = dao.getLeagues();
        m.addAttribute("list", leagues);
        return "leaguesView";
    }

    @RequestMapping("/leagueform")
    public String showForm(Model m) {
        m.addAttribute("command", new League());
        return "leaguesAdd";
    }

    @RequestMapping(value="/saveleague", method=RequestMethod.POST)
    public String save(@ModelAttribute("league") League league) {
        league.setSeasonID();
        dao.save(league);
        return "redirect:/viewleagues";
    }

    @RequestMapping(value="/editleague/{id}")
    public String edit(@PathVariable String id, Model m) {
        League league = dao.getLeagueById(id);
        m.addAttribute("command", league);
        return "leaguesEdit";
    }

    @RequestMapping(value="/editsaveleague", method=RequestMethod.POST)
    public String editsave(@ModelAttribute("league") League league) {
        dao.update(league);
        return "redirect:/viewleagues";
    }

    @RequestMapping(value="/deleteleague/{id}")
    public String delete(@PathVariable String id) {
        dao.delete(id);
        return "redirect:/viewleagues";
    }
}
