package practicum.views;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.HashSet;
import java.util.Set;


/**
 * Abstract parent view containing menu buttons.
 *
 * @author m.smithhva.nl
 */
public abstract class NavigatorView {

    private static final double WIDTH = 150;

    private static final Border RED_BORDER = new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));

    private static final Tooltip READ_ONLY = new Tooltip("Immutable - Read only");

    protected final GridPane gridPane;

    protected final GridPane navigatorGridPane;

    private final ToggleGroup buttonGroup;

    private final ToggleButton aboutButton, countriesButton, citiesButton, copyDatabaseButton, copyToFilesButton, exitButton;

    public NavigatorView() {

        gridPane = new GridPane();

        navigatorGridPane = new GridPane();

        buttonGroup = new ToggleGroup();

        aboutButton = new ToggleButton("About");
        aboutButton.setId("about");

        countriesButton = new ToggleButton("Countries");
        countriesButton.setId("countries");

        citiesButton = new ToggleButton("Cities");
        citiesButton.setId("cities");

        copyDatabaseButton = new ToggleButton("Copy PGDB to H2DB");
        copyDatabaseButton.setId("copyDB");

        copyToFilesButton = new ToggleButton("Copy to files");
        copyToFilesButton.setId("copyToFiles");
        copyToFilesButton.setTooltip(new Tooltip("Flush all entities to file - NOT IMPLEMENTED"));

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
        navigatorGridPane.add(copyToFilesButton, 1, row++);
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

    public ToggleButton getCopyToFilesButton() {
        return copyToFilesButton;
    }

    public ToggleButton getExitButton() {
        return exitButton;
    }

    public abstract Parent getRoot();

    public void setDisable(boolean disable, Control... controls) {
        for (Control control : controls) {
            control.setDisable(disable);
            if (disable) {
                control.setBorder(RED_BORDER);
                control.setTooltip(READ_ONLY);
                control.setStyle(("-fx-background-color: #f0f0f0;"));
            } else {
                control.setBorder(null);
                control.setTooltip(null);
                control.setStyle(("-fx-background-color: #ffffff;"));
            }
        }
    }

    public void setVisible(boolean visible, Control... controls) {
        for (Control control : controls) control.setVisible(visible);
    }

}
