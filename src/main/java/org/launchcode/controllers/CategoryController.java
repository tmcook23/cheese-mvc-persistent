package org.launchcode.controllers;

import org.launchcode.models.Category;
import org.launchcode.models.data.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryDao categoryDao;


    // Request path: /category
    // OLD: @RequestMapping(value = "", method = RequestMethod.GET) // display of the form
    // NEW:
    @RequestMapping(value = "") // display of the form
    public String index(Model model) {

        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("title", "Categories");

        return "category/index"; // just the name of the template, not the extension
    }

    // Request path: /category/add
    @RequestMapping(value = "add", method = RequestMethod.GET) // display of the form
    public String displayAddCategoryForm(Model model) {

        model.addAttribute("title", "Add Category");
        model.addAttribute(new Category()); // name of the class is by default the name of the view (in all lowercase)
        // model.addAttribute("categoryTypes", CategoryType.values()); // gets the list of all types
        return "category/add";
    }

    // Request path: cheese/add
    @RequestMapping(value = "add", method = RequestMethod.POST) // processing of the form
    public String add(Model model,
                      @ModelAttribute @Valid Category category,
                      Errors errors) { // needs to match name in the form

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Category");
            return "category/add";
        }

        categoryDao.save(category); // to save entity

        // Redirect to category/
        return "redirect:";
    }

}
