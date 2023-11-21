package model;

public class Item {
    private int id;
    private Product product;
    private int quantity;
    private long price;
    public Item(){}
    public Item(int id, Product product, int quantity, long price){
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public long getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}