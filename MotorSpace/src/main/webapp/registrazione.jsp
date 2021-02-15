<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="Header.jsp">
    <jsp:param name="pageTitle" value="Registrazione utente"/>
</jsp:include>
<section>
    <h1>Registrazione utente</h1>
    <h5>Riempi tutti i campi</h5>
    <form name="registrazione" action="Registrazione" method="get">
        <label>Username(almeno 6 caratteri), solo lettere e numeri, non utilizzato da altri utenti</label>
        <input type="text" name="username" >
        <label>Password (almeno 8 caratteri, deve contenere: una lettera maiuscola, un numero)</label>
        <input type="password" name="password" >
        <label>Password conferma</label>
        <input type="password" name="passwordConferma" >
        <label>Nome(solo lettere)</label>
        <input type="text" name="nome" >
        <label>Cognome(solo lettere)</label>
        <input type="text" name="cognome" >
        <label> Email</label>
        <input type="email" name="email" >
        <label> Data di nascita </label>
        <input type="date" name="nascita">
        <input id="registrami" type="submit" value="Registrami"><span id="registramimessaggio"></span>
    </form>
</section>
<!--<script>
    var borderOk = '2px solid #080';
    var borderNo = '2px solid #f00';
    var usernameOk = false;
    var nomeOk = false;
    var cognomeOK = false;
    var passwordOk = false;
    var emailOk = false;

    function validaUsername(){
        var input = document.forms['registrazione']['username'];
        if (input.value.length >= 6 && input.value.match(/^[0-9a-zA-Z]+$/)){
            var xmlHttpReq = new XMLHttpRequest();
            xmlHttpReq.onreadystatechange = function(){
                if(this.readyState == 4 && this.status == 200 && this.responseText =='<ok/>'){
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
    function validaPassword(){
        var inputpw = document.forms['registrazione']['password'];
        var inputpwconf = documenti.forms['registrazione']['passwordConferma'];
        var password = inputpw.value;
        if(password.lenght>= 8 && password.toUpperCase() != password && password.toLowerCase() != password && /[0-9]/.test(password)){
            inputpw.style.border = borderOk;

            if(password == inputpwconf.value){
                inputpwconf.style.border = borderOk;
                passwordOk = true;
            }else {
                inputpwconf.style.border = borderNo;
                passwordOk = false;
            }
            }else {
            inputpw.style.border = borderNo;
            inputpwconf.style.border = borderNo;
            passwordOk = false;
        }
        cambiaStatoRegistrami();
    }
    function validaNome(){
        var input = document.forms['registrazione']['nome'];
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
        var input = document.forms['registrazione']['cognome'];
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
        var input = document.forms['registrazione']['email'];
        if(input.value.match(/^\w+([\.-]?w+)'@\w+([\.-]?\w+)*(\.\w+)+$/)){
            input.style.border = borderOk;
            emailOk = true;
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
</script>-->
<%@ include file="footer.html"%>