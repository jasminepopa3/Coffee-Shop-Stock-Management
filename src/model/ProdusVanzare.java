package model;

import java.util.HashMap;
import java.util.Map;

public class ProdusVanzare extends Produs {
    public ProdusVanzare(String numeProdus, String descriere, double pret, int categorie) {
        super(numeProdus, descriere, pret, categorie);
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ProdusVanzare{");
        stringBuilder.append("id=").append(getIdProdus()).append(", ");
        stringBuilder.append("numeProdus='").append(getNumeProdus()).append("', ");
        stringBuilder.append("descriere='").append(getDescriere()).append("', ");
        stringBuilder.append("pret=").append(getPret()).append(", ");
        stringBuilder.append("categorie=").append(getCategorie()).append(", ");
        stringBuilder.append("}");

        return stringBuilder.toString();
    }
}
