package model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Vanzare extends Tranzactie {
    private Map<ProdusVanzare, Integer> listaProduseVanzareCantitate;

    public Vanzare(Map<ProdusVanzare, Integer> listaProduseVanzareCantitate, double pretTotal, LocalDate dataTranzactie, String cashCard) {
        super(pretTotal, dataTranzactie, cashCard); // Furnizorul este null pentru o vânzare
        this.listaProduseVanzareCantitate = listaProduseVanzareCantitate;
    }

    public Map<ProdusVanzare, Integer> getListaProduseVanzareCantitate() {
        return listaProduseVanzareCantitate;
    }

    public void setListaProduseVanzareCantitate(Map<ProdusVanzare, Integer> listaProduseVanzareCantitate) {
        this.listaProduseVanzareCantitate = listaProduseVanzareCantitate;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Vanzare{idVanzare=").append(getId()).append(", ");
        stringBuilder.append("listaProduseVanzareCantitate={");
        for (Map.Entry<ProdusVanzare, Integer> entry : listaProduseVanzareCantitate.entrySet()) {
            ProdusVanzare produs = entry.getKey();
            Integer cantitate = entry.getValue();
            stringBuilder.append(produs.getNumeProdus()).append(": ").append(cantitate).append(", ");
        }
        stringBuilder.append("}, ");
        stringBuilder.append("pretTotal=").append(pretTotal).append(", ");
        stringBuilder.append("dataTranzactie=").append(dataTranzactie).append(", ");
        stringBuilder.append("cashCard='").append(cashCard).append("'}");
        return stringBuilder.toString();
    }

}
