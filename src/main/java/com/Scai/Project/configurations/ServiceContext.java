package com.Scai.Project.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.Scai.Project.services.AuthorService;
import com.Scai.Project.services.BookService;
import com.Scai.Project.services.CoverService;
import com.Scai.Project.services.CustomerService;
import com.Scai.Project.services.EmployeeService;
import com.Scai.Project.services.LoginService;

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

    @Bean
    public EmployeeService newEmployeeService(){
        return new EmployeeService();
    }

    @Bean
    public CustomerService newCustomerService(){
        return new CustomerService();
    }

    @Bean 
    public CoverService newCoverService(){
        return new CoverService();
    }

    @Bean
    public LoginService newLoginService(){
        return new LoginService();
    }

    
}
