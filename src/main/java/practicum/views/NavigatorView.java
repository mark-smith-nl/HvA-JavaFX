package practicum.views;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;


/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public abstract class NavigatorView {

    private static final double WIDTH = 150;

    protected final GridPane navigatorGridPane;

    private final ToggleGroup buttonGroup;

    private final ToggleButton aboutButton, countriesButton, citiesButton, copyDatabaseButton, exitButton;

    public NavigatorView() {
        this.navigatorGridPane = new GridPane();

        buttonGroup = new ToggleGroup();

        aboutButton = new ToggleButton("About");
        aboutButton.setId("about");

        countriesButton = new ToggleButton("Countries");
        countriesButton.setId("countries");

        citiesButton = new ToggleButton("Cities");
        citiesButton.setId("cities");

        copyDatabaseButton = new ToggleButton("Copy PGDB to H2DB");
        copyDatabaseButton.setId("copyDB");

        exitButton = new ToggleButton("Exit");
        exitButton.setId("exit");
    }

    protected void initialize() {
        navigatorGridPane.setPadding(new Insets(10, 10, 10, 10));
        navigatorGridPane.setHgap(10);
        navigatorGridPane.setVgap(10);

        int row = 0;

        navigatorGridPane.add(aboutButton, 1, row++);
        navigatorGridPane.add(countriesButton, 1, row++);
        navigatorGridPane.add(citiesButton, 1, row++);
        navigatorGridPane.add(copyDatabaseButton, 1, row++);
        navigatorGridPane.add(exitButton, 1, row);

        navigatorGridPane.getChildren().stream().filter(n -> n instanceof ButtonBase).map(n -> (ToggleButton) n).forEach(b -> {
            b.setMinWidth(WIDTH);
            b.setPrefWidth(WIDTH);
            b.setToggleGroup(buttonGroup);
        });

    }

    public ToggleButton getAboutButton() {
        return aboutButton;
    }

    public ToggleButton getCountriesButton() {
        return countriesButton;
    }

    public ToggleButton getCitiesButton() {
        return citiesButton;
    }

    public ToggleButton getCopyDatabaseButton() {
        return copyDatabaseButton;
    }

    public ToggleButton getExitButton() {
        return exitButton;
    }

    public abstract Parent getRoot();
}
