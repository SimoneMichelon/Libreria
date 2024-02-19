package com.Scai.Project.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Scai.Project.entities.Customer;
import com.Scai.Project.entities.Employee;
import com.Scai.Project.entities.Model;
import com.Scai.Project.services.LoginService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/loginPage")
    public String loginPage(HttpSession session) {

        if (session.getAttribute("LOGIN") != null) {
            if (session.getAttribute("LOGIN").toString().equals("OK")) {
                Model model = (Model) session.getAttribute("ENTITY");
                if (model instanceof Customer) {
                    return "redirect:/listCustomers";
                } else if (model instanceof Employee) {
                    return "redirect:/listEmployees";
                }
            }
        }
        return "loginPage.html";
    }

    @PostMapping("/login")
    public String login(@RequestParam Map<String, String> params, HttpSession session) {
        Model model = loginService.findUser(params.get("username"), params.get("password"));

        if (model instanceof Customer) {
            session.setAttribute("LOGIN", "OK");
            session.setAttribute("ROLE", "CUSTOMER");
            session.setAttribute("ENTITY", model);
            return "redirect:/listCustomers";
        } else if (model instanceof Employee) {
            session.setAttribute("LOGIN", "OK");
            session.setAttribute("ROLE", "EMPLOYEE");
            session.setAttribute("ENTITY", model);
            return "redirect:/listEmployees";
        }

        else {
            // LOGIN ERRATO
            return "redirect:/culo.html";
        }
    }

}