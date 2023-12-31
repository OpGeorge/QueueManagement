package model;

public class Product {

    private int id;
    private int cantitate;
    private int pret;
    private String productName;

    public Product()
    {}

    public Product( int cantitate, int pret, String productName) {

        this.cantitate = cantitate;
        this.pret = pret;
        this.productName = productName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", cantitate=" + cantitate +
                ", pret=" + pret +
                ", productName='" + productName + '\'' +
                '}';
    }
}
