package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AmministratoreDAO {

    public Amministratore doRetrieveByUsername(String username){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT username,email, password, nome, cognome,  codicediaccesso FROM amministratore WHERE username =?");
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Amministratore p = new Amministratore();
                p.setUsername(rs.getString(1));
                p.setEmail(rs.getString(2));
                p.setPassword(rs.getString(3));
                p.setNome(rs.getString(4));
                p.setCognome(rs.getString(5));
                p.setCodice(rs.getString(6));
                return p;
            }
            return null;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Amministratore doRetrieveByCodice(String codice){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT username,email, password, nome, cognome,  codicediaccesso FROM amministratore WHERE codicediaccesso =?");
            ps.setString(1,codice);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Amministratore p = new Amministratore();
                p.setUsername(rs.getString(1));
                p.setEmail(rs.getString(2));
                p.setPassword(rs.getString(3));
                p.setNome(rs.getString(4));
                p.setCognome(rs.getString(5));
                p.setCodice(rs.getString(6));
                return p;
            }
            return null;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Amministratore> doRetrieveAll(int offset,int limit){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT username, email, password, nome, cognome, codicediaccesso FROM amministratore LIMIT ?,?  ");
            ps.setInt(1,offset);
            ps.setInt(2,limit);
            List<Amministratore> amministratori= new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Amministratore u= new Amministratore();
                u.setUsername(rs.getString(1));
                u.setEmail(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setNome(rs.getString(4));
                u.setCognome(rs.getString(5));
                u.setCodice(rs.getString(6));
                amministratori.add(u);
            }
            return amministratori;

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    public Amministratore doRetrieveByUsernamePasswordCode(String username, String password, String code){
        try(Connection con= ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement(
                    "SELECT username, email, password, nome, cognome, codicediaccesso FROM amministratore WHERE username=? AND password = ? AND codicediaccesso=?");
            ps.setString(1,username);
            ps.setString(2,password);
            ps.setString(3,code);

            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Amministratore p= new Amministratore();
                p.setUsername(rs.getString(1));
                p.setEmail(rs.getString(2));
                p.setPassword(rs.getString(3));
                p.setNome(rs.getString(4));
                p.setCognome(rs.getString(5));
                p.setCodice(rs.getString(6));

                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
