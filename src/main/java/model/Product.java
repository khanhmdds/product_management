package model;

public class Product {
    protected int id;
    protected String title;
    protected String image;
    protected int price;
    protected int quantity;
    protected String description;
    protected int idcategory;

    public Product(){}

    public Product(int id, String title, String image, int price, int quantity, String description, int idcategory) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.idcategory = idcategory;
    }

    public Product(String title, String image, int price, int quantity, String description, int idcategory) {
        this.title = title;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.idcategory = idcategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(int idcategory) {
        this.idcategory = idcategory;
    }
}
