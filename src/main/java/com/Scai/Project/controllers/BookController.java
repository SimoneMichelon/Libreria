package com.Scai.Project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Scai.Project.entities.Book;
import com.Scai.Project.entities.Cover;
import com.Scai.Project.services.BookService;
import com.Scai.Project.services.CoverService;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    public CoverService coverService;

    @GetMapping("/listBooks")
    public String listBooks(Model model) {
        List<Book> bookList = bookService.findAll();
        List<Cover> coverList = coverService.findAll();
        model.addAttribute("books",bookList);
        model.addAttribute("covers",coverList);
        return "listBooks.html";
    }

}
