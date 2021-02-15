package Servlet;
import model.Categoria;
import model.CategoriaDAO;
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

@WebServlet("/Home")
public class HomeServlet2 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ProdottoDAO prodottoDao= new ProdottoDAO();
    @Override
    public void init() throws ServletException {
        CategoriaDAO categoriaDAO= new CategoriaDAO();
        List<Categoria> categorie= categoriaDAO.doRetrieveAll();
        getServletContext().setAttribute("categorie",categorie);
        super.init();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Prodotto> prodotti= prodottoDao.doRetrieveAll(0,10);
        request.setAttribute("Prodotti",prodotti);

        RequestDispatcher requestDispatcher= request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request,response);
    }
}