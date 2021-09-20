/*package DAOTest;

import model.ProdottoDAO;
import model.Utente;
import model.UtenteDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import setup.InitTestDb;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.SQLException;

public class UtenteDAOTest {
    UtenteDAO utenteDAO;
    @BeforeEach
    public void setup() throws FileNotFoundException, SQLException {
        new InitTestDb().initeDb();
         utenteDAO= new UtenteDAO();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        new InitTestDb().destroyDb();
    }

    @Test
    public void doSaveTest() throws SQLException
    {
        Utente utente = new Utente();
        utente.setUsername("Camillo");
        utente.setEmail("Lamia@email.com");
        utente.setPassword("Ciaone5!");
        utente.setNome("Giovanni");
        utente.setCognome("Verdi");
        utente.setNascita(new Date(new java.util.Date().getTime()));
        utenteDAO.doSave(utente);
        assertNotNull(utenteDAO.doRetrieveByUsername("Camillo"),"Deve aggiungere l'utente al database");
    }

    @Test
    public void doUpdateTest() throws SQLExcpetion{
        Utente utente = new Utente();
        utente.setNome("Mario");
        utente.setCognome("Antonietti");
        utente.setEmail("Lamia@email.com");
        utente.setPassword("Ciaone5!");
        utenteDAO.doUpdate(utente);
        assertEquals("Mario",utenteDAO.doRetrieveByUsername("chungus"),"Deve modificare l'utente");
    }
    @Test
    public void doRetrieveByUsernameTest()
    {
        assertNotNull(utenteDAO.doRetrieveByUsername("Pasquale88"),"Deve restituire L'utente");
    }
    @Test
    public void doRetrieveByUsernameFailure()
    {
        assertNull(utenteDAO.doRetrieveByUsername("ci@@@"),"Non deve restituire L'utente");
    }
    @Test
    public void doRetrieveByUsernamePasswordTest()
    {
        assertNotNull(utenteDAO.doRetrieveByUsernamePassword("Pasquale88","777ganggang"),"Deve restituire L'utente");
    }
    @Test
    public void doRetrieveByUsernamePasswordFailure()
    {
        assertNull(utenteDAO.doRetrieveByUsernamePassword("ci@@@","pas12"),"Non deve restituire L'utente");
    }

}*/
