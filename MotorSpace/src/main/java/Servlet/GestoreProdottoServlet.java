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

@WebServlet( "/AdminProdotto")
public class GestoreProdottoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
    }
    private final ProdottoDAO prodottoDAO = new ProdottoDAO();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if(utente == null || !utente.getAdmin()) {
            throw new MyServletException("Utente non autorizzato");
        }
        String idStr = request.getParameter("id");
        if(idStr != null) {
            List<Categoria> categorie= (List<Categoria>) getServletContext().getAttribute("categorie");
            if (request.getParameter("rimuovi") != null){
                prodottoDAO.doDelete(idStr);
                request.setAttribute("notifica", "Prodotto rimosso con successo");
            }else{
                Prodotto prodotto;
                String nome = request.getParameter("nome");
                String descrizione = request.getParameter("descrizione");
                String marca = request.getParameter("marca");
                String prezzo = request.getParameter("prezzo");
                String categoria = request.getParameter("addCategoria");
                if(nome != null && descrizione != null && marca != null && prezzo != null && categoria!=null){
                    prodotto = new Prodotto();
                    prodotto.setId(idStr);
                    prodotto.setNome(nome);
                    prodotto.setDescrizione(descrizione);
                    prodotto.setMarca(marca);
                    prodotto.setPrezzo(Float.parseFloat(prezzo));
                    prodotto.setCategoria(categoria);

                    if (idStr.isEmpty()){
                        int idCategoria=0;
                        for(Categoria c:categorie){
                            if (c.getNome().equals(categoria))  {idCategoria = c.getId();}
                        }
                        System.out.println(idCategoria);
                        prodottoDAO.doSave(prodotto,idCategoria);
                        request.setAttribute("notifica", "prodotto aggiunto con successo");
                    }else {
                        int idCategoria=0;
                        for(Categoria c:categorie){
                            if (c.getNome().equals(categoria))  {idCategoria = c.getId();}
                        }
                        System.out.println("L'id che ci interessa e'"+idCategoria);
                        System.out.println(prodotto.toString());
                        prodottoDAO.doUpdate(prodotto,idCategoria);
                        request.setAttribute("notifica", "Prodotto modificato con successo.");
                    }
                }else{
                    prodotto = prodottoDAO.doRetrieveById(idStr);
                }
            request.setAttribute("prodotto", prodotto);
            }
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("adminprodotto.jsp");
        requestDispatcher.forward(request,response);
    }
}
