package assetto.afpenalty.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import assetto.afpenalty.web.model.Regulation;
import assetto.afpenalty.web.dao.RegDao;

import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegulationController {
    
    @Autowired
    RegDao dao;

    @RequestMapping("/viewregs")
    public String viewRegs(Model m) {
        List<Regulation> regs = dao.getRegulations();
        m.addAttribute("list", regs);
        return "regsView";
    }

    @RequestMapping("/regform")
    public String showForm(Model m) {
        m.addAttribute("command", new Regulation());
        return "regsAdd";
    }

    @RequestMapping(value="/savereg", method = RequestMethod.POST)
    public String save(@ModelAttribute("reg") Regulation regulation) {
        regulation.setViolationID();
        dao.save(regulation);
        return "redirect:/viewregs";
    }

    @RequestMapping(value="/editreg/{id}")
    public String edit(@PathVariable String id, Model m) {
        Regulation reg = dao.getRegById(id);
        m.addAttribute("command", reg);
        return "regsEdit";
    }

    @RequestMapping(value="/editsavereg", method=RequestMethod.POST)
    public String editsave(@ModelAttribute("reg") Regulation regulation) {
        dao.update(regulation);
        return "redirect:/viewregs";
    }

    @RequestMapping(value="/deletereg/{id}", method=RequestMethod.GET)
    public String delete(@PathVariable String id) {
        dao.delete(id);
        return "redirect:/viewregs";
    }

}
