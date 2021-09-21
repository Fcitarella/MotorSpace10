package Servlet;

import model.Utente;
import model.UtenteDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/Registrazione")
public class RegistrazioneServlet extends HttpServlet {

    private UtenteDAO utenteDAO = new UtenteDAO();
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("utente") != null){
            throw new MyServletException("Utente loggato");
        }
        String username = request.getParameter("username");
        if (!(username != null && username.matches("([0-9a-zA-Z]{6,30})"))) {
            throw new MyServletException("Username non valido.");
        }

        String password = request.getParameter("password");
        if (!(password != null && password.length() >= 8 && !password.toUpperCase().equals(password)
                && !password.toLowerCase().equals(password) && password.matches(".*[0-9].*"))) {
            throw new MyServletException("Password non valida.");
        }

        String passwordConferma = request.getParameter("passwordConferma");
        if (!password.equals(passwordConferma)) {
            throw new MyServletException("Password e conferma differenti.");
        }

        String nome = request.getParameter("nome");
        if (!(nome != null && nome.trim().length() > 0 && nome.matches("([a-zA-Z]{0,30})"))) {
            throw new MyServletException("Nome non valido.");
        }
        String cognome = request.getParameter("cognome");
        if (!(cognome != null && cognome.trim().length() > 0 && cognome.matches("([ a-zA-Z]{0,30})"))) {
            throw new MyServletException("Nome non valido.");
        }
        String email = request.getParameter("email");
        if (!(email != null && email.matches("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w+)"))) {
            throw new MyServletException("Email non valida.");
        }

            Utente utente = new Utente();
            utente.setUsername(username);
            utente.setNome(nome);
            utente.setCognome(cognome);
            utente.setPassword(password);
            utente.setEmail(email);
            System.out.println(utente.getNome());
            utenteDAO.doSave(utente);
            request.getSession().setAttribute("utente",utente);


        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Home");
        requestDispatcher.forward(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
