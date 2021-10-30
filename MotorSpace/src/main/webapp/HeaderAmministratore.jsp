<%--
  Created by IntelliJ IDEA.
  User: Federico
  Date: 21/09/2021
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
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


        <ul>
            <div>
                <a href="Home"><img src="img/logo_small_1.png"></a>
            </div>
            <!-- Caricamento delle varie categorie di prodotti all'interno dell'header della home-->
            <li>
                <a>Vestiario</a>
                <menu>
                    <c:forEach items="${categorie1}" var="categorie1">
                        <menuitem>
                            <a href="Categoria?id=<c:out value="${categorie1.id}"/>"><c:out
                                    value="${categorie1.nome}" /></a>
                        </menuitem>
                    </c:forEach>
                </menu>
            </li>
            <li>
                <a>Parti Di Ricambio</a>
                <menu>
                    <c:forEach items="${categorie2}" var="categorie2">
                        <menuitem>
                            <a href="CategoriaParti?id=<c:out value="${categorie2.id}"/>"><c:out
                                    value="${categorie2.nome}" /></a>
                        </menuitem>
                    </c:forEach>
                </menu>
            </li>
            <li>
                <a>Accessori Moto</a>
                <menu>
                    <c:forEach items="${categorie3}" var="categorie3">
                        <menuitem>
                            <a href="CategoriaAccessori?id=<c:out value="${categorie3.id}"/>"><c:out
                                    value="${categorie3.nome}" /></a>
                        </menuitem>
                    </c:forEach>
                </menu>
            </li>
            </li>
            <li>
                <a>Offerte</a>
            </li>

            <li>
                <c:choose>
                    <c:when test="${amministratore == null}">
                        <!-- In caso l'utente non sia loggato verrà data la possibilità di effettuare il login mediante un menù a tendina-->
                        <a> <img src="img/logoLogin_2_2.png"> </a>
                        <menu>
                            <menuitem>
                                <card>
                                    <form action="LoginServlet" method="post">
                                        <input type="text" name="username" value="Spaghettino"><br>
                                        <input type="password" name="password" value="Ciaone55"><br>
                                        <input type="submit" value="Login">
                                    </form>
                                </card>
                            </menuitem>
                            <menuitem><a href="RegistrazioneForm">Registrazione</a> </menuitem>
                            <menuitem><a href="Home">Non sei un amministratore?Accedi come utente</a> </menuitem>
                        </menu>
                    </c:when>


                    <c:otherwise>
                        <!-- In caso l'utente sia loggato avrà la possibilità di controllare il proprio profilo o la pagina dei propri ordini -->
                        <a> <img src="img/logoLogin_2_2.png"> </a>
                        <menu>
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
            <li><a href="Carrello"><img src="img/carrello_rosso.png"></a></li>
            <li><form action="RicercaServlet" method="get" >
                <input type="text" name="q" ricerca="ricerca-datalist" placeholder="Ricerca" onkeyup="ricerca(this.value)" value="<c:out value ="${param.q}"/>">
                <datalist id="ricerca-datalist"></datalist>
            </form>
            </li>
        </ul>
    </label>
</nav>
