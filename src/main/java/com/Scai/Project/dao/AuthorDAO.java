package com.Scai.Project.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.Scai.Project.entities.Author;
import com.Scai.Project.entities.Entity;

public class AuthorDAO implements IDAO{

    @Autowired
    private Database database;

    @Autowired
    private ApplicationContext context;

    @Override
    public void create(Entity e) {
        String query = "insert into authors (name, surname, dob, biography) values (?,?,?,?);";
        PreparedStatement ps = null;

        try {

            Author autore = (Author)e;
            ps = database.getConnection().prepareStatement(query);

            ps.setString(1, autore.getName());
            ps.setString(2, autore.getSurname());
            ps.setDate(3, autore.getDob());
            ps.setString(4, autore.getBiography());

            ps.executeUpdate();
        } catch (SQLException exc) {
            System.out.println("Errore inserimento Author | AuthorDAO | create " + exc.getMessage());
        }
        catch (ClassCastException exc) {
            System.out.println("Errore sul cast di Author | AuthorDAO | create " + exc.getMessage());
        }
        finally{
            try {
                ps.close();
            } catch (Exception exc) {
                System.out.println("Errore nella chiusura del PrepareStatement | AuthorDAO | create");
            }

        }
        
    }

    @Override
    public void delete(int id) {
        String query = "delete from Authors where id = ?";
        PreparedStatement ps = null;

        try{

            ps = database.getConnection().prepareStatement(query);
            ps.setInt(1, id);
            ps.executeQuery();

        }catch(SQLException exc){
            System.out.println("Errore nella delete in AuthorDAO | delete" + exc.getMessage());
        }
        finally{
            try {
                ps.close();
            } catch (Exception e) {
                System.out.println("Errore nella chiusura del PS | AuthorDAO | delete");
            }
        }
        
    }

    @Override
    public Map<Integer, Entity> read() {
        String query = "select * from authors";
        PreparedStatement ps = null;
        ResultSet rs = null;

        Map<Integer, Entity> ris = new HashMap<>();
        
        try {
            ps = database.getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next()){
                Map<String,String> params = new HashMap<>();
                params.put("id",rs.getInt("id")+"");
                params.put("name", rs.getString("name"));
                params.put("surname", rs.getString("surname"));
                params.put("dob", rs.getDate("dob").toString());
                params.put("biography", rs.getString("biography"));

                Author a = context.getBean(Author.class, params);

                ris.put(a.getId(), a);
            }

            
        } catch (SQLException e) {
            System.out.println("Errore nella select degli Autori | AuthorDAO | read");
        }
        finally{
            try {
                ps.close();
                rs.close();
            } catch (Exception e) {
                System.out.println("Errore chiusura PS o RS | AuthorDAO" + e.getMessage());
            }
        }
        return ris;
    }

    @Override
    public void update(Entity e) {
        String query = "update authors set name = ?, surname = ?, dob = ?, biography = ? where id = ?";
        PreparedStatement ps = null;
        
        try{
            Author a = (Author)e;
            ps = database.getConnection().prepareStatement(query);
            ps.setString(1, a.getName());
            ps.setString(2, a.getSurname());
            ps.setDate(3, a.getDob());
            ps.setString(4, a.getBiography());
            ps.setInt(5, a.getId());

            ps.executeUpdate();
        }
        catch(SQLException exc){
            System.out.println("Errore nell'update in AuthorDAO | update " + exc.getMessage() );
        }
        catch(ClassCastException exc){
            System.out.println("Errore di casting in AuthorDAO | update " + exc.getMessage());
        }
        finally{
            try{    
                ps.close();
            }catch(Exception exc){
                System.out.println("Errore nella chiusura del PS | AuthorDAO | Update");
            }
        }
    }
    
}
