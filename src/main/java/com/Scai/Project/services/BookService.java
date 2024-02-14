package com.Scai.Project.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.Scai.Project.dao.BookDAO;
import com.Scai.Project.entities.Author;
import com.Scai.Project.entities.Book;
import com.Scai.Project.entities.Entity;

@Service
public class BookService {

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private ApplicationContext context;

    public BookService() {

    }

    public List<Book> findAll() {
        Map<Integer, Entity> data = bookDAO.read();
        List<Book> ris = new ArrayList<>();

        for (Entity book : data.values()) {
            ris.add((Book) book);
        }

        return ris;
    }

    public Book findById(int id) {
        Map<Integer, Entity> data = bookDAO.read();
        return (Book) data.get(id);
    }

    public List<Book> findByTitle(String title) {
        Map<Integer, Entity> data = bookDAO.read();
        List<Book> ris = new ArrayList<>();

        for (Entity book : data.values()) {
            if (((Book) book).getTitle().equalsIgnoreCase(title)) {
                ris.add((Book) book);
            }
        }

        return ris;
    }

    public List<Book> findByType(String type) {
        Map<Integer, Entity> data = bookDAO.read();
        List<Book> ris = new ArrayList<>();

        for (Entity book : data.values()) {
            if (((Book) book).getType().equalsIgnoreCase(type)) {
                ris.add((Book) book);
            }
        }

        return ris;
    }

    public void insertBook(Map<String, String> params, Author a) {
        Book b = context.getBean(Book.class, params, a);
        bookDAO.create(b);
    }

    public void updateBook(Map<String, String> params, Author a) {
        Book b = context.getBean(Book.class, params, a);
        bookDAO.update(b);
    }

    public void deleteBook(int id) {
        bookDAO.delete(id);
    }
}
