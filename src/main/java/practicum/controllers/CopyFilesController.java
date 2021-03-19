package practicum.controllers;

import javafx.scene.control.TextField;
import practicum.MainApplication;
import practicum.service.CopyDatabaseService;
import practicum.utils.ApplicationConfiguration;
import practicum.views.CopyView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static practicum.utils.ApplicationConfiguration.getDatabaseType;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class CopyFilesController extends NavigatorController<CopyView.Files> {

    private final CopyDatabaseService copyDatabaseService;

    public CopyFilesController(MainApplication mainApplication) {
        super(mainApplication, mainApplication.getViewByClass(CopyView.Files.class));

        this.copyDatabaseService = new CopyDatabaseService(mainApplication);

        initialize();
    }

    protected void initialize() {
        super.initialize();

        TextField actionField = view.getActionField();
        actionField.setText(String.format("Copy %s ===> Files", getDatabaseType()));

        TextField pathField = view.getPathField();
        pathField.setText(ApplicationConfiguration.TEMP_DIR);

        TextField statusField = view.getStatusField();
        statusField.setText("idle");

       // TODO Implement view.getStartCopyButton().setOnAction(actionEvent -> copy());

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
