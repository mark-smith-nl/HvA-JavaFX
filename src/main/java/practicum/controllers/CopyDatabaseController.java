package practicum.controllers;

import javafx.scene.control.TextField;
import practicum.MainApplication;
import practicum.service.CopyDatabaseService;
import practicum.views.CopyDatabaseView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import static practicum.utils.Configuration.*;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class CopyDatabaseController extends NavigatorController<CopyDatabaseView> {

    private final CopyDatabaseService copyDatabaseService;

    public CopyDatabaseController(MainApplication mainApplication, Set<NavigatorController<?>> abstractControllers) {
        super(mainApplication, abstractControllers, new CopyDatabaseView());

        this.copyDatabaseService = new CopyDatabaseService();

        initialize();
    }

    protected void initialize() {
        super.initialize();

        TextField actionField = view.getActionField();
        actionField.setText("Copy PGDB ===> H2DB");
        actionField.setEditable(false);

        TextField pathField = view.getPathField();
        pathField.setText(H2_FILEPATH);
        pathField.setEditable(false);

        TextField statusField = view.getStatusField();
        statusField.setText("idle");
        statusField.setEditable(false);

        view.getStartCopyDatabaseButton().setOnAction(actionEvent -> {
            statusField.setText("Start");
            copyDatabaseService.start();
            statusField.setText(DateTimeFormatter.ofPattern("hh:MM:ss").format(LocalDateTime.now()));
        });

        setMenuButtonSelected();

    }

    @Override
    protected void setMenuButtonSelected() {
        view.getCopyDatabaseButton().setSelected(true);
    }

}
