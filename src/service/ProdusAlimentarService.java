package service;
import model.Categorie;
import model.Produs;
import model.ProdusAlimentar;

import java.util.ArrayList;
import java.util.List;
public class ProdusAlimentarService {
    private List<ProdusAlimentar> produseAlimentare;

    public ProdusAlimentarService(){
        this.produseAlimentare = new ArrayList<ProdusAlimentar>();
    }

    public ProdusAlimentarService(List<ProdusAlimentar> produseAlimentare){
        this.produseAlimentare = produseAlimentare;
    }

    public void adaugaProdus(ProdusAlimentar produsAlimentar){
        produseAlimentare.add(produsAlimentar);
    }

    public void actualizeazaProdusAlimentar(int idProdus, ProdusAlimentar produsAlimentar){
        for(int i = 0; i < produseAlimentare.size(); i++){
            if(produseAlimentare.get(i).getIdProdus() == idProdus){
                produseAlimentare.set(i, produsAlimentar);
                System.out.println("Produsul cu id-ul: " + idProdus + " a fost actualizat cu succes!");
                return;
            }
        }
        System.out.println("Produsul cu id-ul: " + idProdus + " nu a fost gasit.");
    }

    public void stergeProdusAlimentar(int idProdus){
        for(int i = 0;i < produseAlimentare.size(); i++){
            if(produseAlimentare.get(i).getIdProdus() == idProdus){
                produseAlimentare.remove(i);
                System.out.println("Produsul cu id-ul: " + idProdus + " a fost sters cu succes!");
                return;
            }
        }
        System.out.println("Produsul cu id-ul: " + idProdus + " nu a fost gasit.");
    }

    public void afiseazaproduseAlimentare(){
        for(ProdusAlimentar produsAlimentar : produseAlimentare){
            System.out.println(produsAlimentar);
        }
    }

    public ProdusAlimentar getProdusAlimentarByID(int idProdusAlimentar){
        for(int i = 0; i < produseAlimentare.size(); i++){
            if(produseAlimentare.get(i).getIdProdus() == idProdusAlimentar){
                return produseAlimentare.get(i);
            }
        }
        System.out.println("Produsul alimentar nu a fost gasit.");
        return null;
    }
}
