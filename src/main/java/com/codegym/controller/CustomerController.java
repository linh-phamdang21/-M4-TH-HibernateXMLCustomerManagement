package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.repositories.IRepositories;
import com.codegym.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class CustomerController {
    @Autowired
    public IRepositories repositories;

    @GetMapping
    public String getAllCustomer(Model model){
        model.addAttribute("customers", repositories.getAllCustomer());
        return "index";
    }

    @GetMapping("/create")
    public String showForm(Model model){
        model.addAttribute("customer", new Customer());
        return "create";
    }

    @PostMapping("/create")
    public ModelAndView createCustomer(@ModelAttribute("customer") Customer customer){
        ModelAndView modelAndView = new ModelAndView("index");
        repositories.insertCustomer(customer);
        modelAndView.addObject("customers", repositories.getAllCustomer());
        return modelAndView;
    }
}
