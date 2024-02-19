package com.Scai.Project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Scai.Project.entities.Customer;
import com.Scai.Project.services.CustomerService;

import jakarta.servlet.http.HttpSession;


// @Controller
public class CustomerController {

    // @Autowired
    // private CustomerService customerService;

    // @GetMapping("/listCustomers")
    // public String getMethodName(HttpSession session,Model model) {
    //     if(session.getAttribute("LOGIN")!=null)
    //     {
    //         if(session.getAttribute("LOGIN").toString().equals("OK"))
    //         {
    //             Customer customer = (Customer) session.getAttribute("ENTITY");
    //             List<Customer> customerList = customerService.findAll();
    //             model.addAttribute("customers", customerList);
    //             model.addAttribute("customer", customer);
    //             return "listCustomers.html";
    //         }
    //     }
    //     else
    //     {
    //         model.addAttribute("alert","Simone cedadawdjewiuhwifh ewiucha");
    //         return "redirect:/loginPage";
    //     }
    //     return "redirect:/loginPage";
    // }
}
