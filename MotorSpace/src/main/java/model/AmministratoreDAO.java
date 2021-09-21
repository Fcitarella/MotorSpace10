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
            PreparedStatement ps = con.prepareStatement("SELECT username,tipoamministratore, admin FROM amministratore WHERE username =?");
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Amministratore p = new Amministratore();
                p.setUsername(rs.getString(1));
                p.setTipoAmm(rs.getString(2));

                return p;
            }
            return null;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }


    public List<Amministratore> doRetrieveAll(int offset, int limit){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT username, tipoamministratore FROM amministratore LIMIT ?,?  ");
            ps.setInt(1,offset);
            ps.setInt(2,limit);
            List<Amministratore> amministratori= new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Amministratore u= new Amministratore();
                u.setUsername(rs.getString(1));
                u.setTipoAmm(rs.getString(2));
                amministratori.add(u);
            }
            return amministratori;

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public List<Amministratore> doRetrieveByTipo(String tipo){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT username, tipoamministratore FROM amministratore where tipoamministratore = ?  ");
            ps.setString(1,tipo);
            List<Amministratore> amministratori= new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Amministratore u= new Amministratore();
                u.setUsername(rs.getString(1));
                u.setTipoAmm(tipo);
                amministratori.add(u);
            }
            return amministratori;

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
