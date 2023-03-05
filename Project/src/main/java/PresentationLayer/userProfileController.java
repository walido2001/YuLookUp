package PresentationLayer;

import BusinessLogicLayer.Course;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import static PersistenceLayer.mainScraper.getCourseList;
import static BusinessLogicLayer.courseSearchMethods.searchCourse;

public class userProfileController extends Application {
    @FXML
    public TextField searchField;
    public Hyperlink loginLink, majorCourseViewLink, gpaCalculatorViewLink, scheduleViewLink;
    public Label studentName, studentId, studentMajor, creditsEarned, cgpa;
    public TableView coursesTakenTable;
    public Button deleteButton, searchButton, addCourseButton;
    public ListView<Course> resultList;
    public ComboBox selectGrade;

    String input = searchField.getText();
    ArrayList<Course> results = searchCourse(input, getCourseList());

    resultList.getItems().clear();
    resultList.getItems().addAll(results);


    addCourseButton.setOnAction(event -> {
        Course selectedCourse = resultList.getSelectedItem();
    });




    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }
}
