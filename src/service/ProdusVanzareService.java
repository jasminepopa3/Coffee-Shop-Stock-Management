package service;

import model.ProdusVanzare;

import java.util.ArrayList;
import java.util.List;

public class ProdusVanzareService {
    private List<ProdusVanzare> produseVanzare;

    public ProdusVanzareService() {
        this.produseVanzare = new ArrayList<>();
    }

    public ProdusVanzareService(List<ProdusVanzare> produseVanzare) {
        this.produseVanzare = produseVanzare;
    }

    public void adaugaProdusVanzare(ProdusVanzare produsVanzare) {
        produseVanzare.add(produsVanzare);
    }

    public void actualizeazaProdusVanzare(int idProdus, ProdusVanzare produsVanzare) {
        for (int i = 0; i < produseVanzare.size(); i++) {
            if (produseVanzare.get(i).getIdProdus() == idProdus) {
                produseVanzare.set(i, produsVanzare);
                System.out.println("Produsul de vânzare cu id-ul: " + idProdus + " a fost actualizat cu succes!");
                return;
            }
        }
        System.out.println("Produsul de vânzare cu id-ul: " + idProdus + " nu a fost găsit.");
    }

    public void stergeProdusVanzare(int idProdus) {
        for (int i = 0; i < produseVanzare.size(); i++) {
            if (produseVanzare.get(i).getIdProdus() == idProdus) {
                produseVanzare.remove(i);
                System.out.println("Produsul de vânzare cu id-ul: " + idProdus + " a fost șters cu succes!");
                return;
            }
        }
        System.out.println("Produsul de vânzare cu id-ul: " + idProdus + " nu a fost găsit.");
    }

    public void afiseazaProduseVanzare() {
        for (ProdusVanzare produsVanzare : produseVanzare) {
            System.out.println(produsVanzare);
        }
    }

    public ProdusVanzare getProdusVanzareByID(int idProdusVanzare){
        for(int i = 0; i < produseVanzare.size(); i++){
            if(produseVanzare.get(i).getIdProdus() == idProdusVanzare){
                return produseVanzare.get(i);
            }
        }
        System.out.println("Produsul de vanzare nu a fost gasit.");
        return null;
    }
}
