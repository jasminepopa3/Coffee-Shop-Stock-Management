package model;

//compareTo() pentru a efectua operații de sortare sau de comparație între obiectele Produs
public class Produs implements Comparable<Produs>{
    private static int idCounter = 1;
    private final int idProdus;
    protected String numeProdus;
    protected String descriere;
    protected double pret;
    protected Categorie categorie;

    private Furnizor furnizor;

    public Produs(String numeProdus, String descriere, double pret, Categorie categorie, Furnizor furnizor) {
        this.idProdus = idCounter++;
        this.numeProdus = numeProdus;
        this.descriere = descriere;
        this.pret = pret;
        this.categorie = categorie;
        this.furnizor = furnizor;
    }

    public Produs(String numeProdus, String descriere, double pret, Categorie categorie) {
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

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Furnizor getFurnizor() {
        return furnizor;
    }

    public void setFurnizor(Furnizor furnizor) {
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

