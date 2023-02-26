package Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * A controller class for the Add Part form.
 * Implements functionality for the user to save a new part.
 * @author Hayley D'Angelo
 * */
public class AddPart implements Initializable {

    /**
     * The radio button that defines the new part as InHouse.
     * */
    @FXML
    private RadioButton inhousePartRB;

    /**
     * The toggle group for the radio buttons.
     * */
    @FXML
    private ToggleGroup PART_TYPE;

    /**
     * The radio button that defines the new part as Outsourced.
     * */
    @FXML
    private RadioButton outsourcedPartRB;

    /**
     * The part ID text field. It is disabled to the user because IDs are auto-generated.
     * */
    @FXML
    private TextField partIDTxt;

    /**
     * The part name text field.
     * */
    @FXML
    private TextField partNameTxt;

    /**
     * The part inventory level text field.
     * */
    @FXML
    private TextField partInvTxt;

    /**
     * The part price text field.
     * */
    @FXML
    private TextField partPriceTxt;

    /**
     * The part maximum inventory level text field.
     * */
    @FXML
    private TextField partMaxTxt;

    /**
     * The text field that accepts either a machine ID (InHouse) or company name (Outsourced).
     * */
    @FXML
    private TextField partOriginTxt;

    /**
     * The part minimum inventory level text field.
     * */
    @FXML
    private TextField partMinTxt;

    /**
     * The label for the part origin text field that displays either machine ID (InHouse) or company name (Outsourced).
     * */
    @FXML
    private Label partOriginLabel;

    /**
     * Sets the part origin label to Machine ID.
     * @param event InHouse radio button is selected
     * */
    @FXML
    void onActionInhousePart(ActionEvent event) {

        partOriginLabel.setText("Machine ID");

    }

    /**
     * Sets the part origin label to Company Name.
     * @param event The Outsourced radio button is selected
     * */
    @FXML
    void onActionOutsourcedPart(ActionEvent event) {

        partOriginLabel.setText("Company Name");

    }

    /**
     * Cancels the Add Part form and returns the user to the main menu after confirmation.
     * @param event Cancel button clicked
     * @throws IOException
     * */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Do you want cancel Add Part and return to the main menu?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            returnToMainMenu(event);
        }

    }

    /**
     * Saves a new part to the inventory and returns the user to the main menu.
     * Displays error messages if invalid entries are typed into the text fields.
     * @param event Save button clicked
     * */
    @FXML
    void onActionSavePart(ActionEvent event) {

        try {
            int id = 0;
            String name = partNameTxt.getText();
            Double price = Double.parseDouble(partPriceTxt.getText());
            int stock = Integer.parseInt(partInvTxt.getText());
            int min = Integer.parseInt(partMinTxt.getText());
            int max = Integer.parseInt(partMaxTxt.getText());
            int machineId;
            String companyName;
            boolean partAdded = false;

            if (name.isEmpty()) {
                displayAlert(5);
            } else {
                if (minValid(min, max) && inventoryValid(min, max, stock)) {

                    if (inhousePartRB.isSelected()) {
                        try {
                            machineId = Integer.parseInt(partOriginTxt.getText());
                            InHouse newInHousePart = new InHouse(id, name, price, stock, min, max, machineId);
                            newInHousePart.setId(Inventory.getNewPartId());
                            Inventory.addPart(newInHousePart);
                            partAdded = true;
                        } catch (Exception e) {
                            displayAlert(2);
                        }
                    }

                    if (outsourcedPartRB.isSelected()) {
                        if (partOriginTxt.getText().isEmpty()) {
                            displayAlert(6);
                        }
                        else {
                            companyName = partOriginTxt.getText();
                            Outsourced newOutsourcedPart = new Outsourced(id, name, price, stock, min, max, companyName);
                            newOutsourcedPart.setId(Inventory.getNewPartId());
                            Inventory.addPart(newOutsourcedPart);
                            partAdded = true;
                        }
                    }

                    if (partAdded) {
                        returnToMainMenu(event);
                    }
                }
            }
        } catch(Exception e) {
            displayAlert(1);
        }

    }

    /**
     * Checks to see if the minimum inventory level entered is valid (greater than 0 and less than max).
     * @param min
     * @param max
     * @return boolean true if the min is valid, false if it is not
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
     * Checks to see if the inventory level entered is valid (between min and max).
     * @param min
     * @param max
     * @param stock
     * @return boolean true if the inventory is valid, false if it is not
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

        switch (alertType) {
            case 1:
                alert.setTitle("Error");
                alert.setHeaderText("Error Adding Part");
                alert.setContentText("Form contains blank fields or invalid values.");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Machine ID");
                alert.setContentText("Machine ID may only contain numbers and cannot be blank.");
                alert.showAndWait();
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
                alert.setContentText("Inventory must be a number equal to or between Min and Max.");
                alert.showAndWait();
                break;
            case 5:
                alert.setTitle("Error");
                alert.setHeaderText("Name Empty");
                alert.setContentText("Name cannot be empty.");
                alert.showAndWait();
                break;
            case 6:
                alert.setTitle("Error");
                alert.setHeaderText("Company Name Empty");
                alert.setContentText("Company Name cannot be empty.");
                alert.showAndWait();
                break;
        }
    }

    /**
     * Initializes the Add Part controller.
     * @param url
     * @param resourceBundle
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
