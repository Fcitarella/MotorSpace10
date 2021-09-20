package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    public List<Categoria> doRetrieveByMacro(String macro){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT id, nome, descrizione FROM categoria WHERE macrocategoria=?");
            ps.setString(1,macro);
            List<Categoria> categorie = new ArrayList<Categoria>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Categoria c= new Categoria();
                c.setId(rs.getInt(1));
                c.setNome(rs.getString(2));
                c.setDescrizione(rs.getString(3));
                categorie.add(c);
            }
            return categorie;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public List<Categoria> doRetrieveAll(){
        try(Connection con = ConPool.getConnection()) {
            PreparedStatement ps= con.prepareStatement("SELECT id, nome, descrizione FROM categoria");
            List<Categoria> categorie= new ArrayList<Categoria>();
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Categoria c= new Categoria();
                c.setId(rs.getInt(1));
                c.setNome(rs.getString(2));
                c.setDescrizione(rs.getString(3));
                categorie.add(c);
            }
            return categorie;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public Categoria doRetrieveById(int id){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps= con.prepareStatement("SELECT id,nome,descrizione FROM categoria WHERE id=?");
            ps.setInt(1,id);

            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Categoria c= new Categoria();
                c.setId(rs.getInt(1));
                c.setNome(rs.getString(2));
                c.setDescrizione(rs.getString(3));
                return c;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    public void doSave(Categoria categoria){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("INSERT INTO categoria(nome, descrizione) VALUES (?,?)");
            ps.setString(1,categoria.getNome());
            ps.setString(2,categoria.getDescrizione());
            if(ps.executeUpdate() != 1){
                throw new RuntimeException("INSERT error");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public void doUpdate(Categoria categoria){
        try (Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("UPDATE categoria SET nome=?, descrizione=? WHERE id=?");
            ps.setString(1,categoria.getNome());
            ps.setString(2,categoria.getDescrizione());
            ps.setInt(3,categoria.getId());
            if(ps.executeUpdate() != 1){
                throw new RuntimeException("UPDATE error");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public void doDelete(int id){
        try (Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("DELETE FROM categoria WHERE id=?");
            ps.setInt(1,id);
            if(ps.executeUpdate() != 1){
                throw new RuntimeException("DELETE error");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
