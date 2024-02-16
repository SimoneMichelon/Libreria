package com.Scai.Project.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.Scai.Project.entities.Customer;
import com.Scai.Project.entities.Entity;

public class CustomerDAO implements IDAO{
    
    @Autowired
    private Database database;

    @Autowired
    private ApplicationContext context;

    @Override
    public void create(Entity e) {
        String queryModel = "insert into Models (name,surname,dob,username,password) values (?,?,?,?,?)";
        String queryGetIdModel = "select id from Models where name like ? && surname like ? && dob like && username like ? && password like ?";
        String queryCustomers = "insert into Customers (idModel) values (?); ";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Customer customer = (Customer)e;

            ps = database.getConnection().prepareStatement(queryModel);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getSurname());
            ps.setDate(3, customer.getDob());
            ps.setString(4, customer.getUsername());
            ps.setString(5, customer.getPassword());

            ps.executeUpdate();

            ps = database.getConnection().prepareStatement(queryGetIdModel);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getSurname());
            ps.setDate(3, customer.getDob());
            ps.setString(4, customer.getUsername());
            ps.setString(5, customer.getPassword());

            rs = ps.executeQuery();

            int idModel = -1;
            while (rs.next()) {
                idModel = rs.getInt("id");
            }

            ps = database.getConnection().prepareStatement(queryCustomers);
            ps.setInt(1, idModel);
            ps.executeUpdate();


        } catch (SQLException exc) {
            System.out.println("Errore nella create in CustomerDAO " + exc.getMessage());
        }
        catch (ClassCastException exc){
            System.out.println("Errore nel cast in create in CustomerDAO " + exc.getMessage());
        }
        finally{
            try {
                ps.close();
                rs.close();
            } catch (Exception es) {
                System.out.println("Errore nella chiusura del PS e RS " + es.getMessage());
            }
        }
    }

    @Override
    public void delete(int id) {
       String selectCustomer = "select idModel from Customers where id = ?";
       String queryCustomer = "delete from Customers where id = ?";
       String queryModel = "delete from Models where id = ?";

       PreparedStatement ps = null;
       ResultSet rs = null;

        try {
            ps = database.getConnection().prepareStatement(selectCustomer);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            int idModel = -1;
            while (rs.next()) {
                idModel = rs.getInt("idModel");
            }

            ps = database.getConnection().prepareStatement(queryCustomer);
            ps.setInt(1, id);
            ps.executeUpdate();

            ps = database.getConnection().prepareStatement(queryModel);
            ps.setInt(1, idModel);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Errore nella delete di CustomerDAO " + e.getMessage());
        }
        finally{
            try {
                ps.close();
                rs.close();
            } catch (Exception e) {
                System.out.println("Errore nella chiusura del Prepared Statement o Result Set  " + e.getMessage());
            }
        }
        
    }

    @Override
    public Map<Integer, Entity> read() {
        String query = "select * from Models m inner join Customers c ON m.id = c.idModel";
        
        PreparedStatement ps = null;
        ResultSet rs = null;

        Map<Integer,Entity> ris = new HashMap<>(); 

        try {

            ps = database.getConnection().prepareStatement(query);
            rs =ps.executeQuery();

            while (rs.next()) {
                Map<String,String> params = new HashMap<>();
                params.put("idCustomer", rs.getInt("c.id")+"");
                params.put("name", rs.getString("m.name"));
                params.put("surname", rs.getString("m.surname"));
                params.put("dob", rs.getDate("m.dob").toString());
                params.put("username", rs.getString("m.username"));
                params.put("password", rs.getString("m.password"));
                params.put("idModel", rs.getInt("m.id")+"");

                Customer customer = context.getBean(Customer.class, params);

                ris.put(rs.getInt("m.id"), customer);
            }            
        } catch (SQLException e) {
            System.out.println("Errore nella read di CustomerDAO " + e.getMessage());
        }
        finally{
            try {
                ps.close();
                rs.close();
            } catch (Exception e) {
                System.out.println("Errore nella chiusura del Prepared Statement o Result Set  " + e.getMessage());
            }
        }
        return ris;
    } 

    @Override
    public void update(Entity e) {
        String queryIdModel = "select c.id from models m inner join customers c on m.id = c.idModel where m.id = ?";
        String queryCustomer = "update customers set idModel = ? where id = ?";
        String queryModel = "update models set name = ?, surname = ?, dob = ?, username = ?, password = ? where id = ?";

        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            Customer customer = (Customer)e;
            ps = database.getConnection().prepareStatement(queryIdModel);
            ps.setInt(1, customer.getId());
            rs = ps.executeQuery();

            int idCustomer = -1;
            while (rs.next()) {
                idCustomer = rs.getInt("c.id");
            }

            ps = database.getConnection().prepareStatement(queryCustomer);
            ps.setInt(1, customer.getId());
            ps.setInt(1, idCustomer);
            ps.executeQuery();

            ps = database.getConnection().prepareStatement(queryModel);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getSurname());
            ps.setDate(3, customer.getDob());
            ps.setString(4, customer.getUsername());
            ps.setString(5, customer.getPassword());
            ps.setInt(6, customer.getId());
            ps.executeUpdate();

        } catch (SQLException exc) {
            System.out.println("Errore nella update in CustomerDAO " + exc.getMessage());
        }
        catch (ClassCastException exc){
            System.out.println("Errore nel cast in update in CustomerDAO " + exc.getMessage());
        }
        finally{
            try {
                ps.close();
            } catch (Exception es) {
                System.out.println("Errore nella chiusura del PS " + es.getMessage());
            }
        }
        
    }

}
