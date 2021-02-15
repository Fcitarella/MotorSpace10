package Servlet;

import model.UtenteDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/VerificaUsername")
public class VerificaUsernameServlet extends HttpServlet {
    private final UtenteDAO utenteDAO = new UtenteDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String username = request.getParameter("username");
    response.setContentType("text/xml");
    if (username != null && username.length() >=6 && username.matches("^(0-9a-zA-Z)+$") && utenteDAO.doRetrieveByUsername(username) == null){
        response.getWriter().append("<ok/>");
    }else {
        response.getWriter().append("<no/>");
    }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
