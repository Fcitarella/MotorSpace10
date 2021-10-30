package Servlet;

import javax.servlet.http.HttpServlet;

import model.Amministratore;
import model.AmministratoreDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/LoginAmministratoreServlet")
public class LoginAmministratoreServlet extends HttpServlet {

    private final AmministratoreDAO amministratoreDAO= new AmministratoreDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        System.out.println(id);
        if("home".equals(id)){
            RequestDispatcher requestDispatcher= request.getRequestDispatcher("WEB-INF/jsp/HomeAmministratore.jsp");
            requestDispatcher.forward(request,response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String codice = request.getParameter("codice");
        Amministratore amministratore = null;
        if(username != null && password != null){
            amministratore = amministratoreDAO.doRetrieveByUsernamePasswordCode(username,password,codice);
        }
        if(amministratore == null){
            throw new MyServletException("Username e/o password non validi.");
        }
        request.getSession().setAttribute("amministratore",amministratore);

        String dest = request.getHeader("referer");
        if(dest == null || dest.contains("/Login") || dest.trim().isEmpty()){
            dest = ".";
        }
        response.sendRedirect(dest);
    }
}
