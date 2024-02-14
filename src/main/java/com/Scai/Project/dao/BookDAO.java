package com.Scai.Project.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.Scai.Project.entities.Author;
import com.Scai.Project.entities.Book;
import com.Scai.Project.entities.Entity;

public class BookDAO implements IDAO{

    @Autowired
    private Database database;

    @Autowired
    private ApplicationContext context;

    @Override
    public void create(Entity e) {
        String query = "insert into Books (title, type, num_pages, price, idAuthor) values (?,?,?,?,?);";
        PreparedStatement ps = null;

        try {
            
            Book b = (Book)e;
            ps = database.getConnection().prepareStatement(query);
            
            ps.setString(1, b.getTitle());
            ps.setString(2, query);
            ps.setInt(3, b.getNum_pages());
            ps.setDouble(4, b.getPrice());
            ps.setInt(5, b.getAuthor().getId());

            ps.executeUpdate();
        } catch (SQLException exc) {
            System.out.println("Errore nella query di insert | BookDAO | create" + exc.getMessage());
        }
        catch (ClassCastException exc){
            System.out.println("Errore nel cast di Book | BookDAO | create" + exc.getMessage());
        }
        finally{
            try {
                ps.close();
            } catch (Exception exc) {
                System.out.println("Errore nella chiusura del PS | BookDAO | create");
            }
        }
    }

    @Override
    public void delete(int id) {
        String query = "delete from Books WHERE id = ?";
        PreparedStatement ps = null;

        try {

            ps = database.getConnection().prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException exc) {
            System.out.println("Errore nella delete | BookDAO | delete" + exc.getMessage());
        }
        finally{
            try {
                ps.close();
            } catch (Exception exc) {
                System.out.println("Errore nella chiusura del PS | BooksDAO | delete" + exc.getMessage());
            }
        }
    }

    @Override
    public Map<Integer, Entity> read() {
        String query = "select * from Books b inner join Authors a on b.idAuthor = a.id";
        PreparedStatement ps = null;
        ResultSet rs = null;

        Map<Integer,Entity> ris = new HashMap<>();

        try {

            ps = database.getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next()){
                Map<String,String> paramsBook = new HashMap<>();
                paramsBook.put("id", rs.getString("b.id"));
                paramsBook.put("title", rs.getString("b.title"));
                paramsBook.put("type", rs.getString("b.type"));
                paramsBook.put("num_pages", rs.getInt("b.num_pages")+"");
                paramsBook.put("price", rs.getDouble("b.price")+"");

                Map<String,String> paramsAuthor = new HashMap<>();
                paramsAuthor.put("id",rs.getInt("a.id")+"");
                paramsAuthor.put("name", rs.getString("a.name"));
                paramsAuthor.put("surname", rs.getString("a.surname"));
                paramsAuthor.put("dob", rs.getDate("a.dob").toString());
                paramsAuthor.put("biography", rs.getString("a.biography"));

                Author a = context.getBean(Author.class, paramsAuthor);
                Book b = context.getBean(Book.class, paramsBook, a);

                ris.put(rs.getInt("b.id"), b);
            }
            
        } catch (SQLException e) {
            System.out.println("Errore nella read | BookDAO | read " + e.getMessage());
        }
        finally{
            try {
                ps.close();
                rs.close();
            } catch (Exception e) {
                System.out.println("Errore nella chiusura del PS e RS | BookDAO | read " + e.getMessage());
            }
        }
        return ris;
    }

    @Override
    public void update(Entity e) {
        String query = "update Books set title = ?, type = ?, num_pages = ?, price = ?, idAuthor = ? where id = ?"; 
        PreparedStatement ps = null;

        try {
            ps = database.getConnection().prepareStatement(query);

            Book b = (Book)e;

            ps.setString(1, b.getTitle());
            ps.setString(2, b.getType());
            ps.setInt(3, b.getNum_pages());
            ps.setDouble(4, b.getPrice());
            ps.setInt(5, b.getAuthor().getId());
            ps.setInt(6, b.getId());

            ps.executeUpdate();

        } catch (SQLException exc) {
            System.out.println("Errore nella update | BookDAO | update ");
        }
        finally{
            try {
                
            } catch (Exception exc) {
                System.out.println("Errore nella chiusura del PS | BookDAO | update");
            }
        }
    }
    
}
