package GUIControllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class mainController extends Application { // controller class
    private Stage window;
    public TextField searchBar;
    public Button homeSearchButton;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void start(Stage stage) throws IOException {
        this.window = stage;

        Parent searchView = FXMLLoader.load(getClass().getClassLoader().getResource("searchGUI.fxml"));

        Scene homeViewScene = new Scene(searchView);

        stage.setTitle("YULookUp");
        stage.setScene(homeViewScene);
        stage.show();

    }

    public void getSearchResultsScene(ActionEvent event) throws IOException {

        String firstInput = searchBar.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("searchReturnGUI.fxml"));
        root = loader.load();

        courseSearchController var = loader.getController();
        var.setUserSearch(firstInput);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) { // main class
        launch(args);
    }
}