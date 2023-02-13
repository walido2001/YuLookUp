package gui;

import courseStructures.Course;
import courseStructures.Major;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;


// following the tutorial found at https://docs.oracle.com/javafx/2/layout/LayoutSample.java.html


public class CourseTableGui extends Application{
    Major major;
    BorderPane border;

    HashMap<String, Button> buttonsList = new HashMap<>();
//    HashMap<String, Button> buttonsYear2 = new HashMap<>();
//    HashMap<String, Button> buttonsYear3 = new HashMap<>();
//    HashMap<String, Button> buttonsYear4 = new HashMap<>();

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
        primaryStage.setScene(new Scene(border, 1150, 820));
        primaryStage.show();
    }

    public void setCourseColumnsView(){
        border.setCenter(null);
        border.setRight(null);
        if(major!=null) {
            HBox columns = new HBox();
            VBox colYear1 = yearCol(1, buttonsList);
            VBox colYear2 = yearCol(2, buttonsList);
            VBox colYear3 = yearCol(3, buttonsList);
            VBox colYear4 = yearCol(4, buttonsList);
            columns.getChildren().addAll(colYear1, colYear2, colYear3, colYear4);
            border.setCenter(columns);
        }
    }

    public void courseDescription(Course course){
        border.setRight(null);
        HBox frame = new HBox();
        VBox description = new VBox();
        description.setPadding(new Insets(5, 15, 5, 15));
        description.setSpacing(5);

        Text title = new Text(course.getName());
        title.setFont(Font.font("Arial", FontWeight.LIGHT, 28));
        title.setWrappingWidth(400);

        Text credit = new Text("Credits: "+ getCredit(course));
        credit.setFont(Font.font(16));

        Text summary = new Text(course.getDescription());
        summary.setFont(Font.font("Arial", FontWeight.LIGHT,14)); //"Helvetica"
        summary.setWrappingWidth(400);

        Text prereqs = new Text("Prerequisites: "+course.getPrerequisites().toString());
        prereqs.setFont(Font.font("Arial", FontWeight.LIGHT,14));
        prereqs.setWrappingWidth(400);

        description.getChildren().addAll(title, credit, summary, prereqs);
        frame.getChildren().addAll(description);
        frame.setHgrow(description, Priority.ALWAYS);
        description.setStyle("-fx-background-color: #FFFFFF");
        border.setRight(description);
    }

    public VBox yearCol(int year, HashMap<String, Button> buttons){
        VBox stack = new VBox();
        stack.setPadding(new Insets(20, 15, 5, 15));
        stack.setSpacing(5);

        for(Course course : major.getMajor().get(year)){
            Button btn = createButton(getShortCode(course), 150, 35);
            btn.setStyle("-fx-background-color: #FFFFFF");
            buttons.put(getShortCode(course), btn);
            btn.setOnAction(event -> {
                courseDescription(course);
                clearPrerequisites();
                showPrerequisites(btn, course);
            });
            stack.getChildren().add(btn);
        }
        return stack;
    }

    public void showPrerequisites(Button btn, Course course){
        if(course.getCoursePrerequisites()==null) return;
        for(int i = 0; i<course.getCoursePrerequisites().size(); i++) {
            String code = getShortCode(course.getCoursePrerequisites().get(i));
            if(buttonsList.containsKey(code)){
                buttonsList.get(code).setStyle("-fx-background-color: #e31837");
                showPrerequisites(buttonsList.get(code), course.getCoursePrerequisites().get(i));
            }
        }
    }

    public void clearPrerequisites(){
        for (Map.Entry<String,Button> mapElement : buttonsList.entrySet())
            mapElement.getValue().setStyle("-fx-background-color: #FFFFFF");
    }

    public Button createButton(String text, double width, double height){
        Button btn = new Button(text);
        btn.setStyle("-fx-background-color: #FFFFFF");
        btn.setPrefSize(width, height);
        return btn;
    }

    public void setCourses(){
    }

    public HBox majorsBar() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 15, 15, 15));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #e31837;");

        Text text = new Text("Engineering Major:");
        text.setFont(Font.font("Verdana", FontWeight.NORMAL, 14));
        text.setFill(Color.WHITE);

        Button civilMajButton = createButton("Civil", 100, 20);
        civilMajButton.setOnAction(event -> {
            major = new Major("Civil Engineering");
            setCourseColumnsView();
        });

        Button compMajButton = createButton("Computer", 100, 20);
        compMajButton.setOnAction(event -> {
            major = new Major("Computer Engineering");
            setCourseColumnsView();
        });

        Button elecMajButton = createButton("Electrical", 100, 20);
        elecMajButton.setOnAction(event -> {
            major = new Major("Electrical Engineering");
            setCourseColumnsView();
        });

        Button mechMajButton = createButton("Mechanical", 100, 20);
        mechMajButton.setOnAction(event -> {
            major = new Major("Mechanical Engineering");
            setCourseColumnsView();
        });

//        Button softMajButton = createButton("Software", 100, 20);
//        softMajButton.setOnAction(event -> {
//            major = new Major("Software Engineering");
//            setCourseColumnsView();
//        });
//
//        Button spaceMajButton = createButton("Space", 100, 20);
//        spaceMajButton.setOnAction(event -> {
//            major = new Major("Space Engineering");
//            setCourseColumnsView();
//        });

        hbox.getChildren().addAll(text, civilMajButton, compMajButton, elecMajButton, mechMajButton/*, softMajButton, spaceMajButton*/);
        hbox.setAlignment(Pos.BASELINE_CENTER);
        return hbox;
    }

    private String getShortCode(Course course) {
        return course.getCode().substring(3, course.getCode().length()-5);
    }

    private int getCourseLevel(Course course){
        String code = getShortCode(course);
        return Character.getNumericValue(code.charAt(code.length()-4));
    }

    private double getCredit(Course course){
        String c = course.getCode();
        return Character.getNumericValue(c.charAt(c.length()-4));
    }
}
