package model;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.util.Objects;

public class Utente {
    private String username;
    private String email;
    private String password;
    private String nome;
    private String cognome;
    private Date nascita;
    private Boolean admin;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utente utente = (Utente) o;
        return username.equals(utente.username) && email.equals(utente.email) && password.equals(utente.password) && nome.equals(utente.nome) && cognome.equals(utente.cognome) && nascita.equals(utente.nascita) && admin.equals(utente.admin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, password, nome, cognome, nascita, admin);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Date getNascita() {
        return nascita;
    }

    public void setNascita(Date nascita) {
        this.nascita = nascita;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
