package com.Scai.Project.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.Scai.Project.dao.CoverDAO;
import com.Scai.Project.entities.Cover;
import com.Scai.Project.entities.Entity;

@Service
public class CoverService {

    @Autowired
    public CoverDAO coverDAO;

    @Autowired
    public ApplicationContext context;

    public CoverService(){

    }

    public List<Cover> findAll(){
        Map<Integer,Entity> data = coverDAO.read();
        List<Cover> ris = new ArrayList<>();

        for (Entity cover : data.values()) {
            ris.add((Cover)cover);
        }

        return ris;
    }
    
}
