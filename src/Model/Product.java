package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Constructs and controls Product objects that can contain associated parts.
 * @author Hayley D'Angelo
 * */
public class Product {

    //FIELDS

    /**
     * A list of Associated Parts for a Product object.
     * */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * The ID of the product.
     * */
    private int id;

    /**
     * The name of the product.
     * */
    private String name;

    /**
     * The price of the product.
     * */
    private double price;

    /**
     * The number of product in the inventory.
     * */
    private int stock;

    /**
     * The minimum inventory level of the product.
     * */
    private int min;

    /**
     * The maximum inventory level of the product.
     * */
    private int max;


    //METHODS

    /**
     * The constructor that creates a new Product object.
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * */
    public Product(int id, String name, double price, int stock, int min, int max){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock =  stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Gets the ID of a product.
     * @return id
     * */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of a product.
     * @param id
     * */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of a product.
     * @return name
     * */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of a product.
     * @param name
     * */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the price of a product.
     * @return price
     * */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of a product.
     * @param price
     * */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the inventory level (stock) of a product.
     * @return stock
     * */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the inventory level (stock) of a product.
     * @param stock
     * */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Gets the minimum inventory level (min) of a product.
     * @return min
     * */
    public int getMin() {
        return min;
    }

    /**
     * Sets the minimum inventory level (min) of a product.
     * @param min
     * */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Gets the maximum inventory level (max) of a product.
     * @return max
     * */
    public int getMax() {
        return max;
    }

    /**
     * Sets the maximum inventory level (max) of a product.
     * @param max
     * */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Adds a part to the associated parts list for a product.
     * @param part
     * */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * Deletes a part from the associated parts list for a product.
     * @param selectedAssociatedPart
     * @return boolean that indicates a part was deleted (true) or not (false)
     * */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        if (associatedParts.contains(selectedAssociatedPart)) {
            associatedParts.remove(selectedAssociatedPart);
            return true;
        }
        else
            return false;
    }

    /**
     * Gets a list of the associated parts for a product.
     * @return associatedParts
     * */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }


}
