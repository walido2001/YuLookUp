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


// following the tutorial found at https://docs.oracle.com/javafx/2/layout/LayoutSample.java.html


public class CourseTableGui extends Application{
    ArrayList<Course> courses = mainScraper.getCourseList();
    ArrayList<Button> buttonsYear1 = new ArrayList<>();
    ArrayList<Button> buttonsYear2 = new ArrayList<>();
    ArrayList<Button> buttonsYear3 = new ArrayList<>();
    ArrayList<Button> buttonsYear4 = new ArrayList<>();
    ArrayList<Course> year1courses;
    ArrayList<Course> year2courses;
    ArrayList<Course> year3courses;
    ArrayList<Course> year4courses;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Major mechMaj = new Major("Test");
        HashMap<String, Course> courseMap = mechMaj.convertToHashMap(courses);

        primaryStage.setTitle("Courses By Major");

        BorderPane border = new BorderPane();
        HBox columns = new HBox();
        VBox colYear1 = yearCol('1');
        VBox colYear2 = yearCol('2');
        VBox colYear3 = yearCol('3');
        VBox colYear4 = yearCol('4');

        columns.getChildren().addAll(colYear1, colYear2, colYear3, colYear4);

        HBox hbox = majorsBar();
        border.setTop(hbox);
        AnchorPane cols = new AnchorPane();
        cols.getChildren().add(columns);
        border.setCenter(cols);
        primaryStage.setScene(new Scene(border, 500, 400));
        primaryStage.show();
    }

    public VBox yearCol(char year){
        VBox stack = new VBox();
        stack.setPadding(new Insets(15, 15, 15, 15));
        stack.setSpacing(10);

        for(int i=0; i<5; i++){ ///courses.size()
            Course course = courses.get(i);
            String courseCode = course.getCode();
            char level = courseCode.charAt(courseCode.length()-9);
            Button btn = new Button("Button");
            this.buttonsYear1.add(btn);
            stack.getChildren().add(btn);

//            if(level==year) {
//                Button btn = new Button(courseCode);
//                if (level == year && year == '1') {
//                    this.year1courses.add(course);
//                    this.buttonsYear1.add(btn);
//                    stack.getChildren().add(btn);
//                } else if (level == year && year == '2') {
//                    this.year2courses.add(course);
//                    this.buttonsYear2.add(btn);
//                    stack.getChildren().add(btn);
//                } else if (level == year && year == '3') {
//                    this.year3courses.add(course);
//                    this.buttonsYear3.add(btn);
//                    stack.getChildren().add(btn);
//                } else if (level == year && year == '4') {
//                    this.year4courses.add(course);
//                    this.buttonsYear4.add(btn);
//                    stack.getChildren().add(btn);
//                }
//            }
        }

        //stack.getChildren().addAll(/*aaaddddd*/);

        return stack;
    }

    public Button createButton(String text){
        Button btn = new Button();
        btn.setText(text);
        return btn;
    }

    public void setCourses(){
    }

    public HBox majorsBar() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 15, 15, 15));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #e31837;");

        Button testMajButton = new Button("Test");
        testMajButton.setPrefSize(100, 20);

        Button mechMajButton = new Button("Mechanical");
        mechMajButton.setPrefSize(100, 20);

        Button elecMajButton = new Button("Electrical");
        elecMajButton.setPrefSize(100, 20);

        Button aeroMajButton = new Button("Aerospace");
        aeroMajButton.setPrefSize(100, 20);

        Button softMajButton = new Button("Software");
        softMajButton.setPrefSize(100, 20);

        hbox.getChildren().addAll(testMajButton, mechMajButton, elecMajButton, aeroMajButton, softMajButton);

        return hbox;
    }

}
