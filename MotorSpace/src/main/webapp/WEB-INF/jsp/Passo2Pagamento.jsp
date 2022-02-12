<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<body>
<jsp:include page="Header.jsp">
    <jsp:param name="pageTitle" value="Acquisto"/>
</jsp:include>
<section>
    <form action="PassoUnoServlet" method="post">
        <br>
        <label>Pagamento effettuato con successo</label>
    </form>
</section>
</body>
<%@include file="../footer.html"%>