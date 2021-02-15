package ServletTest;

import Servlet.LoginServlet;


import org.junit.jupiter.api.AfterEach;

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
public class LoginServletTest {
    @Mock
    HttpServletResponse response;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpSession session;
    @Mock
    RequestDispatcher rd;
    @InjectMocks
    LoginServlet loginServlet = new LoginServlet();

    @BeforeEach
    public void setup() throws FileNotFoundException, SQLException {
        new InitTestDb().initeDb();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        new InitTestDb().destroyDb();
    }
    @Test
     void testPost()throws Exception{
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("username")).thenReturn("carmelo45");
        when(request.getParameter("password")).thenReturn("napoli1980");
        assertDoesNotThrow (() -> loginServlet.doPost(request,response));
    }
    @Test
    void testPostUsernameErrato() throws Exception{
        when(request.getParameter("username")).thenReturn("carmelo5");
        when(request.getParameter("password")).thenReturn("napoli1980");
        Exception thrown = assertThrows(ServletException.class, () ->{
            loginServlet.doPost(request,response);        });
        assertTrue(thrown.getMessage().contains("Username e/o password non validi."));
    }
    @Test
     void testPostPasswordErrato() throws Exception{

        when(request.getParameter("username")).thenReturn("carmelo45");
        when(request.getParameter("password")).thenReturn("napoli");
        Exception thrown = assertThrows(ServletException.class, () ->{
            loginServlet.doPost(request,response);
        });
        assertTrue(thrown.getMessage().contains("Username e/o password non validi."));
    }
}
