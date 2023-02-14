package gpaCalculator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class gpaCalculator {

    @FXML
    ObservableList<String> gradeOptionsList = FXCollections.observableArrayList("A+","A",
            "B+","B","C+","C","D+","D","E","F","..");
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
    private void initialize(){
        choicebox1.setValue("..");
        choicebox1.setItems(gradeOptionsList);
        choicebox2.setValue("..");
        choicebox2.setItems(gradeOptionsList);
        choicebox3.setValue("..");
        choicebox3.setItems(gradeOptionsList);
        choicebox4.setValue("..");
        choicebox4.setItems(gradeOptionsList);
        choicebox5.setValue("..");
        choicebox5.setItems(gradeOptionsList);

    }
    @FXML
    public void CalculateGPA(ActionEvent e){

        System.out.println("please");
    }


}
