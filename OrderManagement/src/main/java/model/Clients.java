package model;

public class Clients {

    private int id;
    private String numePrenume;
    private String adresa;
    private String nrTel;
    private String email;

    public Clients() {}
    public Clients(String numePrenume, String adresa, String nrTel, String email) {

        this.adresa = adresa;
        this.nrTel = nrTel;
        this.email = email;
        this.numePrenume = numePrenume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumePrenume() {
        return numePrenume;
    }

    public void setNumePrenume(String numePrenume) {
        this.numePrenume = numePrenume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getNrTel() {
        return nrTel;
    }

    public void setNrTel(String nrTel) {
        this.nrTel = nrTel;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Clients{" +
                "id=" + id +
                ", numePrenume='" + numePrenume + '\'' +
                ", adresa='" + adresa + '\'' +
                ", nrTel='" + nrTel + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
