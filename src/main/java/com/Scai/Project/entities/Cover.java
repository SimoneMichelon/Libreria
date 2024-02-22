package com.Scai.Project.entities;

public class Cover extends Entity{
    private String cover;
    private int idBook;

    public Cover(int id, String cover, int idBook) {
        super(id);
        this.cover = cover;
        this.idBook = idBook;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }   
}
