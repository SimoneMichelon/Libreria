package com.Scai.Project.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.Scai.Project.dao.CustomerDAO;
import com.Scai.Project.entities.Customer;
import com.Scai.Project.entities.Entity;

@Service
public class CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private ApplicationContext context;

    public CustomerService() {

    }

    public List<Customer> findAll() {
        Map<Integer, Entity> data = customerDAO.read();
        List<Customer> ris = new ArrayList<>();

        for (Entity customer : data.values()) {
            ris.add((Customer) customer);
        }

        return ris;
    }
    
}
