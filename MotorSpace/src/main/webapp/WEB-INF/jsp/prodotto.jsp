
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="Header.jsp">
    <jsp:param name="productPage" value="Home"/>
</jsp:include>
<div col="1/3">
    <img src="img/prodotto${prodotto.id}.jpg">
</div>
<div col="1/3">
    <h1>
        <a href="a">${prodotto.nome}</a>
    </h1>
    ${prodotto.descrizione}
</div>
<div col="3/3">
    <c:if test="${Amministratore != null}">
        <form action="AdminProdotto" method="post">
            <input type="hidden" name="id" value="${prodotto.id}">
            <input type="submit" value="modifica">
            <input type="submit" name="rimuovi" value="Rimuovi">
        </form>
    </c:if>
    <p>Marca: ${prodotto.marca}
        </p>
<h4>Prezzo: ${prodotto.prezzo} &euro; </h4>
<form action="Carrello" method="post">
        <label>Quantità:</label>
        <select name="addNum">
            <c:forEach begin="1" end="30" varStatus="loop">
                <option value="${loop.index}">${loop.index}</option>
            </c:forEach>
        </select>
        <input type="hidden" name="prodId" value="${prodotto.id}">
        <input type="submit" value="Aggiungi al carrello">
    </form>
</div>
<%@include file="../footer.html"%>


