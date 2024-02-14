package com.Scai.Project.entities;

import java.sql.Date;

public class Order extends Entity {
    private Date order_date;
    private double total_price;
    private String order_status;

    public Order(int id, Date order_date, double total_price, String order_status) {
        super(id);
        this.order_date = order_date;
        this.total_price = total_price;
        this.order_status = order_status;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

}
