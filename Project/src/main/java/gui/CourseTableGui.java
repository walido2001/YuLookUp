package gui;

import courseStructures.Course;
import courseStructures.Major;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;


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
        primaryStage.setScene(new Scene(border, 1000, 700));
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
            columns.setMinWidth(600);
        }
    }

    public void courseDescription(Course course){
        border.setRight(null);
        VBox description = new VBox();
        description.setPadding(new Insets(5, 15, 5, 15));
        description.setSpacing(5);

        Text title = new Text(course.getName());
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        Text credit = new Text("Credits: "+ course.getCredit());
        credit.setFont(Font.font(14));
        Text summary = new Text(course.getDescription());
        summary.setFont(Font.font(12));
        Text prereqs = new Text("Prerequisites: "+course.getPrerequisites().toString());
        prereqs.setFont(Font.font(12));

        description.getChildren().addAll(title, credit, summary, prereqs);
        border.setRight(description);
    }

    public VBox yearCol(int year, ArrayList<Button> buttons){
        VBox stack = new VBox();
        stack.setPadding(new Insets(5, 15, 5, 15));
        stack.setSpacing(5);

        for(Course course : major.getMajor().get(year)){
            Button btn = createButton(course.getCode(), 100, 30);
            buttons.add(btn);
            btn.setOnAction(event -> {
                courseDescription(course);
            });
            stack.getChildren().add(btn);
        }
        return stack;
    }

    public Button createButton(String text, double width, double height){
        Button btn = new Button(text);
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

        Button spaceMajButton = new Button("Space");
        spaceMajButton.setPrefSize(100, 20);
        spaceMajButton.setOnAction(event -> {
            major = new Major("Space Engineering");
            setCourseColumnsView();
        });

        hbox.getChildren().addAll(civilMajButton, compMajButton, elecMajButton, mechMajButton, softMajButton, spaceMajButton);

        return hbox;
    }

}
