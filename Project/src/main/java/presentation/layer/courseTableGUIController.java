package presentation.layer;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import business.logic.layer.Course;
import business.logic.layer.Major;

public class courseTableGUIController {
    Major major;
    BorderPane border;
    Parent root;
    Stage stage;
    Scene scene;
    HashMap<String, Button> buttonsList = new HashMap<>();

    // The method initializes the window of this UI
    public Scene getMajorsListScene() {
        border = new BorderPane();
        HBox hbox = majorsBar();
        border.setTop(hbox);
        setCourseColumnsView();
        return new Scene (border, 1150, 730);
    }

    // The method setts up the four columns of courses corresponding to the course level (1 col -> first level courses)
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

    // The method formats the information about the course and displays it on the right side of the window
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

        description.getChildren().addAll(title, credit, summary);
        frame.getChildren().addAll(description);
        frame.setHgrow(description, Priority.ALWAYS);
        description.setStyle("-fx-background-color: #FFFFFF");
        border.setRight(description);
    }

    // The method fills a particular year column with the courses of that level
    // It adds the courses in the form of buttons so that once it's selected, the user will see its prerequisites
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
                btn.setStyle("-fx-background-color: #e31837");
                showPrerequisites(btn, course);
            });
            stack.getChildren().add(btn);
        }
        return stack;
    }

    // The method utilizes recursion to find and highlight all the prerequisites of a given course
    public void showPrerequisites(Button btn, Course course){
        if(course.getCoursePrerequisites()==null) return;
        for(int i = 0; i<course.getCoursePrerequisites().size(); i++) {
            String code = getShortCode(course.getCoursePrerequisites().get(i));
            if(buttonsList.containsKey(code)){
                buttonsList.get(code).setStyle("-fx-background-color: #dedede");
                showPrerequisites(buttonsList.get(code), course.getCoursePrerequisites().get(i));
            }
        }
    }

    // This method de-selects all highlighted prerequisites
    public void clearPrerequisites(){
        for (Map.Entry<String,Button> mapElement : buttonsList.entrySet())
            mapElement.getValue().setStyle("-fx-background-color: #FFFFFF");
    }

    // This method constructs a button given its parameters
    public Button createButton(String text, double width, double height){
        Button btn = new Button(text);
        btn.setStyle("-fx-border-color: #FFFFFF; -fx-border-width: 1px;");
        btn.setStyle("-fx-background-color: #FFFFFF");
        btn.setPrefSize(width, height);
        return btn;
    }

    // This method initializes the header bar consisting of different majors
    // Upon a click of one of the majors, the method initializes the view with all of the courses in that major
    public HBox majorsBar() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 15, 15, 15));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #e31837; -fx-background-radius: 0 0 10 10");

        Text text = new Text("Engineering Major:");
        text.setFont(Font.font("Verdana", FontWeight.NORMAL, 14));
        text.setFill(Color.WHITE);

        Button Back = createButton("Back", 60, 20);
        Back.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("searchReturnGUI.fxml"));
            try {
                root = loader.load();
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.centerOnScreen();

                courseSearchController var = loader.getController();
                var.setUserSearch("");

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

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

        Button softMajButton = createButton("Software", 100, 20);
        softMajButton.setOnAction(event -> {
            major = new Major("Software Engineering");
            setCourseColumnsView();
        });

        Button spaceMajButton = createButton("Space", 100, 20);
        spaceMajButton.setOnAction(event -> {
            major = new Major("Space Engineering");
            setCourseColumnsView();
        });

        hbox.getChildren().addAll(Back, text, civilMajButton, compMajButton, elecMajButton, mechMajButton, softMajButton, spaceMajButton);
        hbox.setAlignment(Pos.BASELINE_CENTER);
        return hbox;
    }

    // The method retrieves the short version of a course code of a given course
    private String getShortCode(Course course) {
        return course.getCode().substring(3, course.getCode().length()-5);
    }

    // The method retrieves the credit of a given course
    private double getCredit(Course course){
        String c = course.getCode();
        return Character.getNumericValue(c.charAt(c.length()-4));
    }
}
