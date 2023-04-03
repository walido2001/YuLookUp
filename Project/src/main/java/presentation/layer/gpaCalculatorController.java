package presentation.layer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.*;

import java.net.URL;
import java.util.ResourceBundle;

public class gpaCalculatorController implements Initializable {

    //representation of a completed course
    private class CompletedCourse{
        String name;
        String grade;
        int creditWeight;
        int gradeValue;
        public CompletedCourse(String Name,String Grade,int Weight, int gradeValue){
            this.name = Name;
            this.grade = Grade;
            this.creditWeight  = Weight;
            this.gradeValue = gradeValue;
        }
    }
    ArrayList<CompletedCourse> completed = new ArrayList<CompletedCourse>();
    double gpa=0;
    @FXML
    private TextField removedCourse;
    @FXML
    ObservableList<String> gradeOptionsList = FXCollections.observableArrayList("A+","A",
            "B+","B","C+","C","D+","D","E","F");
    @FXML
    private TextField textFieldGPA;
    @FXML
    private TextField textFieldCredits;
    @FXML
    private ChoiceBox<String> choicebox1;
    @FXML
    private ChoiceBox<String> choicebox2;
    @FXML
    private ChoiceBox<String> choicebox3;
    @FXML
    private ChoiceBox<String> choicebox4;
    @FXML
    private ChoiceBox<String> choicebox5;
    @FXML
    private TextField textFieldGPV1;
    @FXML
    private TextField textFieldGPV2;
    @FXML
    private TextField textFieldGPV3;
    @FXML
    private TextField textFieldGPV4;
    @FXML
    private TextField textFieldGPV5;
    @FXML
    private TextField textFieldCW1;
    @FXML
    private TextField textFieldCW2;
    @FXML
    private TextField textFieldCW3;
    @FXML
    private TextField textFieldCW4;
    @FXML
    private TextField textFieldCW5;
    @FXML
    private TextField textFieldCourseName1;
    @FXML
    private TextField textFieldCourseName2;
    @FXML
    private TextField textFieldCourseName3;
    @FXML
    private TextField textFieldCourseName4;
    @FXML
    private TextField textFieldCourseName5;
    @FXML
    private TextArea textAreaCourses;
    private ArrayList<String> coursesAdded = new ArrayList<>();
    private Map<ChoiceBox<String>,TextField> gradeToNumber= new HashMap<>();
    private ArrayList<TextField> creditWeightFields = new ArrayList<>();
    private ArrayList<TextField> gradePointFields =new ArrayList<>();
    private ArrayList<TextField> courseNames =new ArrayList<>();
    private ArrayList<ChoiceBox> grades = new ArrayList<>();
    private ArrayList<TextField> gradePoints =new ArrayList<>();

    //initializes the input fields
    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Initializing choice box values associated with grades
        choicebox1.setValue("Grade");
        choicebox1.setItems(gradeOptionsList);
        choicebox1.setOnAction(this::GradeToGradePoint);
        choicebox2.setValue("Grade");
        choicebox2.setItems(gradeOptionsList);
        choicebox2.setOnAction(this::GradeToGradePoint);
        choicebox3.setValue("Grade");
        choicebox3.setItems(gradeOptionsList);
        choicebox3.setOnAction(this::GradeToGradePoint);
        choicebox4.setValue("Grade");
        choicebox4.setItems(gradeOptionsList);
        choicebox4.setOnAction(this::GradeToGradePoint);
        choicebox5.setValue("Grade");
        choicebox5.setItems(gradeOptionsList);
        choicebox5.setOnAction(this::GradeToGradePoint);

        //mapping each choice box to its associated Grade Point Value text field
        gradeToNumber.put(choicebox1,textFieldGPV1);
        gradeToNumber.put(choicebox2,textFieldGPV2);
        gradeToNumber.put(choicebox3,textFieldGPV3);
        gradeToNumber.put(choicebox4,textFieldGPV4);
        gradeToNumber.put(choicebox5,textFieldGPV5);
        //adding all credit weight fields
        creditWeightFields.add(textFieldCW1);
        creditWeightFields.add(textFieldCW2);
        creditWeightFields.add(textFieldCW3);
        creditWeightFields.add(textFieldCW4);
        creditWeightFields.add(textFieldCW5);
        gradePointFields.add(textFieldGPV1);
        gradePointFields.add(textFieldGPV2);
        gradePointFields.add(textFieldGPV3);
        gradePointFields.add(textFieldGPV4);
        gradePointFields.add(textFieldGPV5);
        courseNames.add(textFieldCourseName1);
        courseNames.add(textFieldCourseName2);
        courseNames.add(textFieldCourseName3);
        courseNames.add(textFieldCourseName4);
        courseNames.add(textFieldCourseName5);
        gradePoints.add(gradeToNumber.get(choicebox1));
        gradePoints.add(gradeToNumber.get(choicebox2));
        gradePoints.add(gradeToNumber.get(choicebox3));
        gradePoints.add(gradeToNumber.get(choicebox4));
        gradePoints.add(gradeToNumber.get(choicebox5));
        grades.add(choicebox1);
        grades.add(choicebox2);
        grades.add(choicebox3);
        grades.add(choicebox4);
        grades.add(choicebox5);
    }

    //converts the grade selected by the user to their equivalent grade point value
    @FXML
    public void GradeToGradePoint(ActionEvent event){
        try {
            ChoiceBox<String> choicebox = (ChoiceBox) event.getSource();

            String grade = choicebox.getValue();
            switch (grade) {
                case "A+":
                    gradeToNumber.get(choicebox).setText("9");
                    break;
                case "A":
                    gradeToNumber.get(choicebox).setText("8");
                    break;
                case "B+":
                    gradeToNumber.get(choicebox).setText("7");
                    break;
                case "B":
                    gradeToNumber.get(choicebox).setText("6");
                    break;
                case "C+":
                    gradeToNumber.get(choicebox).setText("5");
                    break;
                case "C":
                    gradeToNumber.get(choicebox).setText("4");
                    break;
                case "D+":
                    gradeToNumber.get(choicebox).setText("3");
                    break;
                case "D":
                    gradeToNumber.get(choicebox).setText("2");
                    break;
                case "E":
                    gradeToNumber.get(choicebox).setText("1");
                    break;
                case "F":
                    gradeToNumber.get(choicebox).setText("0");
                    break;
                default:

            }

        }catch (Exception e){

        }
    }
    @FXML
    private void CalculateGPA(ActionEvent event){
        int GradePoints=0;
        int TotalCredits=0;
        fillCoursesAdded();
        for (CompletedCourse completedCourse : completed) {
            GradePoints = GradePoints + completedCourse.gradeValue * completedCourse.creditWeight;
            TotalCredits = TotalCredits + completedCourse.creditWeight;
        }
        gpa = (double)GradePoints/(double)TotalCredits;
        gpa = Math.round(gpa*100.0)/100.0;
        textFieldGPA.setText(""+gpa);
        textFieldCredits.setText(""+TotalCredits);
    }

    @FXML
    private void AddCourses(ActionEvent event){
        toCompleted();
        CalculateGPA(event);
        clearFields();
        fillCoursesAdded();
    }

    //removes a course with the same course number as provided by the user
    @FXML
    private void removeCourse(ActionEvent event){

        if (Integer.parseInt(removedCourse.getText()) <= completed.size()) {
            completed.remove(Integer.parseInt(removedCourse.getText()) - 1);
        }
        CalculateGPA(event);
        fillCoursesAdded();
    }

    //adds courses given by the user to the completed list if they have not been added already
    @FXML
    private void toCompleted(){

    try {
        for (int i = 0; i < gradePointFields.size(); i++) {
            boolean found = false;
            for (CompletedCourse completedCourse : completed) {
                if (completedCourse.name.equals(courseNames.get(i).getText())) {
                    found = true;
                    courseNames.get(i).setText(courseNames.get(i).getText() + "already added");
                    break;
                }
            }
            if (found == true) {
                continue;
            } else {
                completed.add(new CompletedCourse(courseNames.get(i).getText(), grades.get(i).getValue().toString(),
                        Integer.parseInt(creditWeightFields.get(i).getText()), Integer.parseInt(gradePointFields.get(i).getText())));

            }
        }
    }catch(Exception e){

    }
    }
    //fills the courses added text area with all the courses taken and their associated values
    @FXML
    private void fillCoursesAdded(){
        textAreaCourses.setText(" ");
        for (int i=0; i<completed.size();i++){
            textAreaCourses.appendText(i+1 + ". " + completed.get(i).name + " Weight: " + completed.get(i).creditWeight
                    + " Grade: " + completed.get(i).grade + " Point Value: " + completed.get(i).gradeValue + ".0\n");

        }
    }

    //clears only the user input fields
    @FXML
    private void clearFields(){
        for ( int i=0; i <gradePointFields.size(); i++){

            courseNames.get(i).setText(null);
            creditWeightFields.get(i).setText(null);
            gradePointFields.get(i).setText(null);
            grades.get(i).setValue("Grade");

        }
    }
    //clears all the fields associated with the Gpa calculator
    @FXML
    private void Clear(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getClassLoader().getResource("gpaCalculator.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    private Parent root;
    private Stage stage;
    private Scene scene;
    public void getSearchResultsScene(ActionEvent event) throws IOException {

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



}




