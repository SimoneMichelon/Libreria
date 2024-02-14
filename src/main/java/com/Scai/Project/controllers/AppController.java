package com.Scai.Project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/home")
    public String home() {
        return "home.html";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/home";
    }
}
