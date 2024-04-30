package model;

import java.sql.Array;

//compareTo() pentru a efectua operații de sortare sau de comparație între obiectele Produs
public class Produs implements Comparable<Produs>{
    private static int idCounter = 1;
    private final int idProdus;
    protected String numeProdus;
    protected String descriere;
    protected double pret;
    protected int categorie;

    private int furnizor;

    public Produs(String numeProdus, String descriere, double pret, int categorie, int furnizor) {
        this.idProdus = idCounter++;
        this.numeProdus = numeProdus;
        this.descriere = descriere;
        this.pret = pret;
        this.categorie = categorie;
        this.furnizor = furnizor;
    }

    public Produs(String numeProdus, String descriere, double pret, int categorie) {
        this.idProdus = idCounter++;
        this.numeProdus = numeProdus;
        this.descriere = descriere;
        this.pret = pret;
        this.categorie = categorie;
    }


    // Gettere și Settere pentru numeProdus, descriere, pret și categorie

    public String getNumeProdus() {
        return numeProdus;
    }

    public void setNumeProdus(String numeProdus) {
        this.numeProdus = numeProdus;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    public int getFurnizor() {
        return furnizor;
    }

    public void setFurnizor(int furnizor) {
        this.furnizor = furnizor;
    }

    public int getIdProdus() {
        return idProdus;
    }

    @Override
    public String toString() {
        return "Produs{" +
                "idProdus=" + idProdus +
                ", numeProdus='" + numeProdus + '\'' +
                ", descriere='" + descriere + '\'' +
                ", pret=" + pret +
                ", categorie=" + categorie +
                ", furnizor=" + furnizor +
                '}';
    }

    @Override
    public int compareTo(Produs other) {
        return this.numeProdus.compareTo(other.numeProdus);
    }
}

