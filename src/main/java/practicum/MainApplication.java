package practicum;

import javafx.application.Application;
import javafx.stage.Stage;
import practicum.controllers.*;
import practicum.dao.AbstractPersistentDao;
import practicum.service.CopyDatabaseService;

import java.util.*;

public class MainApplication extends Application {

    public static final String TITLE = "Alleen samen krijgen we OOP2 eronder";

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
                new CitiesController(this, controllers),
                new CopyDatabaseController(this, controllers))
                );

        stage.setTitle(TITLE);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

        switchController(controllers.stream()
                .filter(c -> c instanceof AboutController)
                .findAny()
                .orElseThrow(IllegalStateException::new));

        stage.show();
    }

    public void switchController(NavigatorController<?> controller) {
        stage.setScene(controller.getScene());
    }
}
