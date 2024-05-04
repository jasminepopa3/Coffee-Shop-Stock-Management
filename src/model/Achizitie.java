package model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Achizitie extends Tranzactie {
//    private Map<ProdusAlimentar, Integer> listaProduseAlimentareCantitate;
//    private Map<Retail, Integer> listaRetailCantitate;

    public Achizitie(double pretTotal, LocalDate dataTranzactie, String cashCard) {
        super(pretTotal, dataTranzactie, cashCard);
//        this.listaProduseAlimentareCantitate = listaProduseCantitate;
//        this.listaRetailCantitate = listaRetailCantitate;
    }

//    public Map<ProdusAlimentar, Integer> getListaProduseCantitate() {
//        return listaProduseAlimentareCantitate;
//    }
//
//    public void setListaProduseCantitate(Map<ProdusAlimentar, Integer> listaProduseCantitate) {
//        this.listaProduseAlimentareCantitate = listaProduseCantitate;
//    }
//
//    public Map<Retail, Integer> getListaRetailCantitate() {
//        return listaRetailCantitate;
//    }
//
//    public void setListaRetailCantitate(Map<Retail, Integer> listaRetailCantitate) {
//        this.listaRetailCantitate = listaRetailCantitate;
//    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Achizitie{idAchizitie=").append(getId()).append(", {");
//        stringBuilder.append("listaProduseAlimentareCantitate={");
//        for (Map.Entry<ProdusAlimentar, Integer> entry : listaProduseAlimentareCantitate.entrySet()) {
//            ProdusAlimentar produs = entry.getKey();
//            Integer cantitate = entry.getValue();
//            stringBuilder.append(produs.getNumeProdus()).append(": ").append(cantitate).append(", ");
//        }
//        stringBuilder.append("}, ");
//        stringBuilder.append("listaRetailCantitate={");
//        for (Map.Entry<Retail, Integer> entry : listaRetailCantitate.entrySet()) {
//            Retail retail = entry.getKey();
//            Integer cantitate = entry.getValue();
//            stringBuilder.append(retail.getNumeProdus()).append(": ").append(cantitate).append(", ");
//        }
//        stringBuilder.append("}, ");
        stringBuilder.append("pretTotal=").append(pretTotal).append(", ");
        stringBuilder.append("dataTranzactie=").append(dataTranzactie).append(", ");
        stringBuilder.append("cashCard='").append(cashCard).append("'}");
        return stringBuilder.toString();
    }

}
