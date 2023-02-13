package GUIControllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class mainController extends Application { // controller class

    //    public AnchorPane paneMain;
//    public AnchorPane paneRed;
    public TextField searchBar;
    public Button homeSearchButton;
//    public Label yuLabel;

    @Override
    public void start(Stage stage) throws IOException {

//        Parent homeView = FXMLLoader.load(getClass().getClassLoader().getResource("searchGUI.fxml"));
        Parent searchView = FXMLLoader.load(getClass().getClassLoader().getResource("2ndCourseSearchView.fxml"));

//        Scene homeViewScene = new Scene(homeView);
        Scene searchViewScene = new Scene(searchView);

        stage.setTitle("YULookUp");
        stage.setScene(searchViewScene);
        stage.show();

    }

    public static void main(String[] args) { // main class
        launch(args);
    }

}