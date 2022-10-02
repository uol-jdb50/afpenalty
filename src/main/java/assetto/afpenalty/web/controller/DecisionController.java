package assetto.afpenalty.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import assetto.afpenalty.web.dao.DecDao;
import assetto.afpenalty.web.model.Decision;

@Controller
public class DecisionController {
    
    @Autowired
    DecDao dao;

    @RequestMapping("/viewdecs")
    public String viewDecs(Model m) {
        List<Decision> decs = dao.getDecisions();
        m.addAttribute("list", decs);
        return "decsView";
    }

    @RequestMapping("/decform")
    public String showForm(Model m) {
        m.addAttribute("command", new Decision());
        return "decsAdd";
    }

    @RequestMapping(value="/savedec", method=RequestMethod.POST)
    public String save(@ModelAttribute("dec") Decision decision) {
        decision.setDecisionID();
        dao.save(decision);
        return "redirect:/viewdecs";
    }

    @RequestMapping(value="/editdec/{id}")
    public String edit(@PathVariable String id, Model m) {
        Decision dec = dao.getDecById(id);
        m.addAttribute("command", dec);
        return "decsEdit";
    }

    @RequestMapping(value="/editsavedec", method=RequestMethod.POST)
    public String editsave(@ModelAttribute("dec") Decision decision) {
        dao.update(decision);
        return "redirect:/viewdecs";
    }

    @RequestMapping(value="/deletedec/{id}")
    public String delete(@PathVariable String id) {
        dao.delete(id);
        return "redirect:/viewdecs";
    }
}
