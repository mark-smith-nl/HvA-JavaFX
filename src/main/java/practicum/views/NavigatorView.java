package practicum.views;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


/**
 * This method <description of functionality>
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

    private final ToggleButton aboutButton, countriesButton, citiesButton, copyDatabaseButton, exitButton;

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

    public void setEditable(boolean editable, TextInputControl... textInputControls) {
        for (TextInputControl textInputControl : textInputControls) {
            textInputControl.setEditable(editable);
            if (editable) {
                textInputControl.setBorder(null);
                textInputControl.setTooltip(null);
                textInputControl.setStyle(("-fx-background-color: #ffffff;"));
            } else {
                textInputControl.setBorder(RED_BORDER);
                textInputControl.setTooltip(READ_ONLY);
                textInputControl.setStyle(("-fx-background-color: #f0f0f0;"));
            }
        }
    }

}
