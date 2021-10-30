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

@WebServlet("/CategoriaParti")
public class Categoria2Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
    }
    private final ProdottoDAO prodottoDAO= new ProdottoDAO();

    protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        @SuppressWarnings("unchecked")

        List<Categoria> categorie= (List<Categoria>) getServletContext().getAttribute("categorie2");
        int id=Integer.parseInt(request.getParameter("id"));
        request.setAttribute("categoria",categorie.stream().filter(c -> c.getId() == id).findAny().get());
        String pagstr = request.getParameter("pag");
        int pag = pagstr == null ? 1 : Integer.parseInt(pagstr);
        request.setAttribute("pag", pag);

        int perpag = 10;

        int totaleprodotti = prodottoDAO.countByCategoria(id);
        int npag = (totaleprodotti + perpag -1) / perpag;
        request.setAttribute("npag", npag);

        String ordStr = request.getParameter("ord");
        ProdottoDAO.OrderBy ord = ordStr == null ? ProdottoDAO.OrderBy.DEFAULT : ProdottoDAO.OrderBy.valueOf(ordStr);
        request.setAttribute("ord",ord);

        List<Prodotto> prodotti= prodottoDAO.doRetrieveByCategoria(id,ord, (pag -1)* perpag,perpag );
        request.setAttribute("prodotti",prodotti);

        RequestDispatcher requestDispatcher =request.getRequestDispatcher("WEB-INF/jsp/categoria.jsp");
        requestDispatcher.forward(request,response);
    }
}
