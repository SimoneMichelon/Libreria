package com.Scai.Project.entities;

public class Cover extends Entity{
    private String url;
    private int idBook;

    public Cover(int id, String url, int idBook) {
        super(id);
        this.url = url;
        this.idBook = idBook;
    }
    

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }   
}
