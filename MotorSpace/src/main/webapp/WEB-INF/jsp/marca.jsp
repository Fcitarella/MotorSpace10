<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="utf-8"%><%@ taglib prefix="c"
                                          uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html>
<jsp:include page="../../Header.jsp">
    <jsp:param name="pageTitle" value="Home"/>
</jsp:include>
<section>
    <h1>${marca.nome}</h1>
    <p>${marca.descrizione}</p>

    <grid>
        <c:forEach items="${prodotti}" var="prodotto">
            <div col="1/3">
                <a href="Prodotto?id=${prodotto.id}"><img src="img/prodotti/${prodotto.id}.jpg"></a>
            </div>
            <div col="2/3">
                <h3>
                    <a href="Prodotto?id=${prodotto.id}">${prodotto.nome}</a>
                </h3>
                <p>${prodotto.descrizione}</p>
                <h4>Prezzo:${prodotto.prezzo}&euro</h4>
            </div>
        </c:forEach>
        <c:if test="${empty prodotti}">
            <div col="1">Nessun prodotto per questa marca</div>
        </c:if>
    </grid>
</section>
<%@include file="../../footer.html"%>