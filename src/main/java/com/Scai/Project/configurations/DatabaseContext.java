package com.Scai.Project.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.Scai.Project.dao.AuthorDAO;
import com.Scai.Project.dao.BookDAO;
import com.Scai.Project.dao.Database;

@Configuration
public class DatabaseContext {

    @Bean
    public Database newDatabase(){
        return new Database("library");
    }

    @Bean
    public BookDAO newBookDAO(){
        return new BookDAO();
    }

    @Bean
    public AuthorDAO newAuthorDAO(){
        return new AuthorDAO();
    }




    
}
