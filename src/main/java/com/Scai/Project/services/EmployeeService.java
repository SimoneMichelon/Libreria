package com.Scai.Project.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.Scai.Project.dao.EmployeeDAO;
import com.Scai.Project.entities.Employee;
import com.Scai.Project.entities.Entity;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO; 

    @Autowired
    private ApplicationContext context;

    public EmployeeService(){

    }

    public List<Employee> findAll() {
        Map<Integer, Entity> data = employeeDAO.read();
        List<Employee> ris = new ArrayList<>();

        for (Entity employee : data.values()) {
            ris.add((Employee) employee);
        }

        return ris;
    }
    
}
