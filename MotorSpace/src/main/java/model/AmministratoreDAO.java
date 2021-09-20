package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AmministratoreDAO {

    public Utente doRetrieveByUsername(String username){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT username,tipoamministratore, admin FROM cliente WHERE username =?");
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Utente p = new Utente();
                p.setUsername(rs.getString(1));
                p.setEmail(rs.getString(2));
                p.setPassword(rs.getString(3));
                p.setNome(rs.getString(4));
                p.setCognome(rs.getString(5));
                p.setNascita(rs.getDate(6));
                return p;
            }
            return null;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
