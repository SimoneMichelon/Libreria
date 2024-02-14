package com.Scai.Project.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.Scai.Project.services.AuthorService;
import com.Scai.Project.services.BookService;

@Configuration
public class ServiceContext {
    
    @Bean
    public AuthorService newAuthorService(){
        return new AuthorService();
    }

    @Bean
    public BookService newBookService(){
        return new BookService();
    }
}
