package practicum;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import practicum.controllers.Controller;
import practicum.controllers.MasterController;

public class MainApplication extends Application {

    public static final String TITLE = "Practicum week 2";

    public static final double WIDTH = 580;

    public static final double HEIGHT = 580;

    private Controller controller;

    @Override
    public void start(Stage stage) {
        Controller controller = new MasterController();

        stage.setTitle(TITLE);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.setScene(new Scene(controller.getView().getRoot()));

        stage.show();
    }

    public void switchController(Controller controller) {
        // TODO Implement
    }
}
