package model;

import java.sql.Date;

public class Ordine {
    int id;
    String cliente;// viene inserito l'username del cliente
    Date dataOrdine;
    Date dataSpedizione;
    Date dataConsegna;

    public Ordine(String cliente, Date dataOrdine, Date dataConsegna, Date dataSpedizione, float prezzo_tot ) {
        this.cliente=cliente;
        this.dataOrdine = dataOrdine;
        this.dataConsegna = dataConsegna;
        this.dataSpedizione = dataSpedizione;
    }

    public Ordine() {
    }

    public String getCliente(){return cliente;}

    public void setCliente(String cliente){this.cliente= cliente; }

    public Date getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(Date dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public Date getDataSpedizione() {
        return dataSpedizione;
    }

    public void setDataSpedizione(Date dataSpedizione) {
        this.dataSpedizione = dataSpedizione;
    }

    public void setDataConsegna(Date dataConsegna){
        this.dataConsegna = dataConsegna;
    }

    public Date getDataConsegna(){
        return dataConsegna  ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
