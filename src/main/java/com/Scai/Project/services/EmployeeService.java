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

    public Employee findById(int id){
        Map<Integer,Entity> data = employeeDAO.read();
        return (Employee)data.get(id);
    }

    public List<Employee> findByFullName(String fullname){
        Map<Integer,Entity> data = employeeDAO.read();
        List<Employee> ris = new ArrayList<>();

        for (Entity employee : data.values()) {
            if((((Employee)employee).getName() + " " + ((Employee)employee).getSurname()).equalsIgnoreCase(fullname)){
                ris.add((Employee)employee);
            }
        }
        return ris;
    }

    public List<Employee> findByWorkingRole(String workingRole){
        Map<Integer,Entity> data = employeeDAO.read();
        List<Employee> ris = new ArrayList<>();

        for (Entity employee : data.values()) {
            if(((Employee)employee).getWorking_role().equalsIgnoreCase(workingRole)){
                ris.add((Employee)employee);
            }
        }

        return ris;
    }

    public void updateEmployee(Map<String,String> params){
        Employee e = context.getBean(Employee.class,params);
        employeeDAO.update(e);;
    }
    
    public void insertEmployee(Map<String,String> params){
        Employee e = context.getBean(Employee.class,params);
        employeeDAO.create(e);
    }

    public void deleteEmployee(int id){
        employeeDAO.delete(id);
    }

}
