package org.launchcode.controllers;

import org.launchcode.models.Category;
import org.launchcode.models.data.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryDao categoryDao;


    // Request path: /category
    @RequestMapping(value = "", method = RequestMethod.GET) // display of the form
    public String index(Model model) {

        model.addAttribute("categoryDao", categoryDao.findAll());
        model.addAttribute("title", "Categories");

        return "category/index"; // just the name of the template, not the extension
    }

}
