/*package Servlet;

import javax.servlet.http.HttpServlet;

package Servlet;

import model.Amministratore;
import model.AmministratoreDAO;
import model.Utente;
import model.UtenteDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginAmministratoreServlet extends HttpServlet {

    private final AmministratoreDAO AmministratoreDAO= new AmministratoreDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String codice = request.getParameter("codice");
        Amministratore amministratore=null;
        if(username != null && password != null){
            amministratore = AmministratoreDAO.doRetrieveByUsernamePasswordCodice(username,password);
        }
        if(utente == null){
            throw new MyServletException("Username e/o password non validi.");
        }
        request.getSession().setAttribute("utente",utente);

        String dest = request.getHeader("referer");
        if(dest == null || dest.contains("/Login") || dest.trim().isEmpty()){
            dest = ".";
        }
        response.sendRedirect(dest);
    }
}
*/