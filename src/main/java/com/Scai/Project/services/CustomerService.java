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

    public Customer findById(int id){
        Map<Integer,Entity> data = customerDAO.read();
        return (Customer)data.get(id);
    }

    public List<Customer> findByFullName(String fullname){
        Map<Integer,Entity> data = customerDAO.read();
        List<Customer> ris = new ArrayList<>();

        for (Entity customer : data.values()) {
            if((((Customer)customer).getName() + " " + ((Customer)customer).getSurname()).equalsIgnoreCase(fullname)){
                ris.add((Customer)customer);
            }
        }
        return ris;
    }

    public void updateCustomer(Map<String,String> params){
        Customer customer = context.getBean(Customer.class,params);
        customerDAO.update(customer);;
    }
    
    public void insertCustomer(Map<String,String> params){
        Customer customer = context.getBean(Customer.class,params);
        customerDAO.create(customer);
    }

    public void deleteCustomer(int id){
        customerDAO.delete(id);
    }
    
}
