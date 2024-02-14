package com.Scai.Project.entities;

import java.sql.Date;

public class Employee extends Model {
    private String working_role;

    public Employee(int id, String name, String surname, Date dob, String working_role, String username,
            String password) {
        super(id, name, surname, dob, username, password);
        this.working_role = working_role;
    }

    public String getWorking_role() {
        return working_role;
    }

    public void setWorking_role(String working_role) {
        this.working_role = working_role;
    }

}
