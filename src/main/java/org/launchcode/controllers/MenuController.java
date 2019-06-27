package org.launchcode.controllers;

import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;


@Controller
@RequestMapping(value = "menu")
public class MenuController {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private CheeseDao cheeseDao;


    // Request path: /
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("menus", menuDao.findAll());
        model.addAttribute("title", "Menus");

        return "menu/index";
    }


    // Request path: menu/add
    @RequestMapping(value = "add", method = RequestMethod.GET) // display of the form
    public String displayAddMenuForm(Model model) {

        model.addAttribute("title", "Add Menu");
        model.addAttribute(new Menu()); // name of the class is by default the name of the view (in all lowercase)
        // model.addAttribute("menuTypes", MenuType.values()); // gets the list of all types
        return "menu/add";
    }


    // Request path: menu/add
    @RequestMapping(value = "add", method = RequestMethod.POST) // processing of the form
    public String processAddMenuForm(@ModelAttribute @Valid Menu newMenu, Errors errors, Model model) { // needs to match name in the form

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Menu");
            return "menu/add";
        }

        menuDao.save(newMenu); // to save entity

        // Redirect
        return "redirect:view/" + newMenu.getId(); // must be "newMenu" not "menu" because it must match the object name declared in the method
    }

    @RequestMapping(value = "view/{menuId}", method = RequestMethod.GET)
    public String viewMenu(Model model, @PathVariable int menuId) {
        Menu menuToLoad = menuDao.findOne(menuId);
        model.addAttribute("menu", menuToLoad);
        return "menu/view";
    }

}
