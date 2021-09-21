package model;

public class Amministratore {

    private String username;
    private String tipoAmm;
    public Amministratore(){

    }

    public Amministratore(String username, String tipoAmm){
        this.tipoAmm = tipoAmm;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTipoAmm() {
        return tipoAmm;
    }

    public void setTipoAmm(String tipoAmm) {
        this.tipoAmm = tipoAmm;
    }
}
