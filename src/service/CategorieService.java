package service;

import model.Categorie;
import model.Furnizor;

import java.util.ArrayList;
import java.util.List;

public class CategorieService {
    private List<Categorie> categorii;

    //când cream o nouă instanță a clasei CategorieService,
    // o listă nouă de categorii va fi creată și asociată instanței respective.
    // Această listă va fi utilizată pentru a adăuga, actualiza și șterge categorii în
    // cadrul serviciului CategorieService
    // Constructor pentru inițializare cu o listă goală de categorii
    public CategorieService() {
        this.categorii = new ArrayList<>();
    }

    // Constructor pentru inițializare cu o listă de categorii preexistente
    public CategorieService(List<Categorie> categorii) {
        this.categorii = categorii;
    }

    // Metoda pentru adăugarea unei categorii
    public void adaugaCategorie(Categorie categorie) {
        categorii.add(categorie);
    }

    // Metoda pentru actualizarea unei categorii
    public void actualizeazaCategorie(int idCategorie, Categorie categorieActualizata) {
        for (int i = 0; i < categorii.size(); i++) {
            if (categorii.get(i).getIdCategorie() == idCategorie) {
                categorii.set(i, categorieActualizata);
                System.out.println("Categorie cu id-ul " + idCategorie + " a fost actualizata cu succes.");
                return;
            }
        }
        System.out.println("Categorie cu id-ul " + idCategorie + " nu a fost găsită.");
    }

    // Metoda pentru ștergerea unei categorii
    public void stergeCategorie(int idCategorie) {
        for (int i = 0; i < categorii.size(); i++) {
            if (categorii.get(i).getIdCategorie() == idCategorie) {
                categorii.remove(i);
                System.out.println("Categorie cu id-ul " + idCategorie + " a fost stearsa cu succes.");
                return;
            }
        }
        System.out.println("Categorie cu id-ul " + idCategorie + " nu a fost găsită.");
    }

    // Metoda pentru afișarea tuturor categoriilor
    public void afiseazaCategorii() {
        for (Categorie categorie : categorii) {
            System.out.println(categorie);
        }
    }

    public Categorie getCategorieByID(int idCategorie){
        for(int i = 0; i < categorii.size(); i++){
            if(categorii.get(i).getIdCategorie() == idCategorie){
                return categorii.get(i);
            }
        }
        System.out.println("Categoria nu a fost gasita.");
        return null;
    }
}
