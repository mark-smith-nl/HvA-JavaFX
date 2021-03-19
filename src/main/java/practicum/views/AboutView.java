package practicum.views;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * Credentials
 *
 * @author m.smithhva.nl
 */
public class AboutView extends NavigatorView {

    private static final double WIDTH = 400;

    private final Label labelDeveloper;
    private final TextField developerField;

    private final Label labelDescription;
    private final TextArea descriptionField;

    private final Label labelCreated;
    private final TextField createdField;

    private final Label labelIsVersioned;
    private final CheckBox isVersionedField;

    private final Label labelDatabaseType;
    private final TextField databaseTypeField;

    private final Label labelH2DbPath;
    private final TextField h2DbpathField;

    public AboutView() {
        super();

        labelDeveloper = new Label("Developer");
        developerField = new TextField();

        labelDescription = new Label("Description");
        descriptionField = new TextArea();
        descriptionField.setMinHeight(100);

        labelCreated = new Label("Created");
        createdField = new TextField();
        createdField.setMinWidth(WIDTH);

        labelIsVersioned = new Label("Versioned");
        isVersionedField = new CheckBox("Check git tags");

        labelDatabaseType = new Label("Database");
        databaseTypeField = new TextField();

        labelH2DbPath = new Label("H2DB");
        h2DbpathField = new TextField();

        initialize();
    }

    protected void initialize() {
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

        bodyGridPane.add(labelIsVersioned, 0, row);
        bodyGridPane.add(isVersionedField, 1, row++, 2, 1);

        bodyGridPane.add(labelDatabaseType, 0, row);
        bodyGridPane.add(databaseTypeField, 1, row++, 2, 1);

        bodyGridPane.add(labelH2DbPath, 0, row);
        bodyGridPane.add(h2DbpathField, 1, row, 2, 1);
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

    public CheckBox getIsVersionedField() {
        return isVersionedField;
    }

    public TextField getDatabaseTypeField() {
        return databaseTypeField;
    }

    public TextField getH2DbpathField() {
        return h2DbpathField;
    }
}
