package model;

public class Categorie {
    private String numeCategorie;
    private String descriere;
    private static int idCounter = 1;
    private final int idCategorie;

    // constructor
    public Categorie(String numeCategorie, String descriere) {
        this.numeCategorie = numeCategorie;
        this.descriere = descriere;
        this.idCategorie = idCounter++;
    }

    // getters si setters
    public String getNumeCategorie() {
        return numeCategorie;
    }

    public void setNumeCategorie(String numeCategorie) {
        this.numeCategorie = numeCategorie;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    //nu am nevoie de setter de idCategorie ptc el trb sa fie setat o singura data in constructor
    //am idCounter static si idCategorie final care incrementeaza automat idCounter
//    public void setIdCategorie(int idCategorie) {
//        this.idCategorie = idCategorie;
//    }

    // Metoda pentru a afișa informațiile despre categorie
    @Override
    public String toString() {
        return "Categorie{" +
                "numeCategorie='" + numeCategorie + '\'' +
                ", descriere='" + descriere + '\'' +
                ", idCategorie=" + idCategorie +
                '}';
    }
}

