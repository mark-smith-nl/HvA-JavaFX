package practicum.views;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class CopyDatabaseView extends NavigatorView {

    private static final double WIDTH = 400;

    private final GridPane gridPane;

    private final Label labelAction;
    private final TextField actionField;

    private final Label labelPath;
    private final TextField pathField;

    private final Label labelStatus;
    private final TextField statusField;

    private final Button startCopyDatabaseButton;

    public CopyDatabaseView() {
        super();
        gridPane = new GridPane();

        labelAction = new Label("Action");
        actionField = new TextField();
        actionField.setMinWidth(WIDTH);
        actionField.setBorder(new Border(new BorderStroke(Color.RED,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        actionField.setTooltip(new Tooltip("Immutable"));

        labelPath = new Label("Path");
        pathField = new TextField();
        pathField.setMinWidth(WIDTH);
        pathField.setBorder(new Border(new BorderStroke(Color.RED,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        pathField.setTooltip(new Tooltip("Immutable"));

        labelStatus = new Label("Status");
        statusField = new TextField();
        statusField.setMinWidth(WIDTH);
        statusField.setBorder(new Border(new BorderStroke(Color.RED,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        statusField.setTooltip(new Tooltip("Immutable"));

        startCopyDatabaseButton = new Button("Start");
        startCopyDatabaseButton.setMinWidth(WIDTH);

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

        bodyGridPane.add(labelAction, 0, row);
        bodyGridPane.add(actionField, 1, row++, 2, 1);

        bodyGridPane.add(labelPath, 0, row);
        bodyGridPane.add(pathField, 1, row++, 2, 1);

        bodyGridPane.add(labelStatus, 0, row);
        bodyGridPane.add(statusField, 1, row++, 2, 1);

        bodyGridPane.add(startCopyDatabaseButton, 1, row, 2, 1);
    }


    @Override
    public Parent getRoot() {
        return gridPane;
    }

    public TextField getActionField() {
        return actionField;
    }

    public TextField getPathField() {
        return pathField;
    }

    public TextField getStatusField() {
        return statusField;
    }

    public Button getStartCopyDatabaseButton() {
        return startCopyDatabaseButton;
    }
}
