package com.Scai.Project.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.Scai.Project.dao.AuthorDAO;
import com.Scai.Project.entities.Author;
import com.Scai.Project.entities.Entity;

@Service
public class AuthorService {

    @Autowired
    private AuthorDAO authorDAO;

    @Autowired
    private ApplicationContext context;

    public AuthorService(){

    }

    public List<Author> findAll(){
        Map<Integer, Entity> data = authorDAO.read();
        List<Author> lista = new ArrayList<Author>();

        for (Entity author : data.values()) {
            lista.add((Author)author);
        }

        return lista;
    }

    public Author findById(int id){
        Map<Integer,Entity> data = authorDAO.read();
        return (Author)data.get(id);
    }

    public List<Author> findByName(String fullName){
        Map<Integer,Entity> data = authorDAO.read();
        List<Author> ris = new ArrayList<Author>();

        for (Entity author : data.values()) {
            if((((Author)author).getName() + " " + ((Author)author).getSurname()).equalsIgnoreCase(fullName)){
                ris.add((Author)author);
            }
        }
        return ris;
    }

    public void insertAuthor(Map<String,String> params){
        Author a = context.getBean(Author.class, params);
        authorDAO.create(a);
    }

    public void modifyAuthor(Map<String,String> params){
        Author a = context.getBean(Author.class, params);
        authorDAO.update(a);
    }

    public void deleteAuthor(int id){
        authorDAO.delete(id);
    }   
}
