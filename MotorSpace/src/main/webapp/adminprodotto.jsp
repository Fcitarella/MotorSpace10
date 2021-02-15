
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var ="operazione" value="${param.rimuovi != null ? 'Rimozione' : (prodotto == null ? 'Aggiungi' : 'Modifica')}"/>
<jsp:include page="Header.jsp">
    <jsp:param name="pageTitle" value="${operazione}prodotto"/>
</jsp:include>
<section>
    <h1>${operazione} prodotto</h1>
    <h5>${notifica}</h5>
    <c:if test="${param.rimuovi == null}">
        <form action="AdminProdotto" method="post">
            <input type="hidden" name="id" value="${prodotto.id}">
            <label>Categorie</label>
            <select name="addCategoria">
                <c:forEach items="${categorie}" var="categoria" >
                    <option value="${categoria.nome}">${categoria.nome}</option>
                </c:forEach>
            </select>
            <br>
            <label>Nome</label>
            <input type="text" name="nome" value="${prodotto.nome}">
            <label>Descrizione</label>
            <textarea name ="descrizione">${prodotto.descrizione}</textarea>
            <label>Marca</label>
            <textarea name="marca">${prodotto.marca}</textarea>
            <label>Prezzo</label>
            <input type="number" name="prezzo" value="${prodotto.prezzo}">
            <label>Immagine</label>
            <h5>Upload</h5>
            <input type="submit" value="${operazione}">
            <c:if test="${prodotto != null}">
                <input type="submit" name="rimuovi" value="Rimuovi">
            </c:if>
        </form>
    </c:if>
</section>
<%@include file="footer.html"%>