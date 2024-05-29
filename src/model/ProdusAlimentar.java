package model;

import java.time.LocalDate;

public class ProdusAlimentar extends Produs {
    private LocalDate dataExpirare;
    private int stoc;

    public ProdusAlimentar(String numeProdus, String descriere, double pret, int furnizor, int categorie, LocalDate dataExpirare, int stoc) {
        super(numeProdus, descriere, pret, categorie, furnizor);
        this.dataExpirare = dataExpirare;
        this.stoc = stoc;
    }

    public LocalDate getDataExpirare() {
        return dataExpirare;
    }

    public void setDataExpirare(LocalDate dataExpirare) {
        this.dataExpirare = dataExpirare;
    }

    public int getStoc() {
        return stoc;
    }

    public void setStoc(int stoc) {
        this.stoc = stoc;
    }

    @Override
    public String toString() {
        return "ProdusAlimentar{" +
                "id= " + getIdProdus() +
                ", numeProdus='" + getNumeProdus() + '\'' +
                ", descriere='" + getDescriere() + '\'' +
                ", pret=" + getPret() +
                ", furnizor=" + getFurnizor() +
                ", categorie=" + getCategorie() +
                ", dataExpirare=" + getDataExpirare() +
                ", stoc=" + getStoc() +
                '}';
    }

}
