package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdineDAO {

    private static String DO_RETRIEVE_BY_ID = "SELECT id_ordine, cliente, dataordine, dataspedizione, dataconsegna FROM ordine WHERE id_ordine =?";

    public List<Ordine> doRetrieveByUsername(String username){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT id_ordine, cliente, dataordine, dataspedizione, dataconsegna FROM ordine WHERE cliente =?");
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            List<Ordine> ordini = new ArrayList<>();
            while(rs.next()){
                Ordine o = new Ordine();
                o.setId(rs.getInt(1));
                o.setCliente(rs.getString(2));
                o.setDataOrdine(rs.getDate(3));
                o.setDataSpedizione(rs.getDate(4));
                o.setDataConsegna(rs.getDate(5));
                ordini.add(o);
            }
            return ordini;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public int doSave (Ordine o){
        try (Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("INSERT INTO ordine(cliente, dataordine, dataspedizione, dataconsegna) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,o.getCliente());
            ps.setDate(2,new Date(new java.util.Date().getTime()));
            ps.setDate(3,new Date(new java.util.Date().getTime()));
            ps.setDate(4,new Date(new java.util.Date().getTime()));

            if(ps.executeUpdate() != 1){
                throw new RuntimeException("INSERT error");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            return id;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public void doDelete (String cliente, int idOrdine){
        try (Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("DELETE FROM ordine WHERE cliente =? AND id_ordine=?");
            ps.setString(1, cliente);
            ps.setInt(2,idOrdine);
            if(ps.executeUpdate() !=1){
                throw new RuntimeException("DELETE error");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

        public Ordine doRetrieveById(int id_ordine){
            try(Connection con = ConPool.getConnection()){
                PreparedStatement ps = con.prepareStatement(DO_RETRIEVE_BY_ID);
                ps.setInt(1,id_ordine);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    Ordine o = new Ordine();
                    o.setId(rs.getInt(1));
                    o.setCliente(rs.getString(2));
                    o.setDataOrdine(rs.getDate(3));
                    o.setDataSpedizione(rs.getDate(4));
                    o.setDataConsegna(rs.getDate(5));
                    return o;
                }
                return null;
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
}
