package com.Scai.Project.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Scai.Project.entities.Customer;
import com.Scai.Project.entities.Employee;
import com.Scai.Project.entities.Model;


// --- if there'll be Bean problems
@Repository
public class UserDAO {

    @Autowired
    private Database database;

    @Autowired
    private CustomerDAO customersDAO;


    @Autowired 
    private EmployeeDAO employeeDAO;

    
    public Model readUser(String username, String password)
    {
        String query = "Select id from Models where username Like ? and password Like ?";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Model ris = null;
        
        try
        {   
            ps = database.getConnection().prepareStatement(query);
            ps.setString(1,username);
            ps.setString(2,password);

            rs = ps.executeQuery();

            if(rs.next())
            {
                int idModel = rs.getInt(1);
                Customer customer = customersDAO.readFromId(idModel);
                Employee employee = employeeDAO.readFromId(idModel);

                if(customer != null)
                {
                    ris = customer;
                }
                else if(employee != null)
                {
                    ris = employee;
                }
                else
                {
                    ris = null;
                }
            }
        }
        catch (SQLException exc) {
            System.out.println("Errore nella read in UserDAO " + exc.getMessage());
        }
        catch (ClassCastException exc){
            System.out.println("Errore nel cast in read in UserDAO " + exc.getMessage());
        }
        finally{
            try {
                ps.close();
                rs.close();
            } catch (Exception es) {
                System.out.println("Errore nella chiusura del PS e RS" + es.getMessage());
            }
        }

        return ris;
    }
    
}
