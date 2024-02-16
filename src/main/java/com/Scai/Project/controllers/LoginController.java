package com.Scai.Project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.Scai.Project.services.LoginService;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;
    

}