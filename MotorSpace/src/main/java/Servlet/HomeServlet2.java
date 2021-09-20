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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Home")
public class HomeServlet2 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ProdottoDAO prodottoDao= new ProdottoDAO();
    //Servlet per l'apertura della pagina home dopo la pressione del logo o della scritta home
    @Override
    public void init() throws ServletException {//caricamento delle varie categorie dei prodotti nella home page
        CategoriaDAO categoriaDAO= new CategoriaDAO();
        List<Categoria> categorie= new ArrayList<Categoria>();
        categorie= categoriaDAO.doRetrieveByMacro("Vestiario");
        getServletContext().setAttribute("categorie1",categorie);
        categorie= categoriaDAO.doRetrieveByMacro("PartiDiRicambio");
        getServletContext().setAttribute("categorie2",categorie);
        categorie= categoriaDAO.doRetrieveByMacro("AccessoriMoto");
        getServletContext().setAttribute("categorie3",categorie);
        super.init();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Prodotto> prodotti= prodottoDao.doRetrieveAll(0,10);//caricamento primi 10 prodotti nella home page
        request.setAttribute("Prodotti",prodotti);

        RequestDispatcher requestDispatcher= request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request,response);
    }
}