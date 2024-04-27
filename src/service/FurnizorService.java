package service;

import model.Furnizor;

import java.util.ArrayList;
import java.util.List;
public class FurnizorService {
    private List<Furnizor> furnizori;

    public FurnizorService(List<Furnizor> furnizori) {
        this.furnizori = furnizori;
    }

    public FurnizorService() {
        this.furnizori = new ArrayList<>();
    }

    //metoda pt adaugare furnizor
    public void adaugaFurnizor(Furnizor furnizor){
        furnizori.add(furnizor);
    }


    public void actualizeazaFurnizor(int id, Furnizor furnizor){
        for(int i=0;i<furnizori.size();i++){
            if(furnizori.get(i).getId() == id){
                furnizori.set(i, furnizor);
                System.out.println("Furnizorul cu id-ul " + id + " a fost actualizat cu succes.");
                return;
            }
        }
        System.out.println("Furnizorul cu id-ul " + id + " nu a fost găsită.");
    }
    public void stergeFurnizor(int id){
        for(int i=0;i<furnizori.size();i++){
            if(furnizori.get(i).getId() == id){
                furnizori.remove(i);
                System.out.println("Furnizorul cu id-ul " + id + " a fost sters cu succes.");
                return;
            }
        }
        System.out.println("Furnizorul cu id-ul " + id + " nu a fost găsit.");

    }

    public void afiseazaFurnizori(){
        for(Furnizor furnizor : furnizori){
            System.out.println(furnizor);
        }
    }

    public Furnizor getFurnizorByID(int idFurnizor){
        for(int i = 0; i < furnizori.size(); i++){
            if(furnizori.get(i).getId() == idFurnizor){
                return furnizori.get(i);
            }
        }
        System.out.println("Furnizorul nu a fost gasit.");
        return null;
    }


}
