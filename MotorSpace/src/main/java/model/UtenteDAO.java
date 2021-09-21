package model;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAO {

    public Utente doRetrieveByUsername(String username){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT username, email, password, nome, cognome FROM cliente WHERE username =?");
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Utente p = new Utente();
                p.setUsername(rs.getString(1));
                p.setEmail(rs.getString(2));
                p.setPassword(rs.getString(3));
                p.setNome(rs.getString(4));
                p.setCognome(rs.getString(5));
                return p;
            }
            return null;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void doSave(Utente utente){
        try(Connection c = ConPool.getConnection()){

            PreparedStatement ps = c.prepareStatement("insert into cliente(username, email, password, nome, cognome) values(?, ?, ?, ?, ?)");
            ps.setString(1, utente.getUsername());
            ps.setString(2, utente.getEmail());
            ps.setString(3, utente.getPassword());
            ps.setString(4, utente.getNome());
            ps.setString(5, utente.getCognome());
            if(ps.executeUpdate() != 1){
                throw new RuntimeException("INSERT error");
            }

        } catch (SQLException e){
            throw new RuntimeException();
        }
    }

    public List<Utente> doRetrieveAll(int offset,int limit){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT username, email, password, nome FROM cliente LIMIT ?,?  ");
            ps.setInt(1,offset);
            ps.setInt(2,limit);
            List<Utente> utenti= new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Utente u= new Utente();
                u.setUsername(rs.getString(1));
                u.setEmail(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setNome(rs.getString(4));
                u.setCognome(rs.getString(5));
                utenti.add(u);
            }
            return utenti;

        } catch (SQLException e) {
           throw new RuntimeException();
        }
    }
    public Utente doRetrieveByUsernamePassword(String username, String password){
        try(Connection con= ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement(
                    "SELECT username, email, password, nome, cognome FROM cliente WHERE username=? AND password = ?");
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Utente p= new Utente();
                p.setUsername(rs.getString(1));
                p.setEmail(rs.getString(2));
                p.setPassword(rs.getString(3));
                p.setNome(rs.getString(4));
                p.setCognome(rs.getString(5));
                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
