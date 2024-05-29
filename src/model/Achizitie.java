package model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Achizitie extends Tranzactie {

    public Achizitie(double pretTotal, LocalDate dataTranzactie, String cashCard) {
        super(pretTotal, dataTranzactie, cashCard);

    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Achizitie{idAchizitie=").append(getId()).append(", {");
        stringBuilder.append("pretTotal=").append(pretTotal).append(", ");
        stringBuilder.append("dataTranzactie=").append(dataTranzactie).append(", ");
        stringBuilder.append("cashCard='").append(cashCard).append("'}");
        return stringBuilder.toString();
    }

}
