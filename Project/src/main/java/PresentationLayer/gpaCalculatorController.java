package PresentationLayer;

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
    int GradePoints=0;
    int TotalCredits=0;
    double gpa=0;
    @FXML
    ObservableList<String> gradeOptionsList = FXCollections.observableArrayList("A+","A",
            "B+","B","C+","C","D+","D","E","F","..");
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
    private static int courses;
    private ArrayList<String> coursesAdded = new ArrayList<String>();
    private Map<ChoiceBox<String>,TextField> gradeToNumber= new HashMap<ChoiceBox<String>, TextField>();
    private ArrayList<TextField> creditWeightFields = new ArrayList<TextField>();
    private ArrayList<TextField> gradePointFields =new ArrayList<TextField>();
    private ArrayList<TextField> courseNames =new ArrayList<TextField>();
    private ArrayList<ChoiceBox> grades =new ArrayList<ChoiceBox>();
    private ArrayList<TextField> gradePoints =new ArrayList<TextField>();

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
            System.out.println(e);
        }
    }
    @FXML
    private void CalculateGPA(ActionEvent event){
        try{

            for ( int i=0; i <gradePointFields.size(); i++){
                    if (coursesAdded.indexOf(courseNames.get(i).getText()) != -1){
                        continue;
                    }
                    else {
                        coursesAdded.add(courseNames.get(i).getText());
                        GradePoints = GradePoints +
                                Integer.parseInt(gradePointFields.get(i).getText()) *
                                        Integer.parseInt(creditWeightFields.get(i).getText());
                        TotalCredits = TotalCredits + Integer.parseInt(creditWeightFields.get(i).getText());
                        courses++;
                        textAreaCourses.appendText(courses + ". " + courseNames.get(i).getText() + " Weight: " + creditWeightFields.get(i).getText()
                                + " Grade: " + grades.get(i).getValue() + " Point Value: " + gradePoints.get(i).getText() + ".0\n");
                    }
                    //  textAreaCourses.getText().contains(null); use this to check if a course name has already been added

            }


        }catch (Exception e) {

        }
        gpa = (double)GradePoints/(double)TotalCredits;
        gpa = Math.round(gpa*100.0)/100.0;
        textFieldGPA.setText(""+gpa);
        textFieldCredits.setText(""+TotalCredits);
    }
    @FXML
    private void AddCourses(ActionEvent event){
        try{
            for ( int i=0; i <gradePointFields.size(); i++){
                courseNames.get(i).setText(null);
                creditWeightFields.get(i).setText(null);
                gradePointFields.get(i).setText(null);
                grades.get(i).setValue("Grade");

            }
        }catch(Exception e) {

        }
    }
    @FXML
    private void Clear(ActionEvent event) throws IOException {
        courses = 0;
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



//public class Gpa_Calculator extends Application {
//
//    public static void main(String[] args) {
//
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage stage) {
//        try{
//            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gpaCalculator.fxml"));
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
