<%--
  Created by IntelliJ IDEA.
  User: Federico
  Date: 21/09/2021
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="Header.jsp">
    <jsp:param name="pageTitle" value="Registrazione utente"/>
</jsp:include>

<body>
<section>
    <hr>
    <form>
        <h1>Pagina Ordini</h1>

       <li> <a>Nome</a>
        </li>
        <li><a>Cognome</a>
        </li>

        <label>Email</label>
        <input type="text" name="Email"value= "${utente.email}"readonly>
        <label>Data di nascita</label>
        <input type="date" name="nascita" value="${utente.nascita}"readonly>
    </form>
    <button onclick="window.location.href='ModificaDati.jsp'" type="button" class="btn btn-warning">Modifica dati</button>
    <%@ include file="footer.html" %>
</section>
</body>
</html>