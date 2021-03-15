package practicum.controllers;

import javafx.scene.Scene;
import javafx.scene.control.*;
import practicum.MainApplication;
import practicum.utils.PersistencyConfiguration;
import practicum.views.NavigatorView;

import static java.lang.String.valueOf;

/**
 * Controller that controlles the behaviour of the buttons in the left navigator grid pane.
 *
 * @author m.smithhva.nl
 */
public abstract class NavigatorController<V extends NavigatorView> {

    protected final V view;

    private Scene scene;

    protected final MainApplication mainApplication;

    // Note: the controller is aware off all other controllers as well as the mainApplication.
    public NavigatorController(MainApplication mainApplication, V view) {
        this.mainApplication = mainApplication;
        this.view = view;
        scene = new Scene(view.getRoot());
    }

    protected void initialize() {
        view.getAboutButton().setOnAction(actionEvent -> {
            AboutController controller = mainApplication.getControllerByClass(AboutController.class);
            controller.view.getAboutButton().setSelected(true);
            mainApplication.switchController(controller);
        });

        view.getCountriesButton().setOnAction(actionEvent -> {
            CountryController controller = mainApplication.getControllerByClass(CountryController.class);
            controller.view.getCountriesButton().setSelected(true);
            mainApplication.switchController(controller);
        });

        view.getCitiesButton().setOnAction(actionEvent -> {
            CitiesController controller = mainApplication.getControllerByClass(CitiesController.class);
            controller.view.getCitiesButton().setSelected(true);
            mainApplication.switchController(controller);
        });

        ToggleButton copyDatabaseButton = view.getCopyDatabaseButton();
        if (PersistencyConfiguration.isPostgresDatabase()) {
            copyDatabaseButton.setOnAction(actionEvent -> {
                CopyDatabaseController controller = mainApplication.getControllerByClass(CopyDatabaseController.class);
                controller.view.getCopyDatabaseButton().setSelected(true);
                mainApplication.switchController(controller);
            });
        } else {
          //  copyDatabaseButton.setVisible(false);
            copyDatabaseButton.setDisable(true);
        }

        view.getExitButton().setOnAction(actionEvent -> {
            System.exit(0);
        });
    }

    protected abstract void setMenuButtonSelected();



    public Scene getScene() {
        return scene;
    }
}
