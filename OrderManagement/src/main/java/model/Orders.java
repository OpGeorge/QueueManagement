package model;

public class Orders {

    private int id;
    private String orderName;
    private int productId;
    private int clientId;
    private int cantitate;

    public Orders(String orderName, int productId, int clientId, int cantitate) {

        this.orderName = orderName;
        this.productId = productId;
        this.clientId = clientId;
        this.cantitate = cantitate;
    }
    public Orders(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", orderName='" + orderName + '\'' +
                ", productId=" + productId +
                ", clientId=" + clientId +
                ", cantitate=" + cantitate +
                '}';
    }
}
