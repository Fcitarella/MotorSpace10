package ServletTest;

import Servlet.AggiuntaProdottoServlet;


import Servlet.RegistrazioneServlet;
import model.Prodotto;
import model.ProdottoDAO;
import model.Utente;
import model.UtenteDAO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import setup.InitTestDb;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AggiuntaProdottoServletTest {
    @Mock
    HttpServletResponse response;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpSession session;
    @Mock
    RequestDispatcher rd;
    @InjectMocks
    AggiuntaProdottoServlet aggiuntaProdottoServlet ;

    @BeforeEach
    public void setup() throws FileNotFoundException, SQLException {
        new InitTestDb().initeDb();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        new InitTestDb().destroyDb();
    }
    @Test
    void doPostTest() throws Exception
    {
        aggiuntaProdottoServlet = new AggiuntaProdottoServlet();
        when(request.getSession()).thenReturn(session);

        when(session.getAttribute("utente")).thenReturn(new UtenteDAO().doRetrieveByUsername("adminCatalogo"));
        when(request.getParameter("nome")).thenReturn("Casco");
        when(request.getParameter("idStr")).thenReturn("0000000013");
        when(request.getParameter("descrizione")).thenReturn("Un casco sfizioso");
        when(request.getParameter("prezzo")).thenReturn("45");
        when(request.getParameter("marca")).thenReturn("Pirelli");
        when(request.getParameter("addCategoria")).thenReturn("1");
        when(request.getRequestDispatcher("aggiuntaprodotto.jsp")).thenReturn(rd);
        aggiuntaProdottoServlet.doPost(request,response);
        Optional<Prodotto> prodotto = Optional.ofNullable(new ProdottoDAO().doRetrieveById("0000000013"));
        assertTrue(prodotto.isPresent());
    }
    @Test
    void metodoFailuerNomeLunghezza() throws Exception
    {
        aggiuntaProdottoServlet = new AggiuntaProdottoServlet();
        when(request.getSession()).thenReturn(session);

        when(session.getAttribute("utente")).thenReturn(new UtenteDAO().doRetrieveByUsername("adminCatalogo"));
        when(request.getParameter("nome")).thenReturn("Ca");
        when(request.getParameter("idStr")).thenReturn("0000000013");
        Exception thrown = Assertions.assertThrows(ServletException.class, () -> {
            aggiuntaProdottoServlet.doPost(request, response);
        });
        assertTrue(thrown.getMessage().contains("Nome non valido."));
    }

    @Test
    void metodoFailureNomeFormato() throws Exception
    {
        aggiuntaProdottoServlet = new AggiuntaProdottoServlet();
        when(request.getSession()).thenReturn(session);

        when(session.getAttribute("utente")).thenReturn(new UtenteDAO().doRetrieveByUsername("adminCatalogo"));
        when(request.getParameter("nome")).thenReturn("Casco@@");
        when(request.getParameter("idStr")).thenReturn("0000000013");
        Exception thrown = Assertions.assertThrows(ServletException.class, () -> {
            aggiuntaProdottoServlet.doPost(request, response);
        });
        assertTrue(thrown.getMessage().contains("Nome non valido."));
    }





    @Test
    void metodoFailureDescrizioneLunghezza() throws Exception
    {
        aggiuntaProdottoServlet = new AggiuntaProdottoServlet();
        when(request.getSession()).thenReturn(session);

        when(session.getAttribute("utente")).thenReturn(new UtenteDAO().doRetrieveByUsername("adminCatalogo"));
        when(request.getParameter("nome")).thenReturn("Casco");
        when(request.getParameter("idStr")).thenReturn("0000000013");
        when(request.getParameter("descrizione")).thenReturn("");
        Exception thrown = Assertions.assertThrows(ServletException.class, () -> {
            aggiuntaProdottoServlet.doPost(request, response);
        });
        assertTrue(thrown.getMessage().contains("Descrizione non valida."));
    }

    @Test
    void metodoFailureDescrizione() throws Exception
    {
        aggiuntaProdottoServlet = new AggiuntaProdottoServlet();
        when(request.getSession()).thenReturn(session);

        when(session.getAttribute("utente")).thenReturn(new UtenteDAO().doRetrieveByUsername("adminCatalogo"));
        when(request.getParameter("nome")).thenReturn("Casco");
        when(request.getParameter("idStr")).thenReturn("0000000013");
        when(request.getParameter("descrizione")).thenReturn("###");
        Exception thrown = Assertions.assertThrows(ServletException.class, () -> {
            aggiuntaProdottoServlet.doPost(request, response);
        });
        assertTrue(thrown.getMessage().contains("Descrizione non valida."));
    }

    @Test
    void metodoFailureLunghezzaCodice() throws Exception
    {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utente")).thenReturn(new UtenteDAO().doRetrieveByUsername("adminCatalogo"));
        when(request.getParameter("idStr")).thenReturn("12");

        Exception thrown = Assertions.assertThrows(ServletException.class, () -> {
            aggiuntaProdottoServlet.doPost(request, response);
        });
        assertTrue(thrown.getMessage().contains("Id non valido."));
    }

    @Test
    void metodoFailureFormatoCodice () throws Exception
    {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utente")).thenReturn(new UtenteDAO().doRetrieveByUsername("adminCatalogo"));
        when(request.getParameter("idStr")).thenReturn("12N");

        Exception thrown = Assertions.assertThrows(ServletException.class, () -> {
            aggiuntaProdottoServlet.doPost(request, response);
        });
        assertTrue(thrown.getMessage().contains("Id non valido."));
    }

    @Test
    void metodoFailureLunghezzaMarca() throws Exception
    {
        aggiuntaProdottoServlet = new AggiuntaProdottoServlet();
        when(request.getSession()).thenReturn(session);

        when(session.getAttribute("utente")).thenReturn(new UtenteDAO().doRetrieveByUsername("adminCatalogo"));
        when(request.getParameter("nome")).thenReturn("Casco");
        when(request.getParameter("idStr")).thenReturn("0000000013");
        when(request.getParameter("descrizione")).thenReturn("Un casco sfizioso");
        when(request.getParameter("marca")).thenReturn("Pi");
        Exception thrown = Assertions.assertThrows(ServletException.class, () -> {
            aggiuntaProdottoServlet.doPost(request, response);
        });
        assertTrue(thrown.getMessage().contains("Marca non valida."));
    }

    @Test
    void metodoFailureFormatoMarca() throws Exception
    {
        aggiuntaProdottoServlet = new AggiuntaProdottoServlet();
        when(request.getSession()).thenReturn(session);

        when(session.getAttribute("utente")).thenReturn(new UtenteDAO().doRetrieveByUsername("adminCatalogo"));
        when(request.getParameter("nome")).thenReturn("Casco");
        when(request.getParameter("idStr")).thenReturn("0000000013");
        when(request.getParameter("descrizione")).thenReturn("Un casco sfizioso");
        when(request.getParameter("marca")).thenReturn("Pi@@");
        Exception thrown = Assertions.assertThrows(ServletException.class, () -> {
            aggiuntaProdottoServlet.doPost(request, response);
        });
        assertTrue(thrown.getMessage().contains("Marca non valida."));
    }




}