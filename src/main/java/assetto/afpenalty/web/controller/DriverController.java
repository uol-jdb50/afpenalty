package assetto.afpenalty.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import assetto.afpenalty.web.dao.DriverDao;
import assetto.afpenalty.web.model.Driver;

@Controller
public class DriverController {
    @Autowired
    DriverDao dao;

    @RequestMapping("/viewdrivers")
    public String viewDecs(Model m) {
        List<Driver> drivers = dao.getDrivers();
        m.addAttribute("list", drivers);
        return "driversView";
    }

    @RequestMapping("/driverform")
    public String showForm(Model m) {
        m.addAttribute("command", new Driver());
        return "driversAdd";
    }

    @RequestMapping(value="/savedriver", method=RequestMethod.POST)
    public String save(@ModelAttribute("driver") Driver driver) {
        driver.setDriverID();
        dao.save(driver);
        return "redirect:/viewdrivers";
    }

    @RequestMapping(value="/editdriver/{id}")
    public String edit(@PathVariable String id, Model m) {
        Driver driver = dao.getDecById(id);
        m.addAttribute("command", driver);
        return "driversEdit";
    }

    @RequestMapping(value="/editsavedriver", method=RequestMethod.POST)
    public String editsave(@ModelAttribute("driver") Driver driver) {
        dao.update(driver);
        return "redirect:/viewdrivers";
    }

    @RequestMapping(value="/deletedriver/{id}")
    public String delete(@PathVariable String id) {
        dao.delete(id);
        return "redirect:/viewdrivers";
    }
}
