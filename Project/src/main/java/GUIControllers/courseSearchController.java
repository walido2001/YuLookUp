package GUIControllers;

import courseStructures.Course;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

import static courseScraper.mainScraper.getCourseList;
import static courseSearch.courseSearchMethods.searchCourse;

public class courseSearchController {
    public TextField userSearch;
    public Label courseCodeLabel;
    public Label courseNameLabel;
    public Label courseDescriptionLabel;
    public ListView<Course> resultsList;

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
}
