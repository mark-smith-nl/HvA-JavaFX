package practicum;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import practicum.controllers.AboutController;
import practicum.controllers.CitiesController;
import practicum.controllers.CountryController;
import practicum.controllers.NavigatorController;

import java.util.*;

public class MainApplication extends Application {

    public static final String TITLE = "Practicum week 2";

    public static final double WIDTH = 800;

    public static final double HEIGHT = 580;

    private Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;

        Set<NavigatorController<?>> controllers = new HashSet<>();

        controllers.addAll(Arrays.asList(
                new AboutController(this, controllers),
                new CountryController(this, controllers),
                new CitiesController(this, controllers)));

        stage.setTitle(TITLE);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

        switchController(controllers.iterator().next());

        stage.show();
    }

    public void switchController(NavigatorController<?> controller) {
        stage.setScene(controller.getScene());
    }
}
