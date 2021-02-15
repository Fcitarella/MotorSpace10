package ServletTest;

import Servlet.MyServletException;
import Servlet.RegistrazioneServlet;
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
public class RegistrazioneServletTest {
    @Mock
    HttpServletResponse response;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpSession session;
    @Mock
    RequestDispatcher rd;
    @InjectMocks
    RegistrazioneServlet registrazioneServlet;

    @BeforeEach
    public void setup() throws FileNotFoundException, SQLException {
        new InitTestDb().initeDb();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        new InitTestDb().destroyDb();
    }

    @Test
    void testPost() throws Exception {
       registrazioneServlet = new RegistrazioneServlet();
        when(request.getSession()).thenReturn(session);

        when(request.getParameter("nome")).thenReturn("Raffaele");
        when(request.getParameter("username")).thenReturn("Rafchia98");
        when(request.getParameter("cognome")).thenReturn("Chiarolanza");
        when(request.getParameter("email")).thenReturn("chiarolanza15@gmail.com");
        when(request.getParameter("password")).thenReturn("Raffaelechiarolanza14");
        when(request.getParameter("passwordConferma")).thenReturn("Raffaelechiarolanza14");
        when(request.getParameter("nascita")).thenReturn("1998/01/01");
        when(request.getRequestDispatcher("Home")).thenReturn(rd);
        registrazioneServlet.doGet(request, response);
        Optional<Utente> utente = Optional.ofNullable(new UtenteDAO().doRetrieveByUsername("rafchia98"));
        assertTrue(utente.isPresent());
    }

    @Test
    void testPostUtenteLoggato() {
        when(request.getSession()).thenReturn(session);
        Utente utente = new UtenteDAO().doRetrieveByUsername("citax");
        when(session.getAttribute("utente")).thenReturn(utente);
        Exception thrown = Assertions.assertThrows(ServletException.class, () -> {
            registrazioneServlet.doPost(request, response);
        });
        assertTrue((thrown.getMessage().contains("Utente già loggato")));
    }

    @Test
    void doPostFailureNomeLunghezza() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nome")).thenReturn("Na");
        when(request.getParameter("username")).thenReturn("Camillo");
        when(request.getParameter("cognome")).thenReturn("Verdi");
        when(request.getParameter("email")).thenReturn("Lamia@email.com");
        when(request.getParameter("password")).thenReturn("Ciaone5!");
        when(request.getParameter("passwordConferma")).thenReturn("Ciaone5!");
        when(request.getParameter("nascita")).thenReturn("01/01/1998");
        when(request.getRequestDispatcher("Home")).thenReturn(rd);
        registrazioneServlet.doPost(request, response);
        Exception thrown = Assertions.assertThrows(ServletException.class, () -> {
            registrazioneServlet.doPost(request, response);
        });
        assertTrue((thrown.getMessage().contains("Lunghezza nome non conforme")));
    }

    @Test
    void doPostFailureNomeFormato() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nome")).thenReturn("Viola£");
        when(request.getParameter("username")).thenReturn("Camillo");
        when(request.getParameter("cognome")).thenReturn("Verdi");
        when(request.getParameter("email")).thenReturn("Lamia@email.com");
        when(request.getParameter("password")).thenReturn("Ciaone5!");
        when(request.getParameter("passwordConferma")).thenReturn("Ciaone5!");
        when(request.getParameter("nascita")).thenReturn("01/01/1998");
        when(request.getRequestDispatcher("Home")).thenReturn(rd);
        registrazioneServlet.doPost(request, response);
        Exception thrown = Assertions.assertThrows(ServletException.class, () -> {
            registrazioneServlet.doPost(request, response);
        });
        assertTrue((thrown.getMessage().contains("Formato nome non conforme")));
    }

    @Test
    void doPostFailureCognomeLunghezza() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nome")).thenReturn("Giovanni");
        when(request.getParameter("username")).thenReturn("Camillo");
        when(request.getParameter("cognome")).thenReturn("Ve");
        when(request.getParameter("email")).thenReturn("Lamia@email.com");
        when(request.getParameter("password")).thenReturn("Ciaone5!");
        when(request.getParameter("passwordConferma")).thenReturn("Ciaone5!");
        when(request.getParameter("nascita")).thenReturn("01/01/1998");
        when(request.getRequestDispatcher("Home")).thenReturn(rd);
        Exception thrown = Assertions.assertThrows(ServletException.class, () -> {
            registrazioneServlet.doPost(request, response);
        });
        assertTrue((thrown.getMessage().contains( "Lunghezza cognome non conforme")));
    }

    @Test
    void doPostFailureCognomeFormato() throws Exception{
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nome")).thenReturn("Giovanni");
        when(request.getParameter("username")).thenReturn("Camillo");
        when(request.getParameter("cognome")).thenReturn("Verdi#");
        when(request.getParameter("email")).thenReturn("Lamia@email.com");
        when(request.getParameter("password")).thenReturn("Ciaone5!");
        when(request.getParameter("passwordConferma")).thenReturn("Ciaone5!");
        when(request.getParameter("nascita")).thenReturn("01/01/1998");
        when(request.getRequestDispatcher("Home")).thenReturn(rd);
        Exception thrown = Assertions.assertThrows(ServletException.class, () -> {
            registrazioneServlet.doPost(request, response);
        });
        assertTrue((thrown.getMessage().contains("Formato cognome non conforme")));
    }
    @Test
    void doPostFailureEmailFormato()throws Exception
    {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nome")).thenReturn("Giovanni");
        when(request.getParameter("username")).thenReturn("Camillo");
        when(request.getParameter("cognome")).thenReturn("Verdi");
        when(request.getParameter("email")).thenReturn("Lamiaemail.com");
        when(request.getParameter("password")).thenReturn("Ciaone5!");
        when(request.getParameter("passwordConferma")).thenReturn("Ciaone5!");
        when(request.getParameter("nascita")).thenReturn("01/01/1998");
        when(request.getRequestDispatcher("Home")).thenReturn(rd);
        Exception thrown = Assertions.assertThrows(ServletException.class, () -> {
            registrazioneServlet.doPost(request, response);
        });
        assertTrue((thrown.getMessage().contains("Formato email non conforme")));
    }
    @Test
    void doPostFailureLunghezzaPassword() throws Exception
    {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nome")).thenReturn("Giovanni");
        when(request.getParameter("username")).thenReturn("Camillo");
        when(request.getParameter("cognome")).thenReturn("Verdi");
        when(request.getParameter("email")).thenReturn("Lamia@email.com");
        when(request.getParameter("password")).thenReturn("Cia");
        when(request.getParameter("passwordConferma")).thenReturn("Cia");
        when(request.getParameter("nascita")).thenReturn("01/01/1998");
        when(request.getRequestDispatcher("Home")).thenReturn(rd);
        Exception thrown = Assertions.assertThrows(ServletException.class, () -> {
            registrazioneServlet.doPost(request, response);
        });
        assertTrue((thrown.getMessage().contains("Lunghezza password non conforme")));
    }
    @Test
    void doPostFailureFormatoPassword()throws Exception
    {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nome")).thenReturn("Giovanni");
        when(request.getParameter("username")).thenReturn("Camillo");
        when(request.getParameter("cognome")).thenReturn("Verdi");
        when(request.getParameter("email")).thenReturn("Lamia@email.com");
        when(request.getParameter("password")).thenReturn("Ciaone");
        when(request.getParameter("passwordConferma")).thenReturn("Ciaone");
        when(request.getParameter("nascita")).thenReturn("01/01/1998");
        when(request.getRequestDispatcher("Home")).thenReturn(rd);
        Exception thrown = Assertions.assertThrows(ServletException.class, () -> {
            registrazioneServlet.doPost(request, response);
        });
        assertTrue((thrown.getMessage().contains("Formato password non conforme")));
    }
    @Test
    void doPostFailurePasswordDiverse()throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nome")).thenReturn("Giovanni");
        when(request.getParameter("username")).thenReturn("Camillo");
        when(request.getParameter("cognome")).thenReturn("Verdi");
        when(request.getParameter("email")).thenReturn("Lamia@email.com");
        when(request.getParameter("password")).thenReturn("Ciaone5!");
        when(request.getParameter("passwordConferma")).thenReturn("Ciaone6!");
        when(request.getParameter("nascita")).thenReturn("01/01/1998");
        when(request.getRequestDispatcher("Home")).thenReturn(rd);
        Exception thrown = Assertions.assertThrows(ServletException.class, () -> {
            registrazioneServlet.doPost(request, response);
        });
        assertTrue((thrown.getMessage().contains("Password inserite differenti")));
    }

    @Test
    void doPostFailureLunghezzaUsername()throws Exception
    {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nome")).thenReturn("Giovanni");
        when(request.getParameter("username")).thenReturn("ca");
        when(request.getParameter("cognome")).thenReturn("Verdi");
        when(request.getParameter("email")).thenReturn("Lamia@email.com");
        when(request.getParameter("password")).thenReturn("Ciaone5!");
        when(request.getParameter("passwordConferma")).thenReturn("Ciaone5!");
        when(request.getParameter("nascita")).thenReturn("01/01/1998");
        when(request.getRequestDispatcher("Home")).thenReturn(rd);
        Exception thrown = Assertions.assertThrows(ServletException.class, () -> {
            registrazioneServlet.doPost(request, response);
        });
        assertTrue((thrown.getMessage().contains("Lunghezza username non conforme")));
    }
    @Test
    void doPostFailureFormatoUsername()throws Exception
    {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nome")).thenReturn("Giovanni");
        when(request.getParameter("username")).thenReturn("Camillo#");
        when(request.getParameter("cognome")).thenReturn("Verdi");
        when(request.getParameter("email")).thenReturn("Lamia@email.com");
        when(request.getParameter("password")).thenReturn("Ciaone5!");
        when(request.getParameter("passwordConferma")).thenReturn("Ciaone5!");
        when(request.getParameter("nascita")).thenReturn("01/01/1998");
        when(request.getRequestDispatcher("Home")).thenReturn(rd);
        Exception thrown = Assertions.assertThrows(ServletException.class, () -> {
            registrazioneServlet.doPost(request, response);
        });
        assertTrue((thrown.getMessage().contains("Formato username non conforme")));
    }
}

