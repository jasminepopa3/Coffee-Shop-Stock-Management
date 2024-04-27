package model;

public class Furnizor {
    private static int nextId = 1;
    private final int id;
    private String nume;
    private String adresa;
    private String contBancar;
    private String email;
    private String nrTelefon;

    public Furnizor(String nume, String adresa, String contBancar, String email, String nrTelefon) {
        this.id = nextId++;
        this.nume = nume;
        this.adresa = adresa;
        this.contBancar = contBancar;
        this.email = email;
        this.nrTelefon = nrTelefon;
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getContBancar() {
        return contBancar;
    }

    public void setContBancar(String contBancar) {
        this.contBancar = contBancar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNrTelefon() {
        return nrTelefon;
    }

    public void setNrTelefon(String nrTelefon) {
        this.nrTelefon = nrTelefon;
    }

    @Override
    public String toString() {
        return "Furnizor{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", adresa='" + adresa + '\'' +
                ", contBancar='" + contBancar + '\'' +
                ", email='" + email + '\'' +
                ", nrTelefon='" + nrTelefon + '\'' +
                '}';
    }
}
