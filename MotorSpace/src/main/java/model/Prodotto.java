package model;

import java.util.Objects;

public class Prodotto {
    private String id;
    private String nome;
    private String descrizione;
    private float prezzo;
    private String marca;
    private String categoria;

    @Override
    public String toString() {
        return "Prodotto{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", prezzo=" + prezzo +
                ", marca='" + marca + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prodotto prodotto = (Prodotto) o;
        return id == prodotto.id &&
                Float.compare(prodotto.prezzo, prezzo) == 0 &&
                Objects.equals(nome, prodotto.nome) &&
                Objects.equals(descrizione, prodotto.descrizione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descrizione, prezzo);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public String getMarca() {
        return marca;
    }

    public String getCategoria() { return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setMarca(String marca) {
        this.marca= marca;
    }
}

