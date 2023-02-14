package gpaCalculator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.util.*;

import java.net.URL;
import java.util.ResourceBundle;

public class gpaCalculator implements Initializable {

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
    private Map<ChoiceBox<String>,TextField> gradeToNumber= new HashMap<ChoiceBox<String>, TextField>();
    private ArrayList<TextField> creditWeightFields = new ArrayList<TextField>();
    private ArrayList<TextField> gradePointFields =new ArrayList<TextField>();





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
                    System.out.println("Use drop down menu to input a grade");
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }
    @FXML
    private void CalculateGPA(ActionEvent event){
        int GradePoints=0;
        int TotalCredits=0;
        double gpa=0;
        try{

            for ( int i=0; i <gradePointFields.size(); i++){
                    GradePoints = GradePoints +
                            Integer.parseInt(gradePointFields.get(i).getText()) *
                                    Integer.parseInt(creditWeightFields.get(i).getText());
                    TotalCredits = TotalCredits + Integer.parseInt(creditWeightFields.get(i).getText());

            }


        }catch (Exception e) {

        }
        gpa = (double)GradePoints/(double)TotalCredits;
        gpa = Math.round(gpa*100.0)/100.0;
        textFieldGPA.setText(""+gpa);
        textFieldCredits.setText(""+TotalCredits);

    }



}
