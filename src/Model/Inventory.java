package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * Contains fields and methods to model and manipulate an inventory of Parts and Products.
 * This class also contains methods for searching the inventory for specific items.
 * @author Hayley D'Angelo
 * */
public class Inventory {

    //FIELDS

    /**
     * A list of all the parts currently contained in the inventory.
     * */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**
     * A list of all the products currently contained in the inventory.
     * */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Initializes the starting ID for new parts in order to generate unique part IDs automatically.
     * */
    private static int partID = 0;

    /**
     * Initialized the started ID for new products in order to generate unique product IDs automatically.
     * */
    private static int productID = 0;


    //METHODS

    /**
     * Generates a unique ID for a new part.
     * @return partID
     * */
    public static int getNewPartId() {

        return ++partID;
    }

    /**
     * Generates a unique ID for a new product.
     * @return productID
     */
    public static int getNewProductId() {

        return ++productID;
    }

    /**
     * Adds a new part to the inventory.
     * @param part
     * */
    public static void addPart(Part part){
        allParts.add(part);
    }

    /**
     * Adds a new product to the inventory.
     * @param product
     * */
    public static void addProduct(Product product){
        allProducts.add(product);
    }

    /**
     * Gets a list of all the parts in the inventory.
     * @return allParts
     * */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Gets a list of all the products in the inventory.
     * @return allProducts
     * */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /**
     * Searches through the list of parts by part ID.
     * @param partId The ID to search
     * @return The part if found, null if not.
     * */
    public static Part lookupPart(int partId) {
        Part partFound = null;

        for (Part part : allParts) {
            if (part.getId() == partId) {
                partFound = part;
            }
        }

        return partFound;
    }

    /**
     * Searches through the list of products by product ID.
     * @param productId The ID to search
     * @return The product if found, null if not.
     * */
    public static Product lookupProduct(int productId) {
        Product productFound = null;

        for (Product product : allProducts) {
            if (product.getId() == productId) {
                productFound = product;
            }
        }

        return productFound;
    }

    /**
     * Searches through the list of parts by part name.
     * @param partName The name to search
     * @return The part if found, null if not.
     * */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> partsFound = FXCollections.observableArrayList();

        for (Part part : allParts) {
            if (part.getName().contains(partName)) {
                partsFound.add(part);
            }
        }

        return partsFound;
    }

    /**
     * Searches through the list of products by product ID.
     * @param productName The name to search
     * @return The product if found, null if not.
     * */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> productsFound = FXCollections.observableArrayList();

        for (Product product : allProducts) {
            if (product.getName().contains(productName)) {
                productsFound.add(product);
            }
        }

        return productsFound;
    }

    /**
     * Updates the values of a part object.
     * @param index The index of the part to be updated
     * @param selectedPart The replacing part
     * */
    public static void updatePart (int index, Part selectedPart) {

        allParts.set(index, selectedPart);
    }

    /**
     * Updates the values of a product object.
     * @param index The index of the product to be updated
     * @param selectedProduct The replacing product
     * */
    public static void updateProduct (int index, Product selectedProduct) {

        allProducts.set(index, selectedProduct);
    }

    /**
     * Deletes a part from the inventory.
     * @param selectedPart The part to be deleted
     * @return boolean true if part deleted, false if part is not deleted
     * */
    public static boolean deletePart(Part selectedPart) {
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Deletes a product from the inventory.
     * @param selectedProduct The product to be deleted
     * @return boolean true if product deleted, false if product is not deleted
     * */
    public static boolean deleteProduct(Product selectedProduct) {
        if (allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
            return true;
        }
        else {
            return false;
        }
    }

}
