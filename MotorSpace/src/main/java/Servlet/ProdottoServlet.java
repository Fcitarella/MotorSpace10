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
        String id;
       try {
          id =request.getParameter("id");
       }catch (NumberFormatException e){
           throw new MyServletException("id prodotto non valido");
       }
           Prodotto prodotto = prodottoDAO.doRetrieveById(id);
        if(prodotto==null){
            throw new MyServletException("Prodotto non trovato");
        }
        request.setAttribute("prodotto",prodotto);

        RequestDispatcher requestDispatcher= request.getRequestDispatcher("prodotto.jsp");
        requestDispatcher.forward(request,response);
    }
}
    