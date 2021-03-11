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

    private final Label labelId;
    private final TextField idField;

    private final Label labelName;
    private final TextField nameField;

    private final Label labelCountry;
    private final TextField countryField;

    private final Label labelDescription;
    private final TextArea descriptionField;

    public CitiesView() {
        super();

        labelId = new Label("Id");
        idField = new TextField();

        labelName = new Label("Name");
        nameField = new TextField();

        labelCountry = new Label("Country");
        countryField = new TextField();

        labelDescription = new Label("Description");
        descriptionField = new TextArea();
        descriptionField.setMinHeight(100);

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

        bodyGridPane.add(labelId, 0, row);
        bodyGridPane.add(idField, 1, row++);

        bodyGridPane.add(labelName, 0, row);
        bodyGridPane.add(nameField, 1, row++);

        bodyGridPane.add(labelCountry, 0, row);
        bodyGridPane.add(countryField, 1, row++);

        bodyGridPane.add(labelDescription, 0, row);
        bodyGridPane.add(descriptionField, 1, row);
    }

    @Override
    public Parent getRoot() {
        return gridPane;
    }

    public TextField getIdField() {
        return idField;
    }

    public TextField getNameField() {
        return nameField;
    }

    public TextField getCountryField() {
        return countryField;
    }

    public TextArea getDescriptionField() {
        return descriptionField;
    }
}
