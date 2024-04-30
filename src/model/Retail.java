package model;

public class Retail extends Produs {
    private String material;
    private int stoc;

    public Retail(String numeProdus, String descriere, double pret, int categorie, int furnizor, String material, int stoc) {
        super(numeProdus, descriere, pret, categorie, furnizor);
        this.material = material;
        this.stoc = stoc;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getStoc() {
        return stoc;
    }

    public void setStoc(int stoc) {
        this.stoc = stoc;
    }

    @Override
    public String toString() {
        return "Retail{" +
                "id= " + getIdProdus() +
                ", numeProdus='" + getNumeProdus() + '\'' +
                ", descriere='" + getDescriere() + '\'' +
                ", pret=" + getPret() +
                ", categorie=" + getCategorie() +
                ", furnizor=" + getFurnizor() +
                ", material='" + material + '\'' +
                ", stoc=" + getStoc() +
                '}';
    }
}
