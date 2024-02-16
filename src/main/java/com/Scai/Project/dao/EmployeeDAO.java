package com.Scai.Project.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.Scai.Project.entities.Employee;
import com.Scai.Project.entities.Entity;

public class EmployeeDAO implements IDAO{

    @Autowired
    private Database database;

    @Autowired
    private ApplicationContext context;

    @Override
    public void create(Entity e) {
        String queryModel = "insert into Models (name,surname,dob,username,password) values (?,?,?,?,?)";

        //forse manca una select per selezionare l'idModel da inserire 
        String queryEmployee = "insert into Employees (working_role, idModel) values (?,?) ";
        PreparedStatement ps = null;

        try {
            ps = database.getConnection().prepareStatement(queryModel);

            Employee em = (Employee)e;
            ps.setString(1, em.getName());
            ps.setString(2, em.getSurname());
            ps.setDate(3, em.getDob());
            ps.setString(4, em.getUsername());
            ps.setString(5, em.getPassword());
            ps.executeUpdate();


            ps = database.getConnection().prepareStatement(queryEmployee);
            ps.setString(1, em.getWorking_role());
            ps.setInt(2, em.getId());
            ps.executeUpdate();

        } catch (SQLException exc) {
            System.out.println("Errore nella create in EmployeeDAO " + exc.getMessage());
        }
        catch (ClassCastException exc){
            System.out.println("Errore nel cast in create in EmployeeDAO " + exc.getMessage());
        }
        finally{
            try {
                ps.close();
            } catch (Exception es) {
                System.out.println("Errore nella chiusura del PS " + es.getMessage());
            }
        }
    }

    @Override
    public void delete(int id) {
        String selectEmployee = "select idModel from employees where id = ?";
        String queryEmployee = "delete from employees where id = ?";
        String queryModel = "delete from models where id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //select di idModel
            ps = database.getConnection().prepareStatement(selectEmployee);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            int idModel = -1;
            while (rs.next()) {
                idModel = rs.getInt("idModel");
            }
            
            //eliminazione record in Employee
            ps = database.getConnection().prepareStatement(queryEmployee);
            ps.setInt(1, id);
            ps.executeUpdate();

            //eliminazione record in Model
            ps = database.getConnection().prepareStatement(queryModel);
            ps.setInt(1, idModel);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Errore nella delete di EmployeeDAO " + e.getMessage());
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
        String query = "select * from models m inner join employees e on e.idModel = m.id";
        PreparedStatement ps = null;
        ResultSet rs = null;

        Map<Integer,Entity> ris = new HashMap<>();

        try {
            ps = database.getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Map<String,String> params = new HashMap<>();
                params.put("idModel", rs.getInt("m.id")+"");
                params.put("name", rs.getString("m.name"));
                params.put("surname", rs.getString("m.surname"));
                params.put("dob", rs.getDate("m.dob").toString());
                params.put("username", rs.getString("m.username"));
                params.put("password", rs.getString("m.password"));
                params.put("idEmployee", rs.getInt("e.id")+"");
                params.put("working_role", rs.getString("working_role"));

                Employee employee = context.getBean(Employee.class, params);

                ris.put(rs.getInt("m.id"), employee);
            }
            
        } catch (SQLException e) {
            System.out.println("Errore nella read di EmployeeDAO " + e.getMessage());
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
        String selectEmp = "select e.id from models m inner join employees e on m.id = e.idModel where m.id = ?";
        String queryEmployee = "update employees set working_role = ?, idModel = ? where id = ?";
        String queryModel = "update model set name = ?, surname = ?, dob = ?, username = ?, password = ? where id = ?";
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idEmployee = -1;

        try {
            Employee employee = (Employee) e;

            //ricavo id di Employee
            ps = database.getConnection().prepareStatement(selectEmp);
            ps.setInt(1, employee.getId());
            rs = ps.executeQuery();

            while (rs.next()) {
                idEmployee = rs.getInt("e.id");
            }

            //modifico Employee
            ps = database.getConnection().prepareStatement(queryEmployee);
            ps.setString(1, employee.getWorking_role());
            ps.setInt(2, employee.getId());
            ps.setInt(3, idEmployee);
            ps.executeUpdate();

            //modifico Model
            ps = database.getConnection().prepareStatement(queryModel);
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getSurname());
            ps.setDate(3, employee.getDob());
            ps.setString(4, employee.getUsername());
            ps.setString(5, employee.getPassword());
            ps.setInt(6, employee.getId());
            
        } catch (SQLException exc) {
            System.out.println("Errore nella update in EmployeeDAO " + exc.getMessage());
        }
        catch (ClassCastException exc){
            System.out.println("Errore nel cast in update in EmployeeDAO " + exc.getMessage());
        }
        finally{
            try {
                ps.close();
            } catch (Exception es) {
                System.out.println("Errore nella chiusura del PS " + es.getMessage());
            }
        }
    }

    public Employee readFromId(int id){
        String query = "select * from Models m inner join employees e on m.id = e.idModel where m.id = ?;";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Employee employee = null;

        try {
            ps = database.getConnection().prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                Map<String,String> params = new HashMap<>();
                params.put("idModel", rs.getInt("m.id")+"");
                params.put("name", rs.getString("m.name"));
                params.put("surname", rs.getString("m.surname"));
                params.put("dob", rs.getDate("m.dob").toString());
                params.put("username", rs.getString("m.username"));
                params.put("password", rs.getString("m.password"));
                params.put("idEmployee", rs.getInt("e.id")+"");
                params.put("working_role", rs.getString("working_role"));

                employee = context.getBean(Employee.class, params);
            }
            
        } catch (SQLException exc) {
            System.out.println("Errore nella selectFromId in EmployeeDAO " + exc.getMessage());
        }
        finally{
            try {
                ps.close();
                rs.close();
            } catch (Exception es) {
                System.out.println("Errore nella chiusura del PS e RS " + es.getMessage());
            }
        }

        return employee;
    }

}
