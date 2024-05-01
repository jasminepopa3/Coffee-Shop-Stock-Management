package model;

import java.util.HashMap;
import java.util.Map;

public class ProdusVanzare extends Produs {
//    nu mai am nevoie de mapare ptc o sa fac un tabel ajutator
//    private Map<ProdusAlimentar, Double> ingredienteCantitate; // Mapare între produs și cantitatea acestuia

    //exclud atributul furnizor din clasa Produs
    public ProdusVanzare(String numeProdus, String descriere, double pret, int categorie) {
        super(numeProdus, descriere, pret, categorie);
//        this.ingredienteCantitate = new HashMap<>(ingredienteCantitate);
    }



//    public Map<ProdusAlimentar, Double> getIngredienteCantitate() {
//        return ingredienteCantitate;
//    }

//    public void setIngredienteCantitate(Map<ProdusAlimentar, Double> ingredienteCantitate) {
//        this.ingredienteCantitate = ingredienteCantitate;
//    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ProdusVanzare{");
        stringBuilder.append("id=").append(getIdProdus()).append(", ");
        stringBuilder.append("numeProdus='").append(getNumeProdus()).append("', ");
        stringBuilder.append("descriere='").append(getDescriere()).append("', ");
        stringBuilder.append("pret=").append(getPret()).append(", ");
        stringBuilder.append("categorie=").append(getCategorie()).append(", ");
//        stringBuilder.append("ingredienteCantitate={");
//
//        for (Map.Entry<ProdusAlimentar, Double> entry : ingredienteCantitate.entrySet()) {
//            ProdusAlimentar produsAlimentar = entry.getKey();
//            double cantitate = entry.getValue();
//            stringBuilder.append(produsAlimentar.getNumeProdus()).append(": ").append(cantitate).append(", ");
//        }
//
//        stringBuilder.append("}");
        stringBuilder.append("}");

        return stringBuilder.toString();
    }
}
