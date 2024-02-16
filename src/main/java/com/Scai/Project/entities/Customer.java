package com.Scai.Project.entities;

import java.sql.Date;

public class Customer extends Model{

    public Customer(int id, String name, String surname, Date dob, String username, String password){
        super(id, name, surname, dob, username, password);
    }
}