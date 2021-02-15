package Servlet;

import model.Prodotto;
import model.ProdottoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/RicercaServlet")
public class
RicercaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    private final ProdottoDAO prodottoDAO=new ProdottoDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Prodotto> prodotti= prodottoDAO.doRetrieveByNomeOrDescrizione(request.getParameter("q"),0,10);
        request.setAttribute("prodotti",prodotti);
        RequestDispatcher requestDispatcher= request.getRequestDispatcher("ricerca.jsp");
        requestDispatcher.forward(request,response);
    }
}
