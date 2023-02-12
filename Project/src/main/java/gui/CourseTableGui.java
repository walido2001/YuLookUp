package gui;

import courseScraper.mainScraper;
import courseStructures.Course;
import courseStructures.Major;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;


// following the tutorial found at https://docs.oracle.com/javafx/2/layout/LayoutSample.java.html


public class CourseTableGui extends Application{
    Major major;
    BorderPane border;

    ArrayList<Button> buttonsYear1 = new ArrayList<>();
    ArrayList<Button> buttonsYear2 = new ArrayList<>();
    ArrayList<Button> buttonsYear3 = new ArrayList<>();
    ArrayList<Button> buttonsYear4 = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Courses By Major");

        border = new BorderPane();
        HBox hbox = majorsBar();
        border.setTop(hbox);
        setCourseColumnsView();
        primaryStage.setScene(new Scene(border, 500, 400));
        primaryStage.show();
    }

    public void setCourseColumnsView(){
        border.setCenter(null);
        if(major!=null) {
            HBox columns = new HBox();
            VBox colYear1 = yearCol(1, buttonsYear1);
            VBox colYear2 = yearCol(2, buttonsYear2);
            VBox colYear3 = yearCol(3, buttonsYear3);
            VBox colYear4 = yearCol(4, buttonsYear4);
            columns.getChildren().addAll(colYear1, colYear2, colYear3, colYear4);
            columns.setHgrow(colYear1, Priority.ALWAYS);
            columns.setHgrow(colYear2, Priority.ALWAYS);
            columns.setHgrow(colYear3, Priority.ALWAYS);
            columns.setHgrow(colYear4, Priority.ALWAYS);
            border.setCenter(columns);
        }
    }

    public VBox yearCol(int year, ArrayList<Button> buttons){
        VBox stack = new VBox();
        stack.setPadding(new Insets(5, 15, 5, 15));
        stack.setSpacing(5);

        for(Course course : major.getMajor().get(year)){
            Button btn = createButton(course.getCode(), 100, 30);
            buttons.add(btn);
            stack.getChildren().add(btn);
        }
        return stack;
    }

    public Button createButton(String text, double width, double height){
        Button btn = new Button(text);
//        btn.setMinWidth(width);
//        btn.setMinHeight(height);
        btn.setMaxWidth(Double.MAX_VALUE);
        return btn;
    }

    public void setCourses(){
    }

    public HBox majorsBar() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 15, 15, 15));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #e31837;");

        Button civilMajButton = new Button("Civil");
        civilMajButton.setPrefSize(100, 20);
        civilMajButton.setOnAction(event -> {
                major = new Major("Civil Engineering");
                setCourseColumnsView();
                });

        Button compMajButton = new Button("Computer");
        compMajButton.setPrefSize(100, 20);
        compMajButton.setOnAction(event -> {
            major = new Major("Computer Engineering");
            setCourseColumnsView();
        });

        Button elecMajButton = new Button("Electrical");
        elecMajButton.setPrefSize(100, 20);
        elecMajButton.setOnAction(event -> {
            major = new Major("Electrical Engineering");
            setCourseColumnsView();
        });

        Button mechMajButton = new Button("Mechanical");
        mechMajButton.setPrefSize(100, 20);
        mechMajButton.setOnAction(event -> {
            major = new Major("Mechanical Engineering");
            setCourseColumnsView();
        });

        Button softMajButton = new Button("Software");
        softMajButton.setPrefSize(100, 20);
        softMajButton.setOnAction(event -> {
            major = new Major("Software Engineering");
            setCourseColumnsView();
        });

        Button aeroMajButton = new Button("Space");
        aeroMajButton.setPrefSize(100, 20);
        aeroMajButton.setOnAction(event -> {
            major = new Major("Space Engineering");
            setCourseColumnsView();
        });

        hbox.getChildren().addAll(civilMajButton, mechMajButton, elecMajButton, aeroMajButton, softMajButton);

        return hbox;
    }

}
