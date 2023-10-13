package model;

public class Product {
    protected int id;
    protected String title;
    protected String image;
    protected int price;
    protected int quantity;
    protected String description;
    protected int idCategory;

    public Product(){}

    public Product(int id, String title, String image, int price, int quantity, String description, int idCategory) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.idCategory = idCategory;
    }

    public Product(String title, String image, int price, int quantity, String description, int idCategory) {
        this.title = title;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.idCategory = idCategory;
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

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }
}
