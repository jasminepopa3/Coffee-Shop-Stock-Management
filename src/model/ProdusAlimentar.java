package model;

import java.time.LocalDate;
import java.util.Objects;

public class ProdusAlimentar extends Produs {
    private LocalDate dataExpirare;
    private int stoc;

    public ProdusAlimentar(String numeProdus, String descriere, double pret,Furnizor furnizor, Categorie categorie, LocalDate dataExpirare, int stoc) {
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

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        if (!super.equals(o)) return false; // Verifică egalitatea atributelor moștenite
//        ProdusAlimentar that = (ProdusAlimentar) o;
//        return Objects.equals(dataExpirare, that.dataExpirare) &&
//                stoc == that.stoc;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(super.hashCode(), dataExpirare, stoc);
//    }
}
