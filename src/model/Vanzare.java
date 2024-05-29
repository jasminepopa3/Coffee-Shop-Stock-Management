package model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Vanzare extends Tranzactie {
    public Vanzare(double pretTotal, LocalDate dataTranzactie, String cashCard) {
        super(pretTotal, dataTranzactie, cashCard); // Furnizorul este null pentru o vânzare
    }



    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Vanzare{idVanzare=").append(getId()).append(", {");
        stringBuilder.append("pretTotal=").append(pretTotal).append(", ");
        stringBuilder.append("dataTranzactie=").append(dataTranzactie).append(", ");
        stringBuilder.append("cashCard='").append(cashCard).append("'}");
        return stringBuilder.toString();
    }

}
