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

@WebServlet("/Prodotto")
public class ProdottoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{

    }
    private final ProdottoDAO prodottoDAO= new ProdottoDAO();
    protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        String idStr = request.getParameter("id");
        if (idStr == null)
            throw new MyServletException("Id non valido");

        int id = Integer.parseInt(idStr);
        Prodotto prodotto = prodottoDAO.doRetrieveById(id);
        if(prodotto==null){
            throw new MyServletException("Prodotto non trovato");
        }

        request.setAttribute("prodotto",prodotto);

        RequestDispatcher requestDispatcher= request.getRequestDispatcher("WEB-INF/jsp/prodotto.jsp");
        requestDispatcher.forward(request,response);
    }
}
    