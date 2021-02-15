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
        when(session.getAttribute("utente")).thenReturn("Pasquae88");
        when(request.getParameter("nome")).thenReturn("Casco");
        when(request.getParameter("id")).thenReturn("NIEN3DF923");
        when(request.getParameter("descrizione")).thenReturn("Un casco sfizioso");
        when(request.getParameter("prezzo")).thenReturn("45");
        when(request.getParameter("marca")).thenReturn("Pirelli");
        when(request.getParameter("categoria")).thenReturn("casco");
        when(request.getRequestDispatcher("aggiuntaprodotto.jsp")).thenReturn(rd);
        aggiuntaProdottoServlet.doPost(request,response);
        Optional<Prodotto> prodotto = Optional.ofNullable(new ProdottoDAO().doRetrieveById("NIEN3DF923"));
        assertTrue(prodotto.isPresent());
    }
    @Test
    void metodoFailuerNomeLunghezza() throws Exception
    {
        aggiuntaProdottoServlet = new AggiuntaProdottoServlet();

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utente")).thenReturn("Pasquae88");
        when(request.getParameter("nome")).thenReturn("ed");
        when(request.getParameter("id")).thenReturn("NIEN3DF923");
        when(request.getParameter("descrizione")).thenReturn("Un casco sfizioso");
        when(request.getParameter("prezzo")).thenReturn("45");
        when(request.getParameter("marca")).thenReturn("Pirelli");
        when(request.getParameter("categoria")).thenReturn("casco");
        when(request.getRequestDispatcher("Home")).thenReturn(rd);
        Exception thrown = Assertions.assertThrows(ServletException.class, () -> {
            aggiuntaProdottoServlet.doPost(request, response);
        });
        assertTrue(thrown.getMessage().contains("Lunghezza nome non valida"));
    }

    @Test
    void metodoFailureNomeFormato() throws Exception
    {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nome")).thenReturn("casco@12");
        when(request.getParameter("id")).thenReturn("NIEN3DF923");
        when(request.getParameter("descrizione")).thenReturn("Un casco sfizioso");
        when(request.getParameter("prezzo")).thenReturn("45");
        when(request.getParameter("marca")).thenReturn("Pirelli");
        when(request.getParameter("categoria")).thenReturn("casco");
        when(request.getRequestDispatcher("Home")).thenReturn(rd);
        Exception thrown = Assertions.assertThrows(ServletException.class, () -> {
            aggiuntaProdottoServlet.doPost(request, response);
        });
        assertTrue(thrown.getMessage().contains("Formato nome non valido"));
    }

    @Test
    void metodoFailureCategoriaLunghezza()throws Exception
    {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nome")).thenReturn("Casco837");
        when(request.getParameter("id")).thenReturn("NIEN3DF923");
        when(request.getParameter("descrizione")).thenReturn("Un casco sfizioso");
        when(request.getParameter("prezzo")).thenReturn("45");
        when(request.getParameter("marca")).thenReturn("Pirelli");
        when(request.getParameter("categoria")).thenReturn("ca");
        when(request.getRequestDispatcher("Home")).thenReturn(rd);
        Exception thrown = Assertions.assertThrows(ServletException.class, () -> {
            aggiuntaProdottoServlet.doPost(request, response);
        });
        assertTrue(thrown.getMessage().contains("Lunghezza categoria non valida"));
    }

    @Test
    void metodoFailureCategoriaFormato() throws Exception
    {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nome")).thenReturn("Casco837");
        when(request.getParameter("id")).thenReturn("NIEN3DF923");
        when(request.getParameter("descrizione")).thenReturn("Un casco sfizioso");
        when(request.getParameter("prezzo")).thenReturn("45");
        when(request.getParameter("marca")).thenReturn("Pirelli");
        when(request.getParameter("categoria")).thenReturn("casco!");
        when(request.getRequestDispatcher("Home")).thenReturn(rd);
        Exception thrown = Assertions.assertThrows(ServletException.class, () -> {
            aggiuntaProdottoServlet.doPost(request, response);
        });
        assertTrue(thrown.getMessage().contains("Formato categoria non valido"));
    }

    @Test
    void metodoFailureDescrizioneLunghezza() throws Exception
    {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nome")).thenReturn("Casco837");
        when(request.getParameter("id")).thenReturn("NIEN3DF923");
        when(request.getParameter("descrizione")).thenReturn("w");
        when(request.getParameter("prezzo")).thenReturn("45");
        when(request.getParameter("marca")).thenReturn("Pirelli");
        when(request.getParameter("categoria")).thenReturn("casco");
        when(request.getRequestDispatcher("Home")).thenReturn(rd);
        Exception thrown = Assertions.assertThrows(ServletException.class, () -> {
            aggiuntaProdottoServlet.doPost(request, response);
        });
        assertTrue(thrown.getMessage().contains("Lunghezza descrizione non valida"));
    }

    @Test
    void metodoFailureDescrizione() throws Exception
    {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nome")).thenReturn("Casco837");
        when(request.getParameter("id")).thenReturn("NIEN3DF923");
        when(request.getParameter("descrizione")).thenReturn("Un casco sfizioso@@@");
        when(request.getParameter("prezzo")).thenReturn("45");
        when(request.getParameter("marca")).thenReturn("Pirelli");
        when(request.getParameter("categoria")).thenReturn("casco");
        when(request.getRequestDispatcher("Home")).thenReturn(rd);
        Exception thrown = Assertions.assertThrows(ServletException.class, () -> {
            aggiuntaProdottoServlet.doPost(request, response);
        });
        assertTrue(thrown.getMessage().contains("Formato descrizione non valido"));
    }

    @Test
    void metodoFailureLunghezzaCodice() throws Exception
    {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nome")).thenReturn("Casco837");
        when(request.getParameter("id")).thenReturn("N23ift");
        when(request.getParameter("descrizione")).thenReturn("Un casco sfizioso");
        when(request.getParameter("prezzo")).thenReturn("45");
        when(request.getParameter("marca")).thenReturn("Pirelli");
        when(request.getParameter("categoria")).thenReturn("casco");
        when(request.getRequestDispatcher("Home")).thenReturn(rd);
        Exception thrown = Assertions.assertThrows(ServletException.class, () -> {
            aggiuntaProdottoServlet.doPost(request, response);
        });
        assertTrue(thrown.getMessage().contains("Lunghezza codice non valida"));
    }

    @Test
    void metodoFailureFormatoCodice () throws Exception
    {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nome")).thenReturn("Casco837");
        when(request.getParameter("id")).thenReturn("Dfb!”£$!wq");
        when(request.getParameter("descrizione")).thenReturn("Un casco sfizioso");
        when(request.getParameter("prezzo")).thenReturn("45");
        when(request.getParameter("marca")).thenReturn("Pirelli");
        when(request.getParameter("categoria")).thenReturn("casco");
        when(request.getRequestDispatcher("Home")).thenReturn(rd);
        Exception thrown = Assertions.assertThrows(ServletException.class, () -> {
            aggiuntaProdottoServlet.doPost(request, response);
        });
        assertTrue(thrown.getMessage().contains("Formato codice non valido"));
    }

    @Test
    void metodoFailureLunghezzaMarca() throws Exception
    {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nome")).thenReturn("Casco837");
        when(request.getParameter("id")).thenReturn("NIEN3DF923");
        when(request.getParameter("descrizione")).thenReturn("Un casco sfizioso");
        when(request.getParameter("prezzo")).thenReturn("45");
        when(request.getParameter("marca")).thenReturn("Pi");
        when(request.getParameter("categoria")).thenReturn("casco");
        when(request.getRequestDispatcher("Home")).thenReturn(rd);
        Exception thrown = Assertions.assertThrows(ServletException.class, () -> {
            aggiuntaProdottoServlet.doPost(request, response);
        });
        assertTrue(thrown.getMessage().contains("Lunghezza marca non valida"));
    }

    @Test
    void metodoFailureFormatoMarca() throws Exception
    {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nome")).thenReturn("Casco837");
        when(request.getParameter("id")).thenReturn("NIEN3DF923");
        when(request.getParameter("descrizione")).thenReturn("Un casco sfizioso");
        when(request.getParameter("prezzo")).thenReturn("45");
        when(request.getParameter("marca")).thenReturn("Pirelli!");
        when(request.getParameter("categoria")).thenReturn("casco");
        when(request.getRequestDispatcher("Home")).thenReturn(rd);
        Exception thrown = Assertions.assertThrows(ServletException.class, () -> {
            aggiuntaProdottoServlet.doPost(request, response);
        });
        assertTrue(thrown.getMessage().contains("Formato marca non valido"));
    }


    @Test
    void metodoFailureFormatoPrezzo() throws Exception
    {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nome")).thenReturn("Casco837");
        when(request.getParameter("id")).thenReturn("NIEN3DF923");
        when(request.getParameter("descrizione")).thenReturn("Un casco sfizioso");
        when(request.getParameter("prezzo")).thenReturn("-2");
        when(request.getParameter("marca")).thenReturn("Pirelli");
        when(request.getParameter("categoria")).thenReturn("casco");
        when(request.getRequestDispatcher("Home")).thenReturn(rd);
        Exception thrown = Assertions.assertThrows(ServletException.class, () -> {
            aggiuntaProdottoServlet.doPost(request, response);
        });
        assertTrue(thrown.getMessage().contains("Formato prezzo non valido"));
    }

}