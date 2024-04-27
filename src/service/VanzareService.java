package service;

import model.Vanzare;

import java.util.ArrayList;
import java.util.List;

public class VanzareService {
    private List<Vanzare> vanzari;

    public VanzareService() {
        this.vanzari = new ArrayList<>();
    }

    public VanzareService(List<Vanzare> vanzari) {
        this.vanzari = vanzari;
    }

    public void adaugaVanzare(Vanzare vanzare) {
        vanzari.add(vanzare);
    }


    public void stergeVanzare(int idVanzare) {
        for (int i = 0; i < vanzari.size(); i++) {
            if (vanzari.get(i).getId() == idVanzare) {
                vanzari.remove(i);
                System.out.println("Vanzarea cu id-ul: " + idVanzare + " a fost ștearsa cu succes!");
                return;
            }
        }
        System.out.println("Vanzarea cu id-ul: " + idVanzare + " nu a fost găsită.");
    }

    public void afiseazaVanzari() {
        for (Vanzare vanzare : vanzari) {
            System.out.println(vanzare);
        }
    }


}
