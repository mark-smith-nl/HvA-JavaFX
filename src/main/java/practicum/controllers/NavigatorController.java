package practicum.controllers;

import javafx.event.EventTarget;
import javafx.scene.Scene;
import javafx.scene.control.*;
import practicum.MainApplication;
import practicum.dao.AbstractPersistentDao;
import practicum.utils.Configuration;
import practicum.views.NavigatorView;

import java.util.Set;

import static java.lang.String.valueOf;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public abstract class NavigatorController<T extends NavigatorView> {

    protected final T view;

    private Scene scene;

    protected final MainApplication mainApplication;

    protected Set<NavigatorController<?>> controllers;

    // Note: the controller is aware off all other controllers as well as the mainApplication.
    public NavigatorController(MainApplication mainApplication, Set<NavigatorController<?>> controllers, T view) {
        this.mainApplication = mainApplication;
        this.controllers = controllers;
        this.view = view;
        scene = new Scene(view.getRoot());
    }

    protected void initialize() {
        view.getAboutButton().setOnAction(actionEvent -> {
            AboutController controller = getController(AboutController.class, controllers);
            controller.view.getAboutButton().setSelected(true);
            mainApplication.switchController(controller);
        });

        view.getCountriesButton().setOnAction(actionEvent -> {
            CountryController controller = getController(CountryController.class, controllers);
            controller.view.getCountriesButton().setSelected(true);
            mainApplication.switchController(controller);
        });

        view.getCitiesButton().setOnAction(actionEvent -> {
            CitiesController controller = getController(CitiesController.class, controllers);
            controller.view.getCitiesButton().setSelected(true);
            mainApplication.switchController(controller);
        });

        ToggleButton copyDatabaseButton = view.getCopyDatabaseButton();
        if (Configuration.isPostgresDatabase()) {
            copyDatabaseButton.setOnAction(actionEvent -> {
                CopyDatabaseController controller = getController(CopyDatabaseController.class, controllers);
                controller.view.getCopyDatabaseButton().setSelected(true);
                mainApplication.switchController(controller);
            });
        } else {
            copyDatabaseButton.setVisible(false);
        }

        view.getExitButton().setOnAction(actionEvent -> {
            System.exit(0);
        });
    }

    private static <T extends NavigatorController<?>>  T getController(Class<T> clazz, Set<NavigatorController<?>> controllers) {
        return controllers.stream().filter(c -> clazz.equals(c.getClass())).map(c -> (T) c).findAny().orElseThrow(IllegalStateException::new);
    }

    protected abstract void setMenuButtonSelected();

    // Method searches for an instance of the specified class.
    protected <C extends NavigatorController<?>> C getControllerByClass(Class<C> clazz) {
        return (C) controllers.stream().filter(c -> c.getClass().equals(clazz)).findFirst().orElse(null);
    }

    public Scene getScene() {
        return scene;
    }
}
