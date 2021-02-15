package model;

import javax.persistence.OrderBy;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdottoDAO {
    public enum OrderBy{
        DEFAULT(""), PREZZO_ASC("ORDER BY prezzo ASC"), PREZZO_DESC("ORDER BY prezzo DESC");
        private String sql;

        private OrderBy(String sql){
            this.sql=sql;
        }
    }
    public List<Prodotto>doRetrieveAll(int offset,int limit) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT prodotto.nome,prodotto.descrizione,prodotto.codice,prodotto.marca,prodotto.prezzo FROM prodotto limit ?,?");
            ps.setInt(1, offset);
            ps.setInt(2, limit);
            ResultSet rs = ps.executeQuery();

            ArrayList<Prodotto> prodotti = new ArrayList<>();
            while (rs.next()) {
                Prodotto P = new Prodotto();
                P.setNome(rs.getString(1));
                P.setDescrizione(rs.getString(2));
                P.setId(rs.getString(3));
                P.setMarca((rs.getString(4)));
                P.setPrezzo(rs.getFloat(5));
                prodotti.add(P);
            }
            return prodotti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Prodotto doRetrieveById(String id){
        try(Connection con =ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("SELECT prodotto.nome,prodotto.descrizione,prodotto.codice,prodotto.marca,prodotto.prezzo FROM prodotto WHERE codice=?");
            ps.setString(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Prodotto P=new Prodotto();
                P.setNome(rs.getString(1));
                P.setDescrizione(rs.getString(2));
                P.setId(rs.getString(3));
                P.setMarca((rs.getString(4)));
                P.setPrezzo(rs.getFloat(5));
                return P;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Prodotto> doRetrieveByNome(String against,int offset,int limit) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT prodotto.nome,prodotto.descrizione,prodotto.codice,prodotto.marca,prodotto.prezzo FROM prodotto WHERE MATCH (nome)AGAINST(? IN BOOLEAN MODE) LIMIT ?,?");
            ps.setString(1, against);
            ps.setInt(2, offset);
            ps.setInt(3, limit);
            ArrayList<Prodotto> prodotti = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Prodotto P=new Prodotto();
                P.setNome(rs.getString(1));
                P.setDescrizione(rs.getString(2));
                P.setId(rs.getString(3));
                P.setMarca((rs.getString(4)));
                P.setPrezzo(rs.getFloat(5));
                prodotti.add(P);
            }
            return prodotti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int countByCategoria(int categoria){
        try(Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(prodotto.nome) FROM prodotto JOIN categoria ON prodotto.categoria= categoria.id WHERE categoria.id =?");
            ps.setInt(1,categoria);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Prodotto> doRetrieveByNomeOrDescrizione(String against,int offset,int limit) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT prodotto.nome,prodotto.descrizione,prodotto.codice,prodotto.marca,prodotto.prezzo FROM prodotto WHERE MATCH (nome,descrizione)AGAINST(?) LIMIT ?,?");
            ps.setString(1, against);
            ps.setInt(2, offset);
            ps.setInt(3, limit);
            ArrayList<Prodotto> prodotti = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Prodotto P=new Prodotto();
                P.setNome(rs.getString(1));
                P.setDescrizione(rs.getString(2));
                P.setId(rs.getString(3));
                P.setMarca((rs.getString(4)));
                P.setPrezzo(rs.getFloat(5));
                prodotti.add(P);
            }
            return prodotti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Prodotto> doRetrieveByCategoria(int categoria, OrderBy orderBy, int offset, int limit) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT prodotto.nome,prodotto.descrizione,prodotto.codice,prodotto.marca,prodotto.prezzo FROM prodotto JOIN categoria ON prodotto.categoria=categoria.id WHERE categoria.id=?"+orderBy.sql+" LIMIT ?,?");
            ps.setInt(1, categoria);
            ps.setInt(2, offset);
            ps.setInt(3, limit);
            ArrayList<Prodotto> prodotti = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Prodotto P = new Prodotto();
                P.setNome(rs.getString(1));
                P.setDescrizione(rs.getString(2));
                P.setId(rs.getString(3));
                P.setMarca((rs.getString(4)));
                P.setPrezzo(rs.getFloat(5));
                prodotti.add(P);
            }
            return prodotti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void doSave(Prodotto prodotto, int idCategoria){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("INSERT  INTO prodotto(nome, descrizione, codice, marca, prezzo, categoria) VALUES(?,?,?,?,?,?)");
            ps.setString(1,prodotto.getNome());
            ps.setString(2,prodotto.getDescrizione());
            ps.setString(3,prodotto.getId());
            ps.setString(4,prodotto.getMarca());
            ps.setFloat(5,prodotto.getPrezzo());
            ps.setInt(6,idCategoria);
            if(ps.executeUpdate() != 1){
                throw new RuntimeException("INSERT error,");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void doUpdate(Prodotto prodotto,int idCategoria){
        try(Connection con = ConPool.getConnection()){
             PreparedStatement ps=con.prepareStatement("UPDATE prodotto SET nome=?, descrizione=?, marca=?, prezzo=?, categoria=? WHERE codice =?");
             ps.setString(1,prodotto.getNome());
             ps.setString(2,prodotto.getDescrizione());
             ps.setString(3,prodotto.getMarca());
             ps.setFloat(4,prodotto.getPrezzo());
             ps.setInt(5,idCategoria);
             ps.setString(6,prodotto.getId());
        if (ps.executeUpdate() != 1){
            throw new RuntimeException("UPDATE error.");
        }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void doDelete(String id){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("DELETE FROM prodotto WHERE codice=?");
            ps.setString(1, id);
            if(ps.executeUpdate() != 1){
                throw new RuntimeException("UPDATE error .");
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
