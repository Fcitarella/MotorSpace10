<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="Header.jsp">
    <jsp:param name="pageTitle" value="Registrazione utente"/>
</jsp:include>
<section>
    <h1>Registrazione utente</h1>
    <h5>Riempi tutti i campi</h5>
    <form name="registrazione" action="Registrazione" method="get" onsubmit= return validate()>
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

<script >

    function validate() {
        return (validateEmail() && validatePassword());
    }

    function validateEmail() {
        var email = document.form.email.value;
        var request = new XMLHttpRequest();
        request.open('GET',	"VerificaUsername?username"+ encodeURIComponent(email),false);
        // `false` per le richieste synchronous
        request.send(null);
        if (request.status === 200) {
            if (request.responseText === '<ok/>')
                return true;
            else
                alert('username gia presente in archivio');
        } else
            alert("problemi di connessione");
        return false;
    }

    function validatePassword() {
        var password = document.form.password.value;
        if (password.length >= 8
            && password.toUpperCase() != password
            && password.toLowerCase() != password
            && /[0-9]/.test(password)) {
            if (password == document.form.passwordConfirm.value) {
                alert("finisco validate");

                return (true);
            } else {
                alert("Password non confermata");
                return false;
            }
        } else {
            alert("Password non valida");
            return (false);
        }

    }
</script>
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