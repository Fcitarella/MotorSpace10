package DAOTest;

import model.Categoria;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.CategoriaDAO;
import setup.InitTestDb;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.*;

public class CategoriaDAOTest {
    CategoriaDAO categoriaDAO;

    @BeforeEach
    public void setup() throws FileNotFoundException, SQLException {
        new InitTestDb().initeDb();
        categoriaDAO = new CategoriaDAO();
    }
        @AfterEach
        public void tearDown() throws SQLException {
            new InitTestDb().destroyDb();
        }

        @Test
        public void doSaveTest () throws SQLException
        {
            Categoria categoria = new Categoria();
            categoria.setNome("Pneumatici");
            categoria.setDescrizione("Pneumatici per moto");
            categoriaDAO.doSave(categoria);
            assertNotNull(categoriaDAO.doRetrieveById(5), "Deve aggiungere la categoria al database");
        }

        @Test
        public void doUpdateTest () throws SQLException
        {
            Categoria categoria = new Categoria();
            categoria.setId(5);
            categoria.setNome("Pneumatici");
            categoria.setDescrizione("Pneumatici sfiziosi!%%");
            categoriaDAO.doUpdate(categoria);
            assertEquals(categoria, categoriaDAO.doRetrieveById(5), "Deve modificare la categoria al database");
        }


        @Test
        public void doRetrieveAllTest () throws SQLException
        {
            List<Categoria> categoriaList = categoriaDAO.doRetrieveAll();
            assertNotNull(categoriaList, "Non deve essere vuoto");
        }

        @Test
        public void doRetrieveByIdTest () throws SQLException
        {
            assertNotNull(categoriaDAO.doRetrieveById(2), "Non deve essere vuoto");
        }

        @Test
        public void doRetrieveByIdFailure () throws SQLException
        {
            assertNull(categoriaDAO.doRetrieveById(18), "Non deve restituire nulla");
        }

        @Test
        public void doDeleteTest () throws SQLException
        {
            categoriaDAO.doDelete(2);
            assertNull(categoriaDAO.doRetrieveById(2), "Deve essere eliminato");
        }


        @Test
        public void doDeleteFailure () throws SQLException
        {
            categoriaDAO.doDelete(10);
            assertNotNull(categoriaDAO.doRetrieveById(10), "Non deve eliminare niente");
        }
    }
