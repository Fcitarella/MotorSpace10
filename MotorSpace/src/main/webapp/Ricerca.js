function ricerca(str){
    var datalist = document.getElementById("ricerca-datalist");
    if (str.length == 0) {
        datalist.innerHTML = "";
        return;
    }
    var xmlHttpReq = new xmlHttpReq();
    xmlHttpReq.responseType = 'json';
    xmlHttpReq.onreadystatechange = function (){
        if(this.readyState == 4 && this.status == 200){
            datalist.innerHTML = '';
            for (var i in this.response){
                var option = document.createElement('option');

                option.value = this.response[i];
                datalist.appendChild(option);
            }
        }
    }
    xmlHttpReq.open("GET","RicercaAjax7q"+encodeURIComponent(str), true);
    xmlHttpReq.send();
}