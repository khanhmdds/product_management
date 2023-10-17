package model;

import java.util.List;

public class Order {
    private int id;
    private User user;
    private List<Item> items;
    private int status;

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public List<Item> getItems() {
        return items;
    }

    public int getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
