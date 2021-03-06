package practicum.controllers;

import javafx.scene.control.TextField;
import practicum.MainApplication;
import practicum.service.CopyDatabaseService;
import practicum.utils.ApplicationConfiguration;
import practicum.views.CopyView;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.String.format;
import static practicum.utils.ApplicationConfiguration.*;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class CopyDatabaseController extends NavigatorController<CopyView.Database> {

    private final CopyDatabaseService copyDatabaseService;

    public CopyDatabaseController(MainApplication mainApplication) {
        super(mainApplication, mainApplication.getViewByClass(CopyView.Database.class));

        this.copyDatabaseService = new CopyDatabaseService(mainApplication);

        initialize();
    }

    protected void initialize() {
        super.initialize();

        TextField actionField = view.getActionField();
        actionField.setText(format("Copy %s ===> %s", getDatabaseType(), H2DB_DATABASE_TYPE));

        TextField pathField = view.getPathField();
        pathField.setText(H2_FILEPATH);

        TextField statusField = view.getStatusField();
        statusField.setText("idle");

        view.getStartCopyButton().setOnAction(actionEvent -> copy());

        view.setDisable(true, actionField, pathField, statusField);

        setMenuButtonSelected();

    }

    @Override
    protected void setMenuButtonSelected() {
        view.getCopyDatabaseButton().setSelected(true);
    }

    private void copy() {
        view.setVisible(false, view.getStartCopyButton());
        view.getStatusField().setText("Start");
        copyDatabaseService.start();
        view.getStatusField().setText("Run: " + DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()));
        view.setVisible(true, view.getStartCopyButton());
    }
}
