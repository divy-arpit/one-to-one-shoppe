package server.models;

/**
 * Model for Product on sale
 */


public class Product {
    int id;
    String name;
    int mrp;
    int quantity;


    public Product(int id, String name, int mrp, int quantity) {
        this.id = id;
        this.name = name;
        this.mrp = mrp;
        this.quantity = quantity;
    }

    public Product(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMrp() {
        return mrp;
    }

    public int getQuantity() {
        return quantity;
    }
}
