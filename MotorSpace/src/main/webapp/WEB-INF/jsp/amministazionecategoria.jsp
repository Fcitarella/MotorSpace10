<%--
  Created by IntelliJ IDEA.
  User: Federico
  Date: 01/02/2021
  Time: 21:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="operazione" value="${param.rimuovi != null ? 'Rimozione' : (categoria == null ? 'Aggiungi' : 'Modifica')}"/>
<jsp:include page="../../Header.jsp">
    <jsp:param name="pageTitle" value="${operazione} categoria"/>
</jsp:include>
<section>
    <h1>${operazione} categoria</h1>
    <h5>${notifica}</h5>
    <c:if test="${param.rimuovi == null}">
        <form action="AdminCategoriaServlet" method="post">
            <input type="hidden" name="id" value="${categoria.id}">
            <label>Nome</label>
            <input type="text" name="nome" value="${categoria.nome}"><br>
            <label>Descrizione</label>
            <textarea name="descrizione">${categoria.descrizione}</textarea>
            <input type="submit" value="${operazione}">
            <c:if test="${categoria != null}">
                <input type="submit" name="rimuovi" value="Rimuovi">
            </c:if>
        </form>
    </c:if>
</section>
