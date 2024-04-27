package service;

import model.ProdusAlimentar;
import model.Retail;

import java.util.ArrayList;
import java.util.List;

public class RetailService {
    private List<Retail> retails;

    public RetailService(){
        this.retails = new ArrayList<Retail>();
    }

    public RetailService(List<Retail> retails){
        this.retails = retails;
    }

    public void adaugaRetail(Retail retail){
        retails.add(retail);
    }

    public void actualizeazaRetail(int idProdus, Retail retail){
        for(int i = 0; i < retails.size(); i++){
            if(retails.get(i).getIdProdus() == idProdus){
                retails.set(i, retail);
                System.out.println("Retail-ul cu id-ul: " + idProdus + " a fost actualizat cu succes!");
                return;
            }
        }
        System.out.println("Retail-ul cu id-ul: " + idProdus + " nu a fost gasit.");
    }

    public void stergeRetail(int idProdus){
        for(int i = 0;i < retails.size(); i++){
            if(retails.get(i).getIdProdus() == idProdus){
                retails.remove(i);
                System.out.println("Retail-ul cu id-ul: " + idProdus + " a fost sters cu succes!");
                return;
            }
        }
        System.out.println("Retail-ul cu id-ul: " + idProdus + " nu a fost gasit.");
    }

    public void afiseazaRetails(){
        for(Retail retail : retails){
            System.out.println(retail);
        }
    }

    public Retail getRetailByID(int idRetail){
        for(int i = 0; i < retails.size(); i++){
            if(retails.get(i).getIdProdus() == idRetail){
                return retails.get(i);
            }
        }
        System.out.println("Retail-ul nu a fost gasit.");
        return null;
    }
}
