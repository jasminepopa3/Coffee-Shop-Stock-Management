package service;
import model.Produs;

import java.util.ArrayList;
import java.util.List;
public class ProdusService {
    private List<Produs> produse;

    public ProdusService(){
        this.produse = new ArrayList<Produs>();
    }

    public ProdusService(List<Produs> produse){
        this.produse = produse;
    }

    public void adaugaProdus(Produs produs){
        produse.add(produs);
    }

    public void actualizeazaProdus(int idProdus, Produs produs){
        for(int i = 0; i < produse.size(); i++){
            if(produse.get(i).getIdProdus() == idProdus){
                produse.set(i, produs);
                System.out.println("Produsul cu id-ul: " + idProdus + " a fost actualizat cu succes!");
                return;
            }
        }
        System.out.println("Produsul cu id-ul: " + idProdus + " nu a fost gasit.");
    }

    public void stergeProdus(int idProdus){
        for(int i = 0;i < produse.size(); i++){
            if(produse.get(i).getIdProdus() == idProdus){
                produse.remove(i);
                System.out.println("Produsul cu id-ul: " + idProdus + " a fost sters cu succes!");
                return;
            }
        }
        System.out.println("Produsul cu id-ul: " + idProdus + " nu a fost gasit.");
    }

    public void afiseazaProduse(){
        for(Produs produs : produse){
            System.out.println(produs);
        }
    }
}
