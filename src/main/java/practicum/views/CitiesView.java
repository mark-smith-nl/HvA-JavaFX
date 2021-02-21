package practicum.views;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.time.LocalDate;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class CitiesView extends NavigatorView {

    private static final double WIDTH = 400;

    private final GridPane gridPane;

    private final Label labelCities;
    private final TextField citiesField;


    public CitiesView() {
        super();
        gridPane = new GridPane();

        labelCities = new Label("Developer");
        citiesField = new TextField();
        citiesField.setBorder(new Border(new BorderStroke(Color.RED,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        citiesField.setTooltip(new Tooltip("Immutable"));

        initialize();
    }

    public void initialize() {
        super.initialize();

        gridPane.add(navigatorGridPane, 0, 0);

        int row = 0;
        GridPane bodyGridPane = new GridPane();
        gridPane.add(bodyGridPane, 1, 0);
        bodyGridPane.setPadding(new Insets(10, 10, 10, 10));
        bodyGridPane.setHgap(10);
        bodyGridPane.setVgap(10);

        bodyGridPane.getColumnConstraints().add(new ColumnConstraints(80, 80, 80));

        bodyGridPane.add(labelCities, 0, row);
        bodyGridPane.add(citiesField, 1, row, 2, 1);
    }

    @Override
    public Parent getRoot() {
        return gridPane;
    }

    public TextField getCitiesField() {
        return citiesField;
    }
}
