<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../Header.jsp">
    <jsp:param name="pageTitle" value="Registrazione utente"/>
</jsp:include>

<body>
<section>
<hr>
<form>
<h1>Bentornato ${utente.nome}</h1>
    <label>Nome</label>
    <input type="text" name="nome" value="${utente.nome}" readonly>
    <label>Cognome</label>
    <input type="text" name ="Cognome"value= "${utente.cognome}" readonly>
    <label>Email</label>
    <input type="text" name="Email"value= "${utente.email}"readonly>
    <label>Data di nascita</label>
    <input type="date" name="nascita" value="${utente.nascita}"readonly>
</form>
    <button onclick="window.location.href='ModificaDati.jsp'" type="button" class="btn btn-warning">Modifica dati</button>
<%@ include file="../../footer.html" %>
</section>
</body>
</html>