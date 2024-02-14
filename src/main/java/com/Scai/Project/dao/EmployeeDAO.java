package com.Scai.Project.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.Scai.Project.entities.Entity;

public class EmployeeDAO implements IDAO{

    @Autowired
    private Database database;

    @Autowired
    private ApplicationContext context;

    @Override
    public void create(Entity e) {
        String query = "insert into Employees ()";
        
    }

    @Override
    public void delete(int id) {

        
    }

    @Override
    public Map<Integer, Entity> read() {

        return null;
    }

    @Override
    public void update(Entity e) {

        
    }



    
}
