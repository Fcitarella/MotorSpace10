package Servlet;

import model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@WebServlet("/Pagamento")
public class AcquistoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        if(carrello == null){
            throw new MyServletException("Nessun prodotto nel carrello");
        }
        Utente u;
        u =(Utente) session.getAttribute("utente");
        if(u == null){
            throw new MyServletException("Devi prima loggarti");
        }
        Ordine o = new Ordine();
        o.setCliente(u.getUsername());
        Date dataodierna = new Date(System.currentTimeMillis());
        o.setDataOrdine(dataodierna);
        o.setDataSpedizione(dataodierna);
        dataodierna.setTime(dataodierna.getDay()+7);
        o.setDataConsegna(dataodierna);
        OrdineDAO ordineDAO = new OrdineDAO();
        int id = ordineDAO.doSave(o);
        LineaOrdine l = new LineaOrdine();
        List<Carrello.ProdottoQuantità> pq = carrello.getProdottiArray();
        Carrello.ProdottoQuantità prova = pq.get(0);
        LineaOrdineDAO lineaOrdineDAO = new LineaOrdineDAO();
        for(Carrello.ProdottoQuantità p:pq){

            l.setIdOrdine(id);
            l.setIdProdotto(p.getProdotto().getId());
            l.setQuantità(p.getQuantità());
            l.setPrezzoUnitario(p.getProdotto().getPrezzo());
            lineaOrdineDAO.doSave(l);
            carrello.remove(p.getProdotto().getId());
        }

       RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/Passo2Pagamento.jsp");
        requestDispatcher.forward(request,response);
    }

}
