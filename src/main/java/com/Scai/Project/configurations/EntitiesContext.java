package com.Scai.Project.configurations;

import java.sql.Date;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.Scai.Project.entities.Author;
import com.Scai.Project.entities.Book;
import com.Scai.Project.entities.Customer;
import com.Scai.Project.entities.Employee;

@Configuration
public class EntitiesContext {
    
    //COMPLETARE I BEAN
    @Bean
    @Scope("prototype")
    public Author newAuthor(Map<String,String> params){

        
        return new Author(
            Integer.parseInt(params.get("id")),
            params.get("name"),
            params.get("surname"),
            Date.valueOf(params.get("dob")),
            params.get("biography")
        );
    }

    @Bean 
    @Scope("prototype")
    public Book newBook(Map<String,String> params, Author a){
        return new Book(
            Integer.parseInt(params.get("id")),
            params.get("title"),
            params.get("type"),
            Integer.parseInt(params.get("num_pages")),
            Double.parseDouble(params.get("price")), 
            a
        );
    }

    @Bean
    @Scope("prototype")
    public Employee newEmployee(Map<String,String> params){
        return new Employee(
            Integer.parseInt(params.get("idEmployee")),
            params.get("name"),
            params.get("surname"),
            Date.valueOf(params.get("dob")),
            params.get("working_role"),
            params.get("username"),
            params.get("password")
        );
    }

    @Bean
    @Scope("prototype")
    public Customer newCustomer(Map<String,String> params){
        return new Customer(
            Integer.parseInt(params.get("idCustomer")),
            params.get("name"),
            params.get("surname"),
            Date.valueOf(params.get("dob")),
            params.get("username"),
            params.get("password")
        );
    }

}
