package practicum;

import javafx.application.Application;
import javafx.stage.Stage;
import practicum.controllers.*;
import practicum.dao.AbstractDao;
import practicum.dao.AbstractPersistentDao;
import practicum.dao.CityPersistentDao;
import practicum.dao.CountryPersistentDao;
import practicum.service.AbstractService;
import practicum.service.CityService;
import practicum.service.CopyDatabaseService;
import practicum.service.CountryService;

import java.util.*;

public class MainApplication extends Application {

    public static final String TITLE = "Alleen samen krijgen we OOP2 eronder";

    public static final double WIDTH = 800;

    public static final double HEIGHT = 700;

    private Stage stage;

    Set<AbstractService<?>> services = new HashSet<>();

    Set<NavigatorController<?>> controllers = new HashSet<>();

    @Override
    public void start(Stage stage) {
        this.stage = stage;

        services.addAll(Arrays.asList(
                new CountryService(),
                new CityService()
                ));

        controllers.addAll(Arrays.asList(
                new AboutController(this),
                new CountryController(this),
                new CitiesController(this),
                new CopyDatabaseController(this))
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

    // Method searches for an instance of the specified class.
    public <S extends AbstractService<?>> S getServiceByClass(Class<S> clazz) {
        return (S) services.stream().filter(c -> c.getClass().equals(clazz)).findFirst().orElse(null);
    }

    // Method searches for an instance of the specified class.
    public <C extends NavigatorController<?>> C getControllerByClass(Class<C> clazz) {
        return (C) controllers.stream().filter(c -> c.getClass().equals(clazz)).findFirst().orElse(null);
    }
}
