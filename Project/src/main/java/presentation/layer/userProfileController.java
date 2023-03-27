package presentation.layer;

import business.logic.layer.Course;
import business.logic.layer.TakenCourse;
import business.logic.layer.UserProfile;
import com.google.common.reflect.TypeToken;
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
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import static persistence.layer.mainScraper.getCourseList;
import static business.logic.layer.courseSearchandFilterMethods.searchCourse;

public class userProfileController {
    @FXML
    public TextField searchField;
    public Hyperlink loginLink, majorCourseViewLink, gpaCalculatorViewLink, scheduleViewLink;
    public Label studentName, studentId, studentMajor, creditsEarned, cgpa;
    public TableView<TakenCourse> coursesTakenTable;
    public Button deleteButton, searchButton, addCourseButton;
    public ListView<Course> resultList;
    public ComboBox selectGrade;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Course selectedCourse;
    private int grade = 0;
    private double cgpaVal = 0;
    private double gradePoint = 0;
    private int totalCredits = 0;

    public TextField nameField, studentNumberField, majorField;
    private UserProfile currAccount;
//    private String nameFieldValue, studentNumberFieldValue, majorFieldValue;

    public void initialize() {
        TableColumn<TakenCourse,String> courseCol = new TableColumn<>("Course");
        TableColumn<TakenCourse,String> gradeCol = new TableColumn<>("Grade");
        courseCol.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        gradeCol.setCellValueFactory(new PropertyValueFactory<>("grade"));
        coursesTakenTable.getColumns().setAll(courseCol, gradeCol);
        selectGrade.getItems().addAll("A+","A","B+","B","C+","C","D+","D","E","F");

        this.currAccount = new UserProfile();

        importButtonHandle();
        nameField.setText(this.currAccount.getName());
        studentNumberField.setText(this.currAccount.getStudentID());
        majorField.setText(this.currAccount.getMajor());

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

    public void searchCourseHandler(ActionEvent actionEvent) {
        String input = searchField.getText();
        ArrayList<Course> results = searchCourse(input, getCourseList());

        resultList.getItems().clear();
        resultList.getItems().addAll(results);

        resultList.getSelectionModel().selectedItemProperty().addListener((list, old, newVal)->{
            selectedCourse = newVal;
        });
    }

    public void addCourseHandler(ActionEvent actionEvent) {

        if(selectGrade.getValue()!=null && !contains(coursesTakenTable, selectedCourse)){
            double credit = Character.getNumericValue(selectedCourse.getCode().charAt(selectedCourse.getCode().length()-4));
            totalCredits += credit;
            gradePoint += (grade * credit);
            cgpaVal = gradePoint/totalCredits;
            updateUserStats();

            coursesTakenTable.getItems().add(new TakenCourse(selectedCourse, (String) selectGrade.getValue()));
        }
    }

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

    public void deleteCourseHandler(ActionEvent actionEvent) {
        if(coursesTakenTable.getSelectionModel().getSelectedItem()!=null) {
            TakenCourse c = coursesTakenTable.getSelectionModel().getSelectedItem();
            coursesTakenTable.getItems().removeAll(coursesTakenTable.getSelectionModel().getSelectedItem());

            totalCredits -= c.getCredit();
            gradePoint -= (c.getGradeVal() * c.getCredit());
            cgpaVal = (totalCredits != 0) ? gradePoint / totalCredits : 0;
            updateUserStats();
        }
    }

    public void updateUserStats(){
        creditsEarned.setText("Credits: " + totalCredits);
        String cgpaString = String.format("%.2f", cgpaVal);
        cgpa.setText( "CGPA: " + cgpaString);
    }
    //implemented using the approach explained at https://stackoverflow.com/questions/30610011/how-to-determine-if-record-exist-in-tableview-in-javafx
    public boolean contains(TableView<TakenCourse> table, Course obj){
        for(TakenCourse item: table.getItems())
            if (item.getCourseCode().equals(obj.getCode()))
                return true;
        return false;
    }

    public void openGpaCalc(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("gpaCalculator.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

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

    public void importButtonHandle()
    {
        Gson gson = new Gson();
        String jsonString="";
        try {
            jsonString = FileUtils.readFileToString(new File("currAccount.json"), StandardCharsets.UTF_8);
            this.currAccount = gson.fromJson(jsonString, UserProfile.class);
        } catch (IOException e) {
            System.out.println("Failed to import userProfile (importButtonHandle())");
            this.currAccount = new UserProfile();
        }

        nameField.setText(this.currAccount.getName());
        studentNumberField.setText(this.currAccount.getStudentID());
        majorField.setText(this.currAccount.getMajor());
    }

    public void exportButtonHandle()
    {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try
        {
            FileWriter writer = new FileWriter("currAccount.json");
            writer.write(gson.toJson(this.currAccount));
            writer.close();
        }
        catch (Exception e)
        {
            System.out.println("Failed to export userProfile (exportButtonHandle())");
        }
    }


}
