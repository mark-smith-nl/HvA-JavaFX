package practicum.views;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

/**
 * View to show database copy settings.
 *
 * @author m.smithhva.nl
 */
public abstract class CopyView extends NavigatorView {

    private static final double WIDTH = 400;

    private final Label labelAction;
    private final TextField actionField;

    private final Label labelPath;
    private final TextField pathField;

    private final Label labelStatus;
    private final TextField statusField;

    private final Button startCopyButton;

    public CopyView() {
        super();

        labelAction = new Label("Action");
        actionField = new TextField();
        actionField.setMinWidth(WIDTH);

        labelPath = new Label("Path");
        pathField = new TextField();
        pathField.setMinWidth(WIDTH);

        labelStatus = new Label("Status");
        statusField = new TextField();
        statusField.setMinWidth(WIDTH);

        startCopyButton = new Button("Start");
        startCopyButton.setMinWidth(WIDTH);

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

        bodyGridPane.add(labelAction, 0, row);
        bodyGridPane.add(actionField, 1, row++, 2, 1);

        bodyGridPane.add(labelPath, 0, row);
        bodyGridPane.add(pathField, 1, row++, 2, 1);

        bodyGridPane.add(labelStatus, 0, row);
        bodyGridPane.add(statusField, 1, row++, 2, 1);

        bodyGridPane.add(startCopyButton, 1, row, 2, 1);
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

    public Button getStartCopyButton() {
        return startCopyButton;
    }

    public static class Database extends CopyView {

        public Database() {
            super();
        }

    }

    public static class Files extends CopyView {

        public Files() {
            super();
        }

    }
}
