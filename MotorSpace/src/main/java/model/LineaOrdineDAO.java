package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LineaOrdineDAO {
    public List<LineaOrdine> doRetrieveByOrdine(int ordine){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT lineadordine.prodotto, lineadordine.qt, lineadordine.prezzounitario FROM lineadordine WHERE lineadordine.ordine=?");
            ps.setInt(1,ordine);
            ResultSet rs = ps.executeQuery();
            List<LineaOrdine> Linee = new ArrayList<LineaOrdine>();
            while (rs.next()){
                LineaOrdine l = new LineaOrdine();
                l.setIdOrdine(ordine);
                l.setIdProdotto(rs.getString(1));
                l.setQuantità(rs.getInt(2));
                l.setPrezzoUnitario(rs.getFloat(3));
                Linee.add(l);
            }
            return Linee;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void doSave(LineaOrdine l){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("INSERT INTO lineadordine(lineadordine.ordine, lineadordine.prodotto, lineadordine.qt, lineadordine.prezzounitario) VALUES (?,?,?,?)");
            ps.setInt(1,l.getIdOrdine());
            ps.setString(2,l.getIdProdotto());
            ps.setInt(3,l.getQuantità());
            ps.setFloat(4,l.getPrezzoUnitario());
            if(ps.executeUpdate() != 1){
                throw new RuntimeException("INSERT error");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
