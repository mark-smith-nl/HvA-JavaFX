package practicum.views;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import practicum.models.Country;

import java.time.LocalDate;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class AboutView extends NavigatorView {

    private static final double WIDTH = 400;

    private final GridPane gridPane;

    private final Label labelDeveloper;
    private final TextField developerField;

    private final Label labelDescription;
    private final TextArea descriptionField;

    private final Label labelCreated;
    private final TextField createdField;


    public AboutView() {
        super();
        gridPane = new GridPane();

        labelDeveloper = new Label("Developer");
        developerField = new TextField();
        developerField.setBorder(new Border(new BorderStroke(Color.RED,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        developerField.setTooltip(new Tooltip("Immutable"));

        labelDescription = new Label("Description");
        descriptionField = new TextArea();
        descriptionField.setMinHeight(100);
        descriptionField.setBorder(new Border(new BorderStroke(Color.RED,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        descriptionField.setTooltip(new Tooltip("Immutable"));

        labelCreated = new Label("Created");
        createdField = new TextField();
        createdField.setMinWidth(WIDTH);
        createdField.setBorder(new Border(new BorderStroke(Color.RED,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        createdField.setTooltip(new Tooltip("Immutable"));

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

        bodyGridPane.add(labelDeveloper, 0, row);
        bodyGridPane.add(developerField, 1, row++, 2, 1);

        bodyGridPane.add(labelDescription, 0, row);
        bodyGridPane.add(descriptionField, 1, row++, 2, 1);

        bodyGridPane.add(labelCreated, 0, row);
        bodyGridPane.add(createdField, 1, row++, 2, 1);

    }

    @Override
    public Parent getRoot() {
        return gridPane;
    }

    public TextField getDeveloperField() {
        return developerField;
    }

    public TextArea getDescriptionField() {
        return descriptionField;
    }

    public TextField getCreatedField() {
        return createdField;
    }
}
