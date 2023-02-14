package gpaCalculator;

import GUIControllers.mainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;

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
    private Stage stage;
    private Scene scene;
    private Parent root;
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


    public void getSearchResultsScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("searchReturnGUI.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
