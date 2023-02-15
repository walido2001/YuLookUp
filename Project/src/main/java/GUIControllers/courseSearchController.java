package GUIControllers;

import courseStructures.Course;
import gui.CourseTableGui;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static courseScraper.mainScraper.getCourseList;
import static courseSearch.courseSearchMethods.searchCourse;

public class courseSearchController {

    public TextField userSearch;
    public Label courseCodeLabel;
    public Label courseNameLabel;
    public Label courseDescriptionLabel;
    public ListView<Course> resultsList;
    public Button majorsListViewButton;
    public Button gpaButton;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void setUserSearch(String s) {
        userSearch.setText(s);
        ActionEvent event = new ActionEvent();
        handleSearchClick(event);
    }

    public void handleMajorsListViewButton(ActionEvent e){
        CourseTableGui majorsListObject = new CourseTableGui();
        Scene majorsListScene = majorsListObject.getMajorsListScene();

        Stage newStage = new Stage();
        newStage.setScene(majorsListScene);
        newStage.setTitle("Courses by Major");
        newStage.show();
    }

    public void handleSearchClick(ActionEvent e)
    {
        String input = userSearch.getText();
        ArrayList<Course> results = searchCourse(input, getCourseList());

        resultsList.getItems().clear();
        resultsList.getItems().addAll(results);

        String courseCode = (results.size() > 0) ? results.get(0).getCode() : "Empty";
        String courseDescription = (results.size() > 0) ? results.get(0).getDescription() : "Empty";
        String courseName = (results.size() > 0) ? results.get(0).getName() : "Empty";
        courseCodeLabel.setText(courseCode);
        courseNameLabel.setText(courseName);
        courseDescriptionLabel.setText(courseDescription);

        resultsList.getSelectionModel().selectedItemProperty().addListener((list, old, newVal)->{
            courseCodeLabel.setText(newVal.getCode());
            courseNameLabel.setText(newVal.getName());
            courseDescriptionLabel.setText(newVal.getDescription());
        });
    }

    public void getGPACalculatorScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("gpaCalculator.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
