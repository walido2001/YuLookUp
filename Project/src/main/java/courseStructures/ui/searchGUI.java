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

    @FXML
    void getUserInput(ActionEvent event) {
        String input = searchBar.getText();
        System.out.println(input);
    }


    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("searchGUI.fxml"));

        stage.setTitle("YULookUp");
        stage.setScene(new Scene(root, 600 , 400));
        stage.show();

    }

    public static void main(String[] args) { // main class
        launch(args);
    }

}