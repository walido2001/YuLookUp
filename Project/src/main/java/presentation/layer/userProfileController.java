package presentation.layer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import business.logic.layer.Course;
import business.logic.layer.TakenCourse;
import business.logic.layer.UserProfile;
import business.logic.layer.UserProfileInstance;
import static persistence.layer.mainScraper.getCourseList;
import static business.logic.layer.courseSearchandFilterMethods.searchCourse;

public class userProfileController {
    @FXML
    public TextField searchField, nameField, studentNumberField, majorField;
    public Hyperlink majorCourseViewLink, gpaCalculatorViewLink, scheduleViewLink;
    public Label studentName, studentId, studentMajor, creditsEarned, cgpa;
    public TableView<TakenCourse> coursesTakenTable;
    public Button deleteButton, searchButton, addCourseButton;
    public ListView<Course> resultList;
    public ComboBox selectGrade;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Course selectedCourse;
    private int grade = totalCredits = 0;
    private double cgpaVal = 0;
    private double gradePoint = 0;
    private int totalCredits = 0;
    private UserProfile currAccount;

    // This method initializes the user profile as well as some of its gui components: table, fields, labels
    public void initialize() {
        TableColumn<TakenCourse,String> courseCol = new TableColumn<>("Course");
        TableColumn<TakenCourse,String> gradeCol = new TableColumn<>("Grade");
        courseCol.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        gradeCol.setCellValueFactory(new PropertyValueFactory<>("grade"));
        coursesTakenTable.getColumns().setAll(courseCol, gradeCol);
        selectGrade.getItems().addAll("A+","A","B+","B","C+","C","D+","D","E","F");

        this.currAccount = UserProfileInstance.getUserProfile();

        nameField.setText(this.currAccount.getName());
        studentNumberField.setText(this.currAccount.getStudentID());
        majorField.setText(this.currAccount.getMajor());

        if(this.currAccount.getCourses() != null) coursesTakenTable.getItems().setAll(this.currAccount.getCourses());
        updateUserStats();

        nameField.textProperty().addListener((obselete, obselete2, newText) -> {
            this.currAccount.setName(newText);
        });
        studentNumberField.textProperty().addListener((obselete, obselete2, newText) -> {
            this.currAccount.setStudentID(newText);
        });
        majorField.textProperty().addListener((obselete, obselete2, newText) -> {
            this.currAccount.setMajor(newText);
        });

    }

    // The method handles the course search feature.
    // It utilizes the searchCourse() method found in courseSearchandFilterMethods
    public void searchCourseHandler(ActionEvent actionEvent) {
        String input = searchField.getText();
        ArrayList<Course> results = searchCourse(input, getCourseList());

        resultList.getItems().clear();
        resultList.getItems().addAll(results);

        resultList.getSelectionModel().selectedItemProperty().addListener((list, old, newVal)->{
            selectedCourse = newVal;
        });
    }

    // This method handles the on-click action when a user tries to add a course to "Courses Taken" table
    public void addCourseHandler(ActionEvent actionEvent) {
        if(selectGrade.getValue()!=null && !contains(coursesTakenTable, selectedCourse)){
            coursesTakenTable.getItems().add(new TakenCourse(selectedCourse, (String) selectGrade.getValue()));
            updateUserStats();
        }
    }

    // This method handles the on-click action of a grade drop-down menu, and converts a letter grade to an integer
    public void gradeSelectionHandler(ActionEvent actionEvent) {
        String value = (String) selectGrade.getValue();
        if(value.equals("A+")) grade=9;
        else if(value.equals("A")) grade=8;
        else if(value.equals("B+")) grade=7;
        else if(value.equals("B")) grade=6;
        else if(value.equals("C+")) grade=5;
        else if(value.equals("C")) grade=4;
        else if(value.equals("D+")) grade=3;
        else if(value.equals("D")) grade=2;
        else if(value.equals("E")) grade=1;
        else grade=0;
    }

    // This method handles the on-click action when a user tries to remove a course to "Courses Taken" table
    public void deleteCourseHandler(ActionEvent actionEvent) {
        if(coursesTakenTable.getSelectionModel().getSelectedItem()!=null) {
            coursesTakenTable.getItems().removeAll(coursesTakenTable.getSelectionModel().getSelectedItem());
            updateUserStats();
        }
    }

    // This method updated user academic standing (gpa and credits) by iterating through a table of taken courses
    // It also updates the UserProfile instance to reflect the up-to-date list of taken courses
    public void updateUserStats(){
        totalCredits = 0;
        gradePoint = cgpaVal = 0;
        for(TakenCourse tc:coursesTakenTable.getItems()){
            totalCredits += tc.getCredit();
            gradePoint += tc.getGradeVal() * tc.getCredit();
        }
        cgpaVal = (totalCredits != 0) ? gradePoint / totalCredits : 0;

        creditsEarned.setText("Credits: " + totalCredits);
        String cgpaString = String.format("%.2f", cgpaVal);
        cgpa.setText( "CGPA: " + cgpaString);

        this.currAccount.setCourses(new ArrayList<>(coursesTakenTable.getItems()));
    }

    // The method check if the provided table contains a particular course; returns true if it contains, false otherwise
    public boolean contains(TableView<TakenCourse> table, Course obj){
        for(TakenCourse item: table.getItems())
            if (item.getCourseCode().equals(obj.getCode()))
                return true;
        return false;
    }

    // The method redirects the user to a "GPA Calculator" view/feature
    public void openGpaCalc(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("gpaCalculator.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // The method redirects the user to a "Major Courses" view/feature
    public void openMajorCourseView(ActionEvent actionEvent) throws IOException {
        courseTableGUIController majorsListObject = new courseTableGUIController();
        Scene majorsListScene = majorsListObject.getMajorsListScene();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close(); // closes previous stage

        Stage newStage = new Stage();
        newStage.setScene(majorsListScene);
        newStage.setTitle("YULookUp");
        newStage.show();
    }

    // The method redirects the user to a "Schedule" view/feature
    public void openScheduleView(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("scheduleGeneratorGUI.fxml"));
        root = loader.load();

        scheduleGeneratorGUIController var = loader.getController();
        var.setUserSearch("");

        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    // The method handles the on-click action of an import button
    // It loads the previously created user profile (in a json format) and populates all the fields: name, id, courses, etc.
    public void importButtonHandle(){
        Gson gson = new Gson();
        String jsonString="";
        UserProfile up = new UserProfile();
        try {
            jsonString = FileUtils.readFileToString(new File("currAccount.json"), StandardCharsets.UTF_8);
            up = gson.fromJson(jsonString, UserProfile.class);
        } catch (IOException e) {
            System.out.println("Failed to import userProfile (importButtonHandle())");
        }
        this.currAccount.setName(up.getName());
        this.currAccount.setMajor(up.getMajor());
        this.currAccount.setStudentID(up.getStudentID());
        this.currAccount.setCourses(up.getCourses());

        nameField.setText(this.currAccount.getName());
        studentNumberField.setText(this.currAccount.getStudentID());
        majorField.setText(this.currAccount.getMajor());
        if(this.currAccount.getCourses() != null)
            coursesTakenTable.getItems().setAll(this.currAccount.getCourses());
        updateUserStats();
    }

    // The method handles the on-click action of an import button
    // It exports the current state of a user profile in a json file and stores it in a project directory
    public void exportButtonHandle(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {
            FileWriter writer = new FileWriter("currAccount.json");
            writer.write(gson.toJson(this.currAccount));
            writer.close();
        }
        catch (Exception e) {
            System.out.println("Failed to export userProfile (exportButtonHandle())");
        }
    }
}