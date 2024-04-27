package model;

import java.time.LocalDate;

public class Tranzactie {

    private static int nextId = 1;
    private final int id;
    protected double pretTotal;
    protected LocalDate dataTranzactie;
    protected String cashCard; // cash sau card

    public Tranzactie(double pretTotal, LocalDate dataTranzactie, String cashCard) {
        this.id = nextId++;
        this.pretTotal = pretTotal;
        this.dataTranzactie = dataTranzactie;
        this.cashCard = cashCard;
    }

    public int getId() {
        return id;
    }

    public double getPretTotal() {
        return pretTotal;
    }

    public void setPretTotal(double pretTotal) {
        this.pretTotal = pretTotal;
    }

    public LocalDate getDataTranzactie() {
        return dataTranzactie;
    }

    public void setDataTranzactie(LocalDate dataTranzactie) {
        this.dataTranzactie = dataTranzactie;
    }

    public String getCashCard() {
        return cashCard;
    }

    public void setCashCard(String cashCard) {
        this.cashCard = cashCard;
    }

    @Override
    public String toString() {
        return "Tranzactie{" +
                "pretTotal=" + pretTotal +
                ", dataTranzactie=" + dataTranzactie +
                ", cashCard='" + cashCard + '\'' +
                '}';
    }
}
