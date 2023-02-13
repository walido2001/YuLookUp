package GUIControllers;

import gui.CourseTableGui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class mainController extends Application { // controller class
    private Stage window;
    //    public AnchorPane paneMain;
    //    public AnchorPane paneRed;
    public TextField searchBar;
    public Button homeSearchButton;
    //    public Label yuLabel;

    @Override
    public void start(Stage stage) throws IOException {
        this.window = stage;

        //        Parent homeView = FXMLLoader.load(getClass().getClassLoader().getResource("searchGUI.fxml"));
        Parent searchView = FXMLLoader.load(getClass().getClassLoader().getResource("2ndCourseSearchView.fxml"));
        //        CourseTableGui majorsListObject = new CourseTableGui();
        //        Scene majorsListScene = majorsListObject.getMajorsListScene();

        Scene homeViewScene = new Scene(searchView);
        //        Scene searchViewScene = new Scene();

        stage.setTitle("YULookUp");
        stage.setScene(homeViewScene);
        stage.show();

    }

    public static void main(String[] args) { // main class
        launch(args);
    }
}