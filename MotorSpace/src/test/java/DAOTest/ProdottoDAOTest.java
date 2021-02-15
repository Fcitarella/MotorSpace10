package DAOTest;

import model.Prodotto;
import model.ProdottoDAO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import setup.InitTestDb;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class ProdottoDAOTest {
    ProdottoDAO prodottoDAO;

    @BeforeEach
    public void setup() throws FileNotFoundException, SQLException {
        new InitTestDb().initeDb();
        prodottoDAO = new ProdottoDAO();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        new InitTestDb().destroyDb();
    }

    @Test
    public void doRetrieveByIdTest() throws SQLException {
        assertNotNull(prodottoDAO.doRetrieveById("1"), "Deve restituire un prodotto");
    }

    @Test
    public void doRetrieveByIdFailure() throws SQLException {
        assertNull(prodottoDAO.doRetrieveById("3"), "Non esiste questo id");
    }

    @Test
    public void doRetrieveAllTest() throws SQLException {
        List<Prodotto> prodotti = new ArrayList<Prodotto>();
        prodotti = prodottoDAO.doRetrieveAll(0, 2);
        assertNotNull(prodotti, "Deve esserci almeno un prodotto");
    }

    @Test
    public void doRetrieveByNomeTest() throws SQLException{
        assertNotNull(prodottoDAO.doRetrieveByNome("PistaGp",0,2),"Deve restituire un prodotto");
    }

    @Test
    public void doRetrieveByNomeFailure() throws SQLException{
        assertNull(prodottoDAO.doRetrieveByNome("Casco",0,2),"Non restituisce alcun prodotto");
    }

    @Test
    public void doRetrieveByCategoriaTest() throws SQLException{
        assertNotNull(prodottoDAO.doRetrieveByCategoria(1, ProdottoDAO.OrderBy.DEFAULT,2,2),"Deve restituire una categoria");

    }


    @Test
    public void doRetrieveByCategoriaFailure() throws SQLException{
        List<Prodotto> prodotti = Collections.emptyList();
        assertEquals(prodotti, prodottoDAO.doRetrieveByCategoria(7, ProdottoDAO.OrderBy.DEFAULT,0,10),"Non restituisce una categoria");
    }

    @Test
    public void doRetrieveByNomeOrDescrizioneTest() throws SQLException{
        assertEquals(prodottoDAO.doRetrieveByNomeOrDescrizione("casco",0,2),"Deve restituire un prodotto");
    }

    @Test
    public void doRetrieveByNomeOrDescrizioneFailure() throws SQLException{
        assertNull(prodottoDAO.doRetrieveByNomeOrDescrizione("patatine",0,2),"Non deve restituire nulla");
    }

    @Test
    public void countByCategoriaTest() throws SQLException
    {
        assertNotNull(prodottoDAO.countByCategoria(2),"Deve restituire il numero della categoria");
    }


    @Test
    public void doUpdateTest() throws SQLException{
        Prodotto prodotto = new ProdottoDAO().doRetrieveById("0000000001");
        prodotto.setNome("Casco35");

        prodottoDAO.doUpdate(prodotto,1);
        assertEquals(prodotto,prodottoDAO.doRetrieveById("0000000001"), "Deve ritornare nome Casco35");
    }



    @Test
    public void doSaveTest() throws SQLException
    {
        Prodotto prodotto = new Prodotto();
        prodotto.setNome("Casco837");
        prodotto.setId("NIEN3DF923");
        prodotto.setDescrizione("Un casco sfizioso");
        prodotto.setPrezzo(45);
        prodotto.setMarca("Pirelli");
        prodotto.setCategoria("casco");
        prodottoDAO.doSave(prodotto,1);
        assertNotNull(prodottoDAO.doRetrieveById("NIEN3DF923"),"Deve aggiungere il prodotto al database");
    }


}
