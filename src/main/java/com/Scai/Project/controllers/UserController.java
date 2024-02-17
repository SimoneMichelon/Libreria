package com.Scai.Project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Scai.Project.entities.Customer;
import com.Scai.Project.entities.Employee;
import com.Scai.Project.services.CustomerService;
import com.Scai.Project.services.EmployeeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/listEmployees")
    public String employeeArea(HttpSession session, Model model) {
        if ((session.getAttribute("LOGIN")) != null) {
            if (session.getAttribute("LOGIN").toString().equals("OK")) {
                Employee employee = (Employee) session.getAttribute("ENTITY");
                List<Employee> employeeList = employeeService.findAll();
                model.addAttribute("employees", employeeList);
                model.addAttribute("customer", employee);
                return "listEmployees.html";

            }
        } else {
            model.addAttribute("alert", "Non sei autenticato");
            return "redirect:/loginPage";
        }

        return "redirect:/loginPage";
    }

    @GetMapping("/listCustomers")
    public String customerArea(HttpSession session, Model model) {
        if (session.getAttribute("LOGIN") != null) {
            if (session.getAttribute("LOGIN").toString().equals("OK")) {
                Customer customer = (Customer) session.getAttribute("ENTITY");
                List<Customer> customerList = customerService.findAll();
                model.addAttribute("customers", customerList);
                model.addAttribute("customer", customer);
                return "listCustomers.html";
            }
        } else {

            return "redirect:/loginPage";
        }
        return "redirect:/loginPage";
    }

}