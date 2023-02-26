package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * A controller class that controls the functions of the Main Menu of the application.
 * @author Hayley D'Angelo
 * */
public class MainMenu implements Initializable {

    /**
     * The text field that allows the user to search for a part.
     * */
    @FXML
    private TextField partSearch;

    /**
     * A table that displays a list of parts in the inventory.
     * */
    @FXML
    private TableView<Part> partsTableView;

    /**
     * The column of the parts table that displays the part IDs.
     * */
    @FXML
    private TableColumn<Part, Integer> partIDCol;

    /**
     * The column of the parts table that displays the part names.
     * */
    @FXML
    private TableColumn<Part, String> partNameCol;

    /**
     * The column of the parts table that displays the inventory levels of the parts.
     * */
    @FXML
    private TableColumn<Part, Integer> partInventoryLevelCol;

    /**
     * The column of the parts table that displays the price of the parts.
     * */
    @FXML
    private TableColumn<Part, Double> partPriceCol;

    /**
     * The text field that allows a user to search for a product.
     * */
    @FXML
    private TextField productSearch;

    /**
     * A table that displays a list of products in the inventory.
     * */
    @FXML
    private TableView<Product> productsTableView;

    /**
     * The column of the product table that displays the product IDs.
     * */
    @FXML
    private TableColumn<Product, Integer> productIDCol;

    /**
     * The column of the product table that displays the product names.
     * */
    @FXML
    private TableColumn<Product, String> productNameCol;

    /**
     * The column of the product table that displays the inventory levels of the products.
     * */
    @FXML
    private TableColumn<Product, Integer> productInventoryLevelCol;

    /**
     * The column of the product table that displays the price of the products.
     * */
    @FXML
    private TableColumn<Product, Double> productPriceCol;

    /**
     * The part selected by the user that will be modified in the Modify Parts screen.
     * */
    private static Part partToModify;

    /**
     * The product selected by the user that will be modified in the Modify Product screen.
     * */
    private static Product productToModify;

    /**
     * Gets the part the user has selected to modify.
     * @return partToModify The part to be modified or null if nothing is selected.
     * */
    public static Part getPartToModify() {

        return partToModify;

    }

    /**
     * Gets the product the user has selected to modify.
     * @return productToModify The product to be modified or null if nothing is selected.
     * */
    public static Product getProductToModify() {

        return productToModify;

    }

    /**
     * Loads the Add Part screen.
     * @param event
     * @throws IOException
     * */
    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("../view/AddPart.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Loads the Add Product screen.
     * @param event
     * @throws IOException
     * */
    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("../view/AddProduct.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Deletes the selected part from the inventory.
     * Displays an error message if no part is selected and a confirmation dialog before deletion.
     * @param event
     * */
    @FXML
    void onActionDeletePart(ActionEvent event) {

        Part selectedPart = partsTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            displayAlert(3);
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Are you sure you want to delete the selected part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(selectedPart);
            }
        }

    }

    /**
     * Deletes the selected product from the inventory.
     * Displays an error message if no product is selected and a confirmation before deletion.
     * @param event
     * */
    @FXML
    void onActionDeleteProduct(ActionEvent event) {

        Product selectedProduct = productsTableView.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            displayAlert(4);
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Are you sure you want to delete the selected product?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {

                ObservableList<Part> associatedParts = selectedProduct.getAllAssociatedParts();

                if (associatedParts.size() >= 1) {
                    displayAlert(5);
                } else {
                    Inventory.deleteProduct(selectedProduct);
                }
            }
        }

    }

    /**
     * Exits the application.
     * @param event The exit button is clicked.
     * */
    @FXML
    void onActionExitProgram(ActionEvent event) {

        System.exit(0);

    }

    /**
     * Loads the Modify Part screen.
     * Displays an error message if no part is selected.
     * @param event The modify part button is clicked.
     * @throws IOException
     * */
    @FXML
    void onActionModifyPart(ActionEvent event) throws IOException {

        partToModify = partsTableView.getSelectionModel().getSelectedItem();

        if (partToModify == null) {
            displayAlert(3);
        } else {
            Parent parent = FXMLLoader.load(getClass().getResource("../view/ModifyPart.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }

    }

    /**
     * Loads the Modify Product screen.
     * Displays an error message if no product is selected.
     * @param event The modify product button is clicked.
     * @throws IOException
     * */
    @FXML
    void onActionModifyProduct(ActionEvent event) throws IOException {

        productToModify = productsTableView.getSelectionModel().getSelectedItem();

        if (productToModify == null) {
            displayAlert(4);
        } else {
            Parent parent = FXMLLoader.load(getClass().getResource("../view/ModifyProduct.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }

    }

    /**
     * Instructs the parts table to display all parts if the search bar is empty.
     * @param event The text in the search bar is deleted.
     * */
    @FXML
    void partsSearchKeyPressed(KeyEvent event) {

        if (partSearch.getText().isEmpty()) {
            partsTableView.setItems(Inventory.getAllParts());
        }

    }

    /**
     * Allows the user to search for a part by part ID or part name.
     * Displays an alert if no parts are found.
     *
     * RUNTIME OR LOGICAL ERROR
     * lookupPart is an overloaded method that can accept either an integer to return one part or
     * a string to return a list of parts.  If you do not force the application to check if the string
     * entered in the search text field is an integer, it automatically assumes it's a string and
     * therefore the user cannot search by part ID but only by part name. By using an if else statement
     * and checking if the text in the text field is an integer and then parsing it as such, you can access
     * both versions of the overloaded lookupPart method.  See code below and also the lookUpPart method
     * in the Inventory class.
     *
     * @param event Text is typed into the search bar.
     * */
    @FXML
    void partsSearchKeyTyped(KeyEvent event) {

        ObservableList<Part> displayParts = FXCollections.observableArrayList();
        String lookupPart = partSearch.getText();

        if (!partSearch.getText().isEmpty()) {
            if (isInteger(lookupPart) == true) {
                int partSearchID = Integer.parseInt(lookupPart);
                Part partFound = Inventory.lookupPart(partSearchID);
                if (!(partFound == null)) {
                    displayParts.add(partFound);
                }
            }
            else {
                displayParts = Inventory.lookupPart(lookupPart);
            }

            partsTableView.setItems(displayParts);

            if (displayParts.isEmpty()) {
                displayAlert(1);
            }

        }
    }

    /**
     * Instructs the products table to display all products if the search bar is empty.
     * @param event The text in the search bar is deleted.
     * */
    @FXML
    void productsSearchKeyPressed(KeyEvent event) {

        if (productSearch.getText().isEmpty()) {
            productsTableView.setItems(Inventory.getAllProducts());
        }

    }

    /**
     * Searches the product table by product ID or product name.
     * Displays an error if the product is not found.
     * @param event A letter or number is typed in the search bar.
     * */
    @FXML
    void productsSearchKeyTyped(KeyEvent event) {

        ObservableList<Product> displayProducts = FXCollections.observableArrayList();
        String lookupProduct = productSearch.getText();

        if (!productSearch.getText().isEmpty()) {
            if (isInteger(lookupProduct) == true) {
                int productSearchID = Integer.parseInt(lookupProduct);
                Product productFound = Inventory.lookupProduct(productSearchID);
                if (!(productFound == null)) {
                    displayProducts.add(productFound);
                }
            }
            else {
                displayProducts = Inventory.lookupProduct(lookupProduct);
            }

            productsTableView.setItems(displayProducts);

            if (displayProducts.isEmpty()) {
                displayAlert(1);
            }

        }

    }

    /**
     * Detects if a string is an integer.
     * @param input
     * @return boolean value true if the string is an integer, false if it is not.
     * */
    private static boolean isInteger(String input)
    {
        try
        { int i = Integer.parseInt(input);
            return true;
        } catch(NumberFormatException er) {
            return false;
        }
    }

    /**
     * Displays various alert messages depending on the situation.
     * @param alertType The alert case that should be displayed.
     * */
    private void displayAlert(int alertType) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Alert alertError = new Alert(Alert.AlertType.ERROR);

        switch (alertType) {
            case 1:
                alert.setTitle("Information");
                alert.setHeaderText("Part not found");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("Information");
                alert.setHeaderText("Product not found");
                alert.showAndWait();
                break;
            case 3:
                alertError.setTitle("Error");
                alertError.setHeaderText("Part not selected");
                alertError.showAndWait();
                break;
            case 4:
                alertError.setTitle("Error");
                alertError.setHeaderText("Product not selected");
                alertError.showAndWait();
                break;
            case 5:
                alertError.setTitle("Error");
                alertError.setHeaderText("Parts Associated");
                alertError.setContentText("Please remove all parts from a product before deleting.");
                alertError.showAndWait();
                break;
        }
    }

    /**
     * Initializes the Main Menu and populates the part and product tables.
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Populate parts table view
        partsTableView.setItems(Inventory.getAllParts());
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Populate products table view
        productsTableView.setItems(Inventory.getAllProducts());
        productIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }
}
