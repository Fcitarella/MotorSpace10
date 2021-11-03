package Servlet;

import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/VisualizzaOrdini")

public class VisualizzaOrdiniServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

    }
    private final OrdineDAO ordineDAO= new OrdineDAO();
    protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        HttpSession session = request.getSession();
        String username;
            Utente utente = (Utente) session.getAttribute("utente");
            if(utente != null)
            username = utente.getUsername();
            else throw new MyServletException("Nessun utente loggato");
        List<Ordine> ordini;
        ordini = ordineDAO.doRetrieveByUsername(username);
        if(ordini==null){
            throw new MyServletException("Ordine non trovato");
        }
        request.setAttribute("ordini",ordini);

        RequestDispatcher requestDispatcher= request.getRequestDispatcher("WEB-INF/jsp/Ordini.jsp");
        requestDispatcher.forward(request,response);
    }
}
