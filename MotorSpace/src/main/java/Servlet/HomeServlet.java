package Servlet;

import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name= "HomeServlet ",urlPatterns = "",loadOnStartup = 1)
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ProdottoDAO prodottoDao= new ProdottoDAO();

    @Override
    public void init() throws ServletException {
        CategoriaDAO categoriaDAO= new CategoriaDAO();
        List<Categoria> categorie= new ArrayList<>();
        categorie= categoriaDAO.doRetrieveByMacro("Abbigliamento");
        getServletContext().setAttribute("categorie1",categorie);
        categorie= categoriaDAO.doRetrieveByMacro("Parti");
        getServletContext().setAttribute("categorie2",categorie);
        categorie= categoriaDAO.doRetrieveByMacro("Accessori");
        getServletContext().setAttribute("categorie3",categorie);
        super.init();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Prodotto> prodotti= prodottoDao.doRetrieveAll(0,10);
        request.setAttribute("Prodotti",prodotti);

        RequestDispatcher requestDispatcher= request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request,response);
    }
}
