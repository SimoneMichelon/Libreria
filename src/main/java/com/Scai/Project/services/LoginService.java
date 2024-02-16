package com.Scai.Project.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.Scai.Project.dao.UserDAO;
import com.Scai.Project.entities.Model;

public class LoginService
{

    @Autowired
    private UserDAO userDAO;

    public Model findUser(String username, String password)
    {
        return userDAO.readUser(username, password);
    }

    

    

}