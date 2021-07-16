package server.models;

/**
 * Model for the items which have been ordered
 */
public class OrderedItem {
    String name;
    int quantity;

    public OrderedItem(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }
}
