package com.Scai.Project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Scai.Project.entities.Employee;
import com.Scai.Project.services.EmployeeService;

import jakarta.servlet.http.HttpSession;


@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // @GetMapping("/listEmployees")
    // public String getMethodName(Model model) {
    //     List<Employee> employeeList = employeeService.findAll();
    //     model.addAttribute("employees", employeeList);
    //     return "listEmployees.html";
    // }

    @GetMapping("/listEmployees")
    public String getMethodName(HttpSession session, Model model) {
        if((session.getAttribute("LOGIN")) != null)
        {   
            if(session.getAttribute("LOGIN").toString().equals("OK"))
            {
                Employee employee = (Employee) session.getAttribute("ENTITY");
                List<Employee> employeeList = employeeService.findAll();
                model.addAttribute("employees", employeeList);
                model.addAttribute("customer", employee);
                return "listEmployees.html";
                
            }
        }
        else
        {
            model.addAttribute("alert", "Non sei autenticato");
            return "redirect:/loginPage";
        }

        return "redirect:/loginPage";
    }
    

}
