package PresentationLayer;

import BusinessLogicLayer.Course;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static BusinessLogicLayer.scheduleGenerator.*;
import static PersistenceLayer.mainScraper.getCourseList;
import static BusinessLogicLayer.courseSearchandFilterMethods.searchCourse;

import java.io.IOException;
import java.util.ArrayList;

public class scheduleGeneratorGUIController extends Application {

    public ListView courseList;
    public ListView takenList;
    public Button addButton;
    public Button removeButton;
    public Button civilButton;
    public Button mechanicalButton;
    public Button electricalButton;
    public Button computerButton;
    public Text textBox;
    public TextField searchBar;
    public Button searchButton;
    public String major;
    public Button backButton;
    public Text fourthText;
    public Text thirdText;
    public Text secondText;
    public Text firstText;
    public ListView firstList;
    public ListView secondList;
    public ListView thirdList;
    public ListView fourthList;
    public AnchorPane pane;
    public ImageView yuLogo;
    private Stage stage;
    private Scene scene;
    private Parent root;

//    public static void main(String[] args) {
//        launch(args);
//    }

    @Override
    public void start(Stage primaryStage) {

    }

    public void addCourse(ActionEvent event) {
        takenList.getItems().addAll(courseList.getSelectionModel().getSelectedItems());
    }

    public void removeCourse(ActionEvent event) {

        int index = takenList.getSelectionModel().getSelectedIndex();
        try {
            takenList.getItems().remove(index);
        }catch(Exception e) {
            System.out.println("error: nothing selected to remove");
        }
        //takenList.getItems().clear();
    }

    public void setCivilMajor(ActionEvent event) throws IOException {
        ArrayList<Course> taken = new ArrayList<>(takenList.getItems());
        System.out.println(taken);
        scheduleBuilder("civil",taken);
        fillSchedule(event);
    }

    public void setMechanicalMajor(ActionEvent event) throws IOException {
        ArrayList<Course> taken = new ArrayList<>();
        taken.addAll(takenList.getItems());
        scheduleBuilder("mechanical",taken);
        fillSchedule(event);
    }

    public void setElectricalMajor(ActionEvent event) throws IOException {
        ArrayList<Course> taken = new ArrayList<>();
        taken.addAll(takenList.getItems());
        scheduleBuilder("electrical",taken);
        fillSchedule(event);
    }

    public void setComputerMajor(ActionEvent event) throws IOException {
        ArrayList<Course> taken = new ArrayList<>();
        taken.addAll(takenList.getItems());
        scheduleBuilder("computer",taken);
        fillSchedule(event);
    }

    public void handleSearch(ActionEvent event) {
        String input = searchBar.getText();
        ArrayList<Course> results = searchCourse(input, getCourseList());
        courseList.getItems().clear();
        courseList.getItems().addAll(results);
    }

    public void fillSchedule(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("generatedScheduleGUI.fxml"));
        root = loader.load();

        scheduleGeneratorGUIController var = loader.getController();
        var.fill();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void fill() {
        firstList.getItems().addAll(firstYear);
        secondList.getItems().addAll(secondYear);
        thirdList.getItems().addAll(thirdYear);
        fourthList.getItems().addAll(fourthYear);
    }


    public void getscheduleGenerator(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("scheduleGeneratorGUI.fxml"));
        root = loader.load();

        scheduleGeneratorGUIController var = loader.getController();
        var.setUserSearch("");

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void getSearchResultsScreen(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("searchReturnGUI.fxml"));
        root = loader.load();

        courseSearchController var = loader.getController();
        var.setUserSearch("");

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void setUserSearch(String s) {
        searchBar.setText(s);
        ActionEvent event = new ActionEvent();
        handleSearch(event);
    }
}
