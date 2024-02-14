package com.Scai.Project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Scai.Project.entities.Author;
import com.Scai.Project.services.AuthorService;

@Controller
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/listAuthors")
    public String listAuthors(Model model) {
        List<Author> authorsList = authorService.findAll();
        model.addAttribute("authors", authorsList);
        return "listAuthors.html";
    }
}
