package model;

import java.util.*;

public class Carrello {

    public static class ProdottoQuantità {
        private Prodotto prodotto;
        private int quantità;

        public ProdottoQuantità(Prodotto prodotto, int quantità) {
            this.prodotto = prodotto;
            this.quantità = quantità;
        }

        public ProdottoQuantità() {

        }

        public Prodotto getProdotto() {
            return prodotto;
        }

        public void setProdotto(Prodotto prodotto) {
            this.prodotto = prodotto;
        }

        public int getQuantità() {
            return quantità;
        }

        public void setQuantità(int quantità) {
            this.quantità = quantità;
        }

        public float getPrezzoTot(){return quantità * prodotto.getPrezzo();
        }
    }

    private LinkedHashMap<Integer, ProdottoQuantità> prodotti = new LinkedHashMap<>();

    public Collection<ProdottoQuantità> getProdotti() {
       return prodotti.values();
    }

    public List<ProdottoQuantità> getProdottiArray(){
       ArrayList<ProdottoQuantità> pq = new ArrayList<ProdottoQuantità>();
       Iterator it = prodotti.entrySet().iterator();
       while (it.hasNext()){
           Map.Entry entry= (Map.Entry) it.next();
           ProdottoQuantità p =(ProdottoQuantità) entry.getValue();
           System.out.println(p.getProdotto().getNome());
           pq.add(p);
       }
        return pq;
    }

    public ProdottoQuantità get(int  prodId) {
        return prodotti.get(prodId);
    }

    public void put(Prodotto prodotto, int quantità) {
        prodotti.put(Integer.valueOf(prodotto.getId()), new ProdottoQuantità(prodotto, quantità));
    }
    public ProdottoQuantità remove(String prodId){
        return prodotti.remove(prodId);
    }

    public float getPrezzoTot(){
        return (float) prodotti.values().stream().mapToDouble(p-> p.getPrezzoTot()).sum();
    }
}