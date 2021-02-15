<%--
  Created by IntelliJ IDEA.
  User: Federico
  Date: 06/01/2021
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" isErrorPage="true" %>
<jsp:include page="Header.jsp">
    <jsp:param name="pageTitle" value="Errore"/>
</jsp:include>
<section>
    <h1><%= exception.getMessage()%></h1>
</section>
<%@include file="footer.html"%>