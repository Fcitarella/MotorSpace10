package Servlet;

import model.Categoria;
import model.Prodotto;
import model.ProdottoDAO;
import model.Utente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet( "/AggiuntaProdotto")
public class AggiuntaProdottoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
    }

    private final ProdottoDAO prodottoDAO = new ProdottoDAO();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Prodotto prodotto;
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if (utente == null || !utente.getAdmin()) {
            throw new MyServletException("Utente non autorizzato");
            //prova github
        }
        List<Categoria> categorie = (List<Categoria>) getServletContext().getAttribute("categorie");
        String idStr = request.getParameter("idStr");
        if (!(idStr!= null && idStr.length() >= 3 && idStr.length()<=10 && idStr.matches("^[0-9]+$"))) {
            throw new MyServletException("Id non valido.");
        }
        String nome = request.getParameter("nome");
        if (!(nome.matches("([ a-zA-Z]{3,32})"))) {
            throw new MyServletException("Username non valido.");
        }
        String descrizione = request.getParameter("descrizione");
        if (!(descrizione != null && descrizione.trim().length() > 0 && descrizione.trim().length()<=128 && descrizione.matches("^[ a-zA-Z\u00C0-\u00ff]+$"))) {
            throw new MyServletException("Nome non valido.");
        }
        String marca = request.getParameter("marca");
        if (!(marca != null && marca.trim().length() > 0 && marca.trim().length()<=32 && marca.matches("^[ a-zA-Z\u00C0-\u00ff]+$"))) {
            throw new MyServletException("Nome non valido.");
        }
        String prezzo = request.getParameter("prezzo");

        String categoria = request.getParameter("addCategoria");
        int idCategoria= Integer.parseInt(categoria);
        System.out.println(categoria);
        if (nome != null && descrizione != null && marca != null && prezzo != null && categoria != null) {
            System.out.println(nome);
            prodotto = new Prodotto();
            prodotto.setNome(nome);
            prodotto.setDescrizione(descrizione);
            prodotto.setMarca(marca);
            prodotto.setPrezzo(Float.parseFloat(prezzo));
            prodotto.setId(idStr);
            prodotto.setCategoria(categoria);
            prodottoDAO.doSave(prodotto, idCategoria);
            request.setAttribute("notifica", "prodotto aggiunto con successo");
            }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("aggiuntaprodotto.jsp");
        requestDispatcher.forward(request,response);
    }
}