package Controller;

import Model.InHouse;
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
 * Controller for the Modify Product screen of the application.
 * @author Hayley D'Angelo
 * */
public class ModifyProduct implements Initializable {

    /**
     * The text field for the maximum inventory of the product.
     * */
    @FXML
    private TextField productMaxTxt;

    /**
     * The text field for the minimum inventory of the product.
     * */
    @FXML
    private TextField productMinTxt;

    /**
     * The text field for the ID of the product.
     * It is disabled because the product ID is automatically generated.
     * */
    @FXML
    private TextField productIDTxt;

    /**
     * The text field for the name of the product.
     * */
    @FXML
    private TextField productNameTxt;

    /**
     * The text field for the inventory level of the product.
     * */
    @FXML
    private TextField productInvTxt;

    /**
     * The text field for the price of the product.
     * */
    @FXML
    private TextField productPriceTxt;

    /**
     * The text field that allows the user to search for a part.
     * */
    @FXML
    private TextField partSearch;

    /**
     * The table that shows the list of parts in the inventory.
     * */
    @FXML
    private TableView<Part> partsTableView;

    /**
     * The column of the Part Table that displays the ID of the parts.
     * */
    @FXML
    private TableColumn<Part, Integer> partIDCol;

    /**
     * The column of the Part Table that displays the names of the parts.
     * */
    @FXML
    private TableColumn<Part, String> partNameCol;

    /**
     * The column of the Parts table that displays the inventory level of the parts.
     * */
    @FXML
    private TableColumn<Part, Integer> partInventoryLevelCol;

    /**
     * The column of the Parts table that displays the price of the parts.
     * */
    @FXML
    private TableColumn<Part, Double> partPriceCol;

    /**
     * The table that displays the parts associated with the product.
     * */
    @FXML
    private TableView<Part> associatedPartsTableView;

    /**
     * The column of the associated parts table that displays the IDs of the parts.
     * */
    @FXML
    private TableColumn<Part, Integer> assocPartIDCol;

    /**
     * The column of the associated parts table that displays the names of the parts.
     * */
    @FXML
    private TableColumn<Part, String> assocPartNameCol;

    /**
     * The column of the associated parts table that displays the inventory level of the parts.
     * */
    @FXML
    private TableColumn<Part, Integer> assocPartInventoryLevelCol;

    /**
     * The column of the associated parts table that displays the price of the parts.
     * */
    @FXML
    private TableColumn<Part, Double> assocPartPriceCol;

    /**
     * The product selected from the Main Menu.
     * */
    Product selectedProduct;

    /**
     * A list that contains the parts associated with the selected product.
     * */
    private ObservableList<Part> assocParts = FXCollections.observableArrayList();

    /**
     * Instructs the parts table to display all parts if the search bar is empty.
     * @param event The text in the search bar is deleted.
     * */
    @FXML
    void partSearchKeyPressed(KeyEvent event) {

        if (partSearch.getText().isEmpty()) {
            partsTableView.setItems(Inventory.getAllParts());
        }

    }

    /**
     * Searches the part table by part ID or part name.
     * Displays an error if the part is not found.
     * @param event A letter or number is typed in the search bar.
     * */
    @FXML
    void partSearchKeyTyped(KeyEvent event) {

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
                displayAlert(2);
            }

        }

    }

    /**
     * Adds a part to the associated parts table for the product.
     * Displays an error message if no part is selected.
     * @param event The Add button is clicked.
     * */
    @FXML
    void onActionAddAssociatedPart(ActionEvent event) {

        Part selectedPart = partsTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            displayAlert(5);
        } else {
            selectedProduct.addAssociatedPart(selectedPart);
            assocParts = selectedProduct.getAllAssociatedParts();
            associatedPartsTableView.setItems(assocParts);
        }

    }

    /**
     * Cancels Modify Product and returns the user to the main menu.
     * Prompts the user to confirm they want to cancel to avoid accidental data loss.
     * @param event The cancel button is clicked.
     * @throws IOException
     * */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Do you want cancel Modify Product and return to the main menu?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            returnToMainMenu(event);
        }

    }

    /**
     * Removes a part from the list of parts associated with the selected product.
     * Displays an alert if no part is selected and confirms deletion.
     * @param event The Remove Associated Parts button is clicked.
     * */
    @FXML
    void onActionRemoveAssociatedPart(ActionEvent event) {

        Part selectedPart = associatedPartsTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            displayAlert(5);
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Do you want to remove the selected part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                selectedProduct.deleteAssociatedPart(selectedPart);
                assocParts = selectedProduct.getAllAssociatedParts();
                associatedPartsTableView.setItems(assocParts);

            }
        }

    }

    /**
     * Updates the selected product the inventory and takes the user back to the main menu.
     * Displays various error messages if the text fields do not have valid values.
     * @param event The Save button is clicked.
     * */
    @FXML
    void onActionSaveProduct(ActionEvent event) {

        try {
            int id = selectedProduct.getId();
            String name = productNameTxt.getText();
            Double price = Double.parseDouble(productPriceTxt.getText());
            int stock = Integer.parseInt(productInvTxt.getText());
            int min = Integer.parseInt(productMinTxt.getText());
            int max = Integer.parseInt(productMaxTxt.getText());
            int index = id - 1;

            if (name.isEmpty()) {
                displayAlert(6);
            } else {
                if (minValid(min, max) && inventoryValid(min, max, stock)) {

                    Product newProduct = new Product(id, name, price, stock, min, max);
                    Inventory.updateProduct(index, newProduct);

                    for (Part part : assocParts) {
                        newProduct.addAssociatedPart(part);
                    }

                    returnToMainMenu(event);
                }
            }
        } catch (Exception e){
            displayAlert(1);
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
     * Checks if the min entered is valid (less than max and greater than 0).
     * @param min
     * @param max
     * @return boolean value true if the min is valid, false if it is not.
     * */
    private boolean minValid(int min, int max) {

        boolean isValid = true;

        if (min <= 0 || min >= max) {
            isValid = false;
            displayAlert(3);
        }

        return isValid;
    }

    /**
     * Checks if the inventory level entered is valid (not less than min or greater than max).
     * @param min
     * @param max
     * @param stock
     * @return boolean value true if the inventory level is valid, false if it is not.
     * */
    private boolean inventoryValid(int min, int max, int stock) {

        boolean isValid = true;

        if (stock < min || stock > max) {
            isValid = false;
            displayAlert(4);
        }

        return isValid;
    }

    /**
     * Returns the user to the Main Menu.
     * @param event
     * @throws IOException
     * */
    private void returnToMainMenu(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("../view/MainMenu.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Displays alerts that describe invalid entries.
     * @param alertType The case the alert should cover.
     * */
    private void displayAlert(int alertType) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);

        switch (alertType) {
            case 1:
                alert.setTitle("Error");
                alert.setHeaderText("Error Adding Product");
                alert.setContentText("Form contains blank fields or invalid values.");
                alert.showAndWait();
                break;
            case 2:
                alertInfo.setTitle("Information");
                alertInfo.setHeaderText("Part not found");
                alertInfo.showAndWait();
                break;
            case 3:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Min");
                alert.setContentText("Min must be a number greater than 0 and less than Max.");
                alert.showAndWait();
                break;
            case 4:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Inventory");
                alert.setContentText("Inventory must be a number equal to or between Min and Max");
                alert.showAndWait();
                break;
            case 5:
                alert.setTitle("Error");
                alert.setHeaderText("Part not selected");
                alert.showAndWait();
                break;
            case 6:
                alert.setTitle("Error");
                alert.setHeaderText("Name Empty");
                alert.setContentText("Name cannot be empty.");
                alert.showAndWait();
                break;
        }
    }

    /**
     * Initializes the controller and populates the text fields with the values of the product selected from the Main Menu.
     * @param url
     * @param resourceBundle
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        selectedProduct = MainMenu.getProductToModify();
        assocParts = selectedProduct.getAllAssociatedParts();

        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        partsTableView.setItems(Inventory.getAllParts());

        assocPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPartInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assocPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        associatedPartsTableView.setItems(assocParts);

        productIDTxt.setText(String.valueOf(selectedProduct.getId()));
        productNameTxt.setText(selectedProduct.getName());
        productInvTxt.setText(String.valueOf(selectedProduct.getStock()));
        productPriceTxt.setText(String.valueOf(selectedProduct.getPrice()));
        productMaxTxt.setText(String.valueOf(selectedProduct.getMax()));
        productMinTxt.setText(String.valueOf(selectedProduct.getMin()));

    }
}
