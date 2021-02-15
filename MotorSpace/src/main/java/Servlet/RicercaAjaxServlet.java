package Servlet;

import model.Prodotto;
import model.ProdottoDAO;
import org.json.JSONArray;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet( "/RicercaAjax")
public class RicercaAjaxServlet extends HttpServlet {
    private final ProdottoDAO prodottoDAO = new ProdottoDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("q") + "*";
        List<Prodotto> prodotti = prodottoDAO.doRetrieveByNome(query, 0, 10);
        JSONArray prodJson = new JSONArray();
        for (Prodotto p : prodotti) {
            prodJson.put(p.getNome());
        }
        response.setContentType("application/json");
        response.getWriter().append(prodJson.toString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
