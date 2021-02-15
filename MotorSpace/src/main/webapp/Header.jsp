<%@ page import="java.util.List" %>
<%@ page import="model.Categoria" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype HTML>
<html lang="en">
<head>
    <title>MotorSpace - ${param.pageTitle}</title>

    <!-- meta -->
    <meta charset=utf-8>
    <meta name="description" content="BareCSS template file">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no"/>

    <!-- css -->

  <link href="bare.min.css" rel="stylesheet" type="text/css">
    <script src="Ricerca.js"></script>
</head>
<body>
<nav><!-- use fx attribute for fixed positioning -->
    <label>
        <header>
            <a href="Home"><img src="img/NewLogo.png">MotorSpace</a>
        </header>

        <ul>
            <li><form action="RicercaServlet" method="get" >
                <input type="text" name="q" ricerca="ricerca-datalist" placeholder="Ricerca" onkeyup="ricerca{this.value}" value="<c:out value ="${param.q}"/>">
                <datalist id="ricerca-datalist"></datalist>
            </form>
            </li>
            <li><a href="Home">Home</a></li>
            <li>
                <a>Categorie</a>
                <menu>
                    <c:forEach items="${categorie}" var="categorie">
                        <menuitem>
                            <a href="Categoria?id=<c:out value="${categorie.id}"/>"><c:out
                                    value="${categorie.nome}" /></a></menuitem>
                    </c:forEach>
                </menu>
            </li>
            <li><a href="Carrello">Carrello</a> </li>
            <li>
                <c:choose>
                    <c:when test="${utente == null}">
                        <a> Login </a>
                        <menu>
                            <menuitem>
                                <card>
                                    <form action="LoginServlet" method="post">
                                        <input type="text" name="username" placeholder="Username"><br>
                                        <input type="password" name="password" placeholder="Password"><br>
                                        <input type="submit" value="Login">
                                    </form>
                                </card>
                            </menuitem>
                            <menuitem><a href="RegistrazioneForm">Registrazione</a> </menuitem>
                        </menu>
                    </c:when>
                    <c:otherwise>
                        <a>${utente.admin ? 'Admin' : 'Account'}</a>
                        <menu>
                            <c:if test="${utente.admin}">
                                <menuitem><a href="AggiuntaProdotto">Aggiungi Prodotto</a> </menuitem>
                                <menuitem><a href="Ordini">Ordini</a> </menuitem>
                                <menuitem><a href="AdminOfferte" >Offerte</a> </menuitem>
                                <hr style="margin: 0px;">
                            </c:if>
                            ${utente.nome}
                            <menuitem><a href="profilo.jsp">Profilo</a> </menuitem>
                            <menuitem><a href="todo">I miei ordini</a> </menuitem>
                            <menuitem>
                                <card>
                                    <form action="Logout">
                                        <input type="submit" value="Logout">
                                    </form>
                                </card>
                            </menuitem>
                        </menu>
                    </c:otherwise>
                </c:choose>
            </li>
        </ul>
    </label>
</nav>
