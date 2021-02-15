<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="Header.jsp">
    <jsp:param name="pageTitle" value="Acquisto"/>
</jsp:include>
<section>
<c:choose>
<c:when test="${utente != null}">

    <form action="Pagamento" method="post">
        <br>
        <label>Nome</label>
        <input type="text" name="nome" value="${utente.nome}">
        <label>Cognome</label>
        <input type="text" name ="cognome" value="${utente.cognome}">
        <label>Data di nascita</label>
        <input type="date" name="nascita" value= "${utente.nascita}">
        <label>Email</label>
        <input type="text" name="email" value="${utente.email}">
        <label>Carta di credito</label>
        <input type="text" name="carta" value="">
        <input type="submit" value="Procedi all'acquisto">
    </form>
</c:when>
    <c:otherwise>
    <form action="Pagamento" method="post">
        <br>
        <label>Nome</label>
        <input type="text" name="nome" value="">
        <label>Cognome</label>
        <input type="text" name ="cognome" value="">
        <label>Data di nascita</label>
        <input type="date" name="nascita"value="">
        <label>Email</label>
        <input type="text" name="email" value="">
        <label>Carta di credito</label>
        <input type="text" name="carta" value="">
        <input type="submit" value="Procedi all'acquisto">
    </form>
    </c:otherwise>
</c:choose>
</section>
<script>
    var borderOk = '2px solid #080';
    var borderNo = '2px solid #f00';
    var usernameOk = false;
    var nomeOk = false;
    var cognomeOK = false;
    var passwordOk = false;
    var emailOk = false;
    var cartaOk = false;

    function validaUsername(){
        var input = document.forms['Pagamento']['username'];
        if (input.value.length >= 6 && input.value.match(/^[0-9a-zA-Z]+$/)){
            var xmlHttpReq = new XMLHttpRequest();
            xmlHttpReq.onreadystatechange = function(){
                if(this.readyState == 4 && this.status == 200 && this.responseText =='<ok/'>){
                    usernameOk = true;
                    input.style.border = borderOk;
                }else{
                    input.style.border = borderNo;
                    usernameOk = false;
                }
                cambiaStatoRegistrami()
            }
            xmlHttpReq.open("GET","VerificaUsername?username="+encodeURIComponent(input.value),true);
            xmlHttpReq.send();
        }else{

        }
    }

    function validaNome(){
        var input = document.forms['Pagamento']['nome'];
        if(input.value.trim().length > 0 && input.value().match(/^[a-zA-Z\u00C0-\u00ff]+$/)) {
            input.style.border = borderOk;
            nomeOk = true;
        }else {
            input.style.border = borderNo;
            nomeOk = false;
        }
        cambiaStatoRegistrami();
    }

    function validaCognome(){
        var input = document.forms['Pagamento']['cognome'];
        if(input.value.trim().length > 0 && input.value().match(/^[a-zA-Z\u00C0-\u00ff]+$/)) {
            input.style.border = borderOk;
            nomeOk = true;
        }else {
            input.style.border = borderNo;
            nomeOk = false;
        }
        cambiaStatoRegistrami();
    }

    function validaEmail(){
        var input = document.forms['Pagamento']['email'];
        if(input.value.match(/^\w+([\.-]?w+)'@\w+([\.-]?\w+)*(\.\w+)+$/)){
            input.style.border = borderOk;
            emailOk = true;
        }else {
            input.style.border = borderNo;
            emailOk = false;
        }
        cambiaStatoRegistrami();
    }

    function validaCarta(){
        var input = document.forms['Pagamento']['carta'];
        if(input.value.match(/^\w+([\.-]?w+)'@\w+([\.-]?\w+)*(\.\w+)+$/)){
            input.style.border = borderOk;
            cartaOk = true;
        }else {
            input.style.border = borderNo;
            emailOk = false;
        }
        cambiaStatoRegistrami();
    }

    function cambiaStatoRegistrami(){
        if(usernameOk && nomeOk && cognomeOK && passwordOk && emailOk){
            document.getElementById('registrami').disabled = false;
            document.getElementById('registramimessaggio').innerHTML ='';
        }else{
            document.getElementById('registrami').disabled = true;
            document.getElementById('registramimessaggio').innerHTML = 'Verifica che tutti i campi siano in verde.';
        }
    }

</script>
<%@include file="footer.html"%>