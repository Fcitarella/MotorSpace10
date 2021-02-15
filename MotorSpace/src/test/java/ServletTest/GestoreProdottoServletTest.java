/*package ServletTest;

import Servlet.AggiuntaProdottoServlet;


import Servlet.RegistrazioneServlet;
import model.Prodotto;
import model.ProdottoDAO;
import model.Utente;
import model.UtenteDAO;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
    AggiuntaProdottoServlet aggiuntaProdottoServlet = new AggiuntaProdottoServlet();

    @BeforeEach
    public void setup() throws FileNotFoundException, SQLException {
        new InitTestDb().initeDb();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        new InitTestDb().destroyDb();
    }
public class GestoreProdottoServletTest {
    @Test
    void metodoTest() throws Exception
    {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nome")).thenReturn("Casco837");
        when(request.getParameter("descrizione")).thenReturn("Un casco sfizioso");
        when(request.getParameter("prezzo")).thenReturn("45");
        when(request.getParameter("marca")).thenReturn("Pirelli");
        when(request.getParameter("categoria")).thenReturn("casco");
        when(request.getRequestDispatcher("Home")).thenReturn(rd);
    }

    @Test
    void metodoFailuerNomeLunghezza() throws Exception
    {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nome")).thenReturn("ed");
        when(request.getParameter("descrizione")).thenReturn("Un casco sfizioso");
        when(request.getParameter("prezzo")).thenReturn("45");
        when(request.getParameter("marca")).thenReturn("Pirelli");
        when(request.getParameter("categoria")).thenReturn("casco");
        when(request.getRequestDispatcher("Home")).thenReturn(rd);
    }

    @Test
    void metodoFailureNomeFormato() throws Exception
    {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nome")).thenReturn("casco@12");
        when(request.getParameter("descrizione")).thenReturn("Un casco sfizioso");
        when(request.getParameter("prezzo")).thenReturn("45");
        when(request.getParameter("marca")).thenReturn("Pirelli");
        when(request.getParameter("categoria")).thenReturn("casco");
        when(request.getRequestDispatcher("Home")).thenReturn(rd);
    }

    @Test
    void metodoFailureCategoriaLunghezza() throws Exception
    {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nome")).thenReturn("Casco837");
        when(request.getParameter("descrizione")).thenReturn("Un casco sfizioso");
        when(request.getParameter("prezzo")).thenReturn("45");
        when(request.getParameter("marca")).thenReturn("Pirelli");
        when(request.getParameter("categoria")).thenReturn("ca");
        when(request.getRequestDispatcher("Home")).thenReturn(rd);
    }

    @Test
    void metodoFailureCategoriaFormato() throws Exception
    {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nome")).thenReturn("Casco837");
        when(request.getParameter("descrizione")).thenReturn("Un casco sfizioso");
        when(request.getParameter("prezzo")).thenReturn("45");
        when(request.getParameter("marca")).thenReturn("Pirelli");
        when(request.getParameter("categoria")).thenReturn("casco!");
        when(request.getRequestDispatcher("Home")).thenReturn(rd);
    }

    @Test
    void metodoFailureDescrizioneLunghezza() throws Exception
    {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nome")).thenReturn("Casco837");
        when(request.getParameter("descrizione")).thenReturn("w");
        when(request.getParameter("prezzo")).thenReturn("45");
        when(request.getParameter("marca")).thenReturn("Pirelli");
        when(request.getParameter("categoria")).thenReturn("casco");
        when(request.getRequestDispatcher("Home")).thenReturn(rd);
    }

    @Test
    void metodoFailureDescrizioneFormato() throws Exception
    {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nome")).thenReturn("Casco837");
        when(request.getParameter("descrizione")).thenReturn("Un casco sfizioso@@@");
        when(request.getParameter("prezzo")).thenReturn("45");
        when(request.getParameter("marca")).thenReturn("Pirelli");
        when(request.getParameter("categoria")).thenReturn("casco");
        when(request.getRequestDispatcher("Home")).thenReturn(rd);
    }


    @Test
    void metodoFailureLunghezzaMarca() throws Exception
    {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nome")).thenReturn("Casco837");
        when(request.getParameter("descrizione")).thenReturn("Un casco sfizioso");
        when(request.getParameter("prezzo")).thenReturn("45");
        when(request.getParameter("marca")).thenReturn("Pi");
        when(request.getParameter("categoria")).thenReturn("casco");
        when(request.getRequestDispatcher("Home")).thenReturn(rd);
    }

    @Test
    void metodoFailureFormatoMarca() throws Exception
    {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nome")).thenReturn("Casco837");
        when(request.getParameter("descrizione")).thenReturn("Un casco sfizioso");
        when(request.getParameter("prezzo")).thenReturn("45");
        when(request.getParameter("marca")).thenReturn("Pirelli!");
        when(request.getParameter("categoria")).thenReturn("casco");
        when(request.getRequestDispatcher("Home")).thenReturn(rd);
    }


    @Test
    void metodoFailureFormatoPrezzo() throws Exception
    {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nome")).thenReturn("Casco837");
        when(request.getParameter("descrizione")).thenReturn("Un casco sfizioso");
        when(request.getParameter("prezzo")).thenReturn(-2);
        when(request.getParameter("marca")).thenReturn("Pirelli");
        when(request.getParameter("categoria")).thenReturn("casco");
        when(request.getRequestDispatcher("Home")).thenReturn(rd);
    }
}*/
