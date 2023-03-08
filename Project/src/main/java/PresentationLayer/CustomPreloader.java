package PresentationLayer;
import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CustomPreloader extends Preloader{
    Stage stage;
    Label label;

    public void start(Stage stage) throws Exception {
        this.stage = stage;
        BorderPane p = new BorderPane();
        label = new Label("Setting up Database... Should take no more than a minute");
        p.setCenter(label);
        stage.setScene(new Scene(p, 350, 50));
        stage.show();
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification evt) {
        if (evt.getType() == StateChangeNotification.Type.BEFORE_START) {
            stage.hide();
        }
    }

}
