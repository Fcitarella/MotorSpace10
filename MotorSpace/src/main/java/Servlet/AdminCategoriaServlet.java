package Servlet;

import model.Amministratore;
import model.Categoria;
import model.CategoriaDAO;
import model.Utente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/AdminCategoria")
public class AdminCategoriaServlet extends HttpServlet {
    private final CategoriaDAO categoriaDAO = new CategoriaDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    Amministratore amministratore = (Amministratore) request.getSession().getAttribute("amministratore");
    if(amministratore == null){
        throw new MyServletException("Utente non autorizzato");
    }
    String idstr = request.getParameter("id");
    if(idstr != null){
        List<Categoria> categorie = (List<Categoria>) getServletContext().getAttribute("categoria");
        Categoria categoria= null;
        if(!idstr.isEmpty()){
            int id = Integer.parseInt(idstr);
            categoria = categorie.stream().filter(c-> c.getId() == id).findAny().get();
        }
        if(request.getParameter("rimuovi") != null){
            categoriaDAO.doDelete(categoria.getId());
            categorie.remove(categoria);
            request.setAttribute("notifica", "Categoria rimossa con successo");
        }else{
            String nome = request.getParameter("nome");
            String descrizione = request.getParameter("descrizione");
            if(nome != null && descrizione != null){
                if(categoria == null){
                    int id = Integer.parseInt(idstr);
                    categoria = new Categoria();
                    categoria.setId(id);
                    categoria.setNome(nome);
                    categoria.setDescrizione(descrizione);
                    categoriaDAO.doSave(categoria);
                }
            }else {
                categoria.setNome(nome);
                categoria.setDescrizione(descrizione);
                categoriaDAO.doUpdate(categoria);
                request.setAttribute("notifica", "categoria modificata con successo");
            }
        }
        request.setAttribute("categoria", categoria);
    }
    RequestDispatcher requestDispatcher = request.getRequestDispatcher("amministrazionecategoria.jsp");
    requestDispatcher.forward(request,response);
    }

}
