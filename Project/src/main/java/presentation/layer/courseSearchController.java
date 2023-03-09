package presentation.layer;

import business.logic.layer.Course;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static business.logic.layer.courseSearchandFilterMethods.*;
import static persistence.layer.mainScraper.getCourseList;

public class courseSearchController {

    public TextField userSearch;
    public Label courseCodeLabel;
    public Label courseNameLabel;
    public Label courseDescriptionLabel;
    public ListView<Course> resultsList;

    //Other sections access
    public Button majorsListViewButton;
    public Button gpaButton;
    public Button userProfileButton;

    //Filtering Components
    public ChoiceBox facultyDropDown;
    public ChoiceBox subjectDropDown;
    public TextField courseLevelInput;
    public TextField creditAmountInput;

    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * This method sets the text in the searchbar to the string s
     * and calls the handleSearchClick method to search what is
     * inputted as a string and return results
     * @param s The string that is inputted into the searchbar
     */
    public void setUserSearch(String s) {
        userSearch.setText(s);
        ActionEvent event = new ActionEvent();
        handleSearchClick(event);
    }

    public void userProfileButtonOnClick(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("userProfile.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void handleMajorsListViewButton(ActionEvent e){
        courseTableGUIController majorsListObject = new courseTableGUIController();
        Scene majorsListScene = majorsListObject.getMajorsListScene();

        Stage stage = (Stage) majorsListViewButton.getScene().getWindow();
        stage.close(); // closes previous stage

        Stage newStage = new Stage();
        newStage.setScene(majorsListScene);
        newStage.setTitle("YULookUp");
        newStage.show();
    }

    public void handleSearchClick(ActionEvent e)
    {
        String input = userSearch.getText();
        ArrayList<Course> results = searchCourse(input, getCourseList());

        ArrayList<String> facultyOptions = new ArrayList<>();
        ArrayList<String> subjectOptions = new ArrayList<>();

        if (facultyDropDown.getValue() != null)
        {
            try
            {
                String facultyDropDownInput = (String) facultyDropDown.getValue();
                results = filterDepartment(results, facultyDropDownInput);
            }
            catch(Exception error)
            {}
        }

        if (subjectDropDown.getValue() != null)
        {
            try
            {
                String subjectDropDownInput = (String) subjectDropDown.getValue();
                results = filterSubject(results, subjectDropDownInput);
            }
            catch(Exception error)
            {}
        }

        for (Course course : results)
        {
            if (!facultyOptions.contains(course.getFacultyType()))
            {
                facultyOptions.add(course.getFacultyType());
            }

            if (!subjectOptions.contains(course.getSubjectType()))
            {
                subjectOptions.add(course.getSubjectType());
            }
        }

        facultyDropDown.setItems(FXCollections.observableArrayList(facultyOptions));
        subjectDropDown.setItems(FXCollections.observableArrayList(subjectOptions));

        if (courseLevelInput.getText() != null && !courseLevelInput.getText().isEmpty())
        {
            try
            {
                int courseLevelInputVar = Integer.parseInt(courseLevelInput.getText());
                results = filterYearLevel(results, courseLevelInputVar);
            }
            catch(Exception error)
            {}
        }

        if (creditAmountInput.getText() != null && !creditAmountInput.getText().isEmpty())
        {
            try
            {
                int creditAmountInputVar = Integer.parseInt(creditAmountInput.getText());
                results = filterCreditAmount(results, creditAmountInputVar);
            }
            catch(Exception error)
            {}
        }

        courseLevelInput.clear();
        creditAmountInput.clear();

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

    // This method switches to the gpa calculator scene
    public void getGPACalculatorScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("gpaCalculator.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
