package com.Scai.Project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Scai.Project.entities.Customer;
import com.Scai.Project.services.CustomerService;


@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/listCustomers")
    public String getMethodName(Model model) {
        List<Customer> customerList = customerService.findAll();
        model.addAttribute("customers", customerList);
        return "listCustomers.html";
    }
}
