package PresentationLayer;

import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        System.setProperty("javafx.preloader", CustomPreloader.class.getCanonicalName());
        Application.launch(mainController.class, args);
    }
}
