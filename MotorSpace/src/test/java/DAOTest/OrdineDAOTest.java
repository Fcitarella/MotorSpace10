package DAOTest;

import model.CategoriaDAO;
import model.Ordine;
import model.OrdineDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.runners.model.RunnerBuilder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.engine.*;
import org.mockito.junit.jupiter.MockitoExtension;
import setup.InitTestDb;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



public class OrdineDAOTest {
    OrdineDAO ordineDAO;
    @BeforeEach
    public void setup() throws FileNotFoundException, SQLException {
        new InitTestDb().initeDb();
         ordineDAO = new OrdineDAO();
    }
    @AfterEach
    public void tearDown() throws SQLException {
        new InitTestDb().destroyDb();
    }

    @Test
    public void doRetrieveByUserTest()
    {
        assertNotNull(ordineDAO.doRetrieveByUsername("Pasquale88"),"Deve restituire gli ordini dell'utente");
    }
    
    @Test
    public void doRetrieveByUserFailure()
    {
        List<Ordine> ordini = Collections.emptyList();
        assertEquals(ordini , ordineDAO.doRetrieveByUsername("Pippo@#"),"Non restituisce l'ordine");
    }
    
    @Test
    public void doDeleteTest()
    {
        ordineDAO.doDelete("Pasquale88",1);
        assertNull( ordineDAO.doRetrieveById(1),"Non restituisce l'ordine");
    }

    
    @Test
    public void doDeleteFailure()
    {
        assertThrows(RuntimeException.class, () -> {ordineDAO.doDelete("Pippo",1);
        });
    }
    
    
    @Test
    public void doSaveTest()
    {
        Ordine ordine = new Ordine();
        ordine.setCliente("Pippo");
        ordineDAO.doSave(ordine);
        assertNotNull(ordineDAO.doRetrieveByUsername("Pippo"),"Deve salvare l'ordine");
    }

    

}
