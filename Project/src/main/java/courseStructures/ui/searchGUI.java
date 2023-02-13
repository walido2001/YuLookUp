package courseStructures.ui;



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * This is the controller class where the instance variables are
 * declared. This class also includes all methods including the main
 * and start methods.
 */
public class searchGUI extends Application { // controller class

    @FXML
    private AnchorPane paneMain;
    @FXML
    private AnchorPane paneRed;
    @FXML
    private TextField searchBar;
    @FXML
    private Button searchButton;
    @FXML
    private Label yuLabel;

    /**
     * This method returns what is inputted into the searchbar as a string
     * @param event indicates that component defined action has occurred
     * @return the text inside the searchbar as a string
     */
    @FXML
    public String getUserInput(ActionEvent event) {
        System.out.println(searchBar.getText());
        return searchBar.getText();
    }

    /**
     * This is the start method that creates the scene and sets
     * the application window to visible.
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws IOException if the fxml file containing the UI data is not found
     */
    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("searchGUI.fxml"));

        stage.setTitle("YULookUp");
        stage.setScene(new Scene(root, 600 , 400));
        stage.show();

    }

    /**
     * This is the main method that launches the GUI
     * @param args the command line arguments
     */
    public static void main(String[] args) { // main class
        launch(args);
    }

}