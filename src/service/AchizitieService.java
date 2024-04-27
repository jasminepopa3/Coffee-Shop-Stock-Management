package service;

import model.Achizitie;

import java.util.ArrayList;
import java.util.List;

public class AchizitieService {
    private List<Achizitie> achizitii;

    public AchizitieService() {
        this.achizitii = new ArrayList<>();
    }

    public AchizitieService(List<Achizitie> achizitii) {
        this.achizitii = achizitii;
    }

    public void adaugaAchizitie(Achizitie achizitie) {
        achizitii.add(achizitie);
    }


    public void stergeAchizitie(int idAchizitie) {
        for (int i = 0; i < achizitii.size(); i++) {
            if (achizitii.get(i).getId() == idAchizitie) {
                achizitii.remove(i);
                System.out.println("Achizitia cu id-ul: " + idAchizitie + " a fost ștearsa cu succes!");
                return;
            }
        }
        System.out.println("Achizitia cu id-ul: " + idAchizitie + " nu a fost găsită.");
    }

    public void afiseazaAchizitii() {
        for (Achizitie achizitie : achizitii) {
            System.out.println(achizitie);
        }
    }
}
