package practicum.controllers;

import javafx.scene.control.TextField;
import practicum.MainApplication;
import practicum.service.CopyDatabaseService;
import practicum.views.CopyDatabaseView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static practicum.utils.ApplicationConfiguration.*;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class CopyDatabaseController extends NavigatorController<CopyDatabaseView> {

    private final CopyDatabaseService copyDatabaseService;

    public CopyDatabaseController(MainApplication mainApplication) {
        super(mainApplication, mainApplication.getViewByClass(CopyDatabaseView.class));

        this.copyDatabaseService = new CopyDatabaseService(mainApplication);

        initialize();
    }

    protected void initialize() {
        super.initialize();

        TextField actionField = view.getActionField();
        actionField.setText("Copy PGDB ===> H2DB");

        TextField pathField = view.getPathField();
        pathField.setText(H2_FILEPATH);

        TextField statusField = view.getStatusField();
        statusField.setText("idle");

        view.getStartCopyDatabaseButton().setOnAction(actionEvent -> copyDatabase());

        view.setDisable(true, actionField, pathField, statusField);

        setMenuButtonSelected();

    }

    @Override
    protected void setMenuButtonSelected() {
        view.getCopyDatabaseButton().setSelected(true);
    }

    private void copyDatabase() {
        view.setVisible(false, view.getStartCopyDatabaseButton());
        view.getStatusField().setText("Start");
        copyDatabaseService.start();
        view.getStatusField().setText("Run: " + DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()));
        view.setVisible(true, view.getStartCopyDatabaseButton());
    }
}
