package com.Scai.Project.entities;

public class Book extends Entity{
    private String title;
    private String type;
    private int num_pages;
    private double price;
    private Author author;

    public Book(int id, String title, String type, int num_pages, double price, Author author) {
        super(id);
        this.title = title;
        this.type = type;
        this.num_pages = num_pages;
        this.price = price;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNum_pages() {
        return num_pages;
    }

    public void setNum_pages(int num_pages) {
        this.num_pages = num_pages;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    

    
    
}
