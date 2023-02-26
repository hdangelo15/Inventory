package Inventory_Management;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This application is an Inventory Management System that allows the user to manage parts and products.
 *
 * FUTURE ENHANCEMENT
 * This application could be expanded in the future by allowing the user to filter by parts by Machine ID or Company Name.
 * This way, it's easy to see at a glance which parts each machine or company are providing.  This would allow the user
 * to see right away which parts might be affected if there were to be a supply chain problem with a certain machine or
 * company.
 *
 * @author Hayley D'Angelo
 * */

public class Main extends Application {


    /**
     * The start method loads the primary stage, the Main Menu of the program.
     *
     * @param primaryStage
     * @throws Exception
     * */

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/MainMenu.fxml"));
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(root, 900, 360));
        primaryStage.show();
    }

    /**
     * The main method initializes the program.
     *
     * It loads the current parts and products and launches the program.
     *
     * @param args
     * */

    //THE JAVADOC FOLDER IS LOCATED AT D'Angelo_Hayley_C482/javadoc
    public static void main(String[] args) {

        //Add sample parts
        int partId = Inventory.getNewPartId();
        InHouse lcdMonitor = new InHouse(partId,"LCD Monitor", 399.99, 10, 2, 20,
                72);
        partId = Inventory.getNewPartId();
        InHouse pcTower = new InHouse(partId,"PC Tower", 999.99, 5, 1, 10,
                65);
        partId = Inventory.getNewPartId();
        Outsourced keyboard = new Outsourced(partId,"Keyboard", 49.99, 25, 10, 40,
                "GameReal");
        partId = Inventory.getNewPartId();
        Outsourced mouse = new Outsourced(partId, "Mouse",29.99, 50, 15,
                150, "GameReal");
        partId = Inventory.getNewPartId();
        Outsourced chair = new Outsourced(partId, "Gaming Chair",299.99, 20, 5,
                30, "GameReal");
        Inventory.addPart(lcdMonitor);
        Inventory.addPart(pcTower);
        Inventory.addPart(keyboard);
        Inventory.addPart(mouse);
        Inventory.addPart(chair);

        //Add sample products
        int productId = Inventory.getNewProductId();
        Product pcSetup = new Product(productId, "PC Setup", 1399.99, 3, 1,
                10);
        pcSetup.addAssociatedPart(lcdMonitor);
        pcSetup.addAssociatedPart(pcTower);
        pcSetup.addAssociatedPart(keyboard);
        pcSetup.addAssociatedPart(mouse);

        productId = Inventory.getNewProductId();
        Product keyboardBundle = new Product(productId, "Keyboard Bundle", 59.99, 10, 5,
                20);
        keyboardBundle.addAssociatedPart(keyboard);
        keyboardBundle.addAssociatedPart(mouse);

        Inventory.addProduct(pcSetup);
        Inventory.addProduct(keyboardBundle);

        launch(args);
    }
}
