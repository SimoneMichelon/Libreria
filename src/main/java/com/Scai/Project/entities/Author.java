package com.Scai.Project.entities;

import java.sql.Date;

public class Author extends Entity{
    private String name;
    private String surname;
    private Date dob;
    private String biography;

    public Author(int id, String name, String surname, Date dob, String biography) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.dob = dob;
        this.biography = biography;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String username) {
        this.surname = username;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    @Override
    public String toString() {
        return "Author [name=" + name + ", surname=" + surname + ", dob=" + dob + ", biography=" + biography + "]";
    }    
}
