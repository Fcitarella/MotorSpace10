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
    <jsp:param name="pageTitle" value="Ordini"/>
</jsp:include>
<section>
    <table>
        <thead>
        <tr>
            <th>Numero ordine</th>
            <th>Data Ordine</th>
            <th>Data spedizione</th>
            <th>Data consegna</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${ordini}" var="ordine">
        <tr>
            <td>${ordine.id}</td>
            <td>${ordine.dataOrdine}</td>
            <td>${ordine.dataSpedizione}</td>
            <td>${ordine.dataConsegna}</td>
            <td><a href="visualizzaOrdine?id=${ordine.id}" btn primary="Dettagli"/></td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</section>
<%@include file="../footer.html"%>