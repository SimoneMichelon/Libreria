package com.Scai.Project.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.Scai.Project.entities.Cover;
import com.Scai.Project.entities.Entity;

public class CoverDAO implements IDAO{

    @Autowired
    public Database database;

    @Autowired
    public ApplicationContext context;

    @Override
    public void create(Entity e) {
        String query = "insert into covers (url, idBook) values (?, ?)";

        PreparedStatement ps = null;
        
        try {
            Cover cover = (Cover)e;
            ps = database.getConnection().prepareStatement(query);

            ps.setString(1, cover.getUrl());
            ps.setInt(2, cover.getIdBook());
            
            ps.executeUpdate();
         } catch (SQLException exc) {
            System.out.println("Errore nella query di insert | CoverDAO | create" + exc.getMessage());
        }
        catch (ClassCastException exc){
            System.out.println("Errore nel cast di Cover | CoverDAO | create" + exc.getMessage());
        }
        finally{
            try {
                ps.close();
            } catch (Exception exc) {
                System.out.println("Errore nella chiusura del PS | CoverDAO | create");
            }
        }
    }

    @Override
    public void delete(int id) {
        String query = "delete from covers where id = ?";

        PreparedStatement ps = null;

        try {
            ps = database.getConnection().prepareStatement(query);
            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException exc) {
            System.out.println("Errore nella delete | CoverDAO | delete" + exc.getMessage());
        }
        finally{
            try {
                ps.close();
            } catch (Exception exc) {
                System.out.println("Errore nella chiusura del PS | CoverDAO | delete" + exc.getMessage());
            }
        }


    }

    @Override
    public Map<Integer, Entity> read() {
        String query = "select * from covers";
        Map<Integer, Entity> ris = new HashMap<>();

        PreparedStatement ps =null;
        ResultSet rs = null;

        try {           
            ps = database.getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next()){
                Map<String,String> params = new HashMap<>();

                params.put("id", rs.getInt("id")+"");
                params.put("url", rs.getString("url"));
                params.put("idBook", rs.getInt("idBook")+"");

                Cover cover = context.getBean(Cover.class, params);

                ris.put(rs.getInt("id"), cover);
            }
        } catch (SQLException e) {
            System.out.println("Errore nella read | CoverDAO | read " + e.getMessage());
        }
        finally{
            try {
                ps.close();
                rs.close();
            } catch (Exception e) {
                System.out.println("Errore nella chiusura del PS e RS | CoverDAO | read " + e.getMessage());
            }
        }

        return ris;
    }

    @Override
    public void update(Entity e) {
        String query = "update Covers set url = ?, idBook = ? where id = ?";
        PreparedStatement ps = null;

        try {
            ps = database.getConnection().prepareStatement(query);
            Cover cover = (Cover)e;

            ps.setString(1, cover.getUrl());
            ps.setInt(2, cover.getIdBook());
            ps.setInt(3, cover.getId());
            
        } catch (SQLException exc) {
            System.out.println("Errore nella update | CoverDAO | update ");
        }
        finally{
            try {
                
            } catch (Exception exc) {
                System.out.println("Errore nella chiusura del PS | CoverDAO | update");
            }
        } 
    }
}
