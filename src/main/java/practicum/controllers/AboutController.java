package practicum.controllers;

import javafx.scene.control.*;
import practicum.MainApplication;
import practicum.views.AboutView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.lang.String.valueOf;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class AboutController extends NavigatorController<AboutView> {

    public AboutController(MainApplication mainApplication) {
        super(mainApplication, new AboutView());

        initialize();
    }

    protected void initialize() {
        super.initialize();

        TextField developerField = view.getDeveloperField();
        developerField.setText("Mark Smith");

        TextArea descriptionField = view.getDescriptionField();
        descriptionField.setText("Application to show countries\n and cities.");

        TextField createdField = view.getCreatedField();
        createdField.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        CheckBox isVersionedField = view.getIsVersionedField();
        isVersionedField.setSelected(true);

        view.setDisable(true, developerField, descriptionField, createdField, isVersionedField);

        setMenuButtonSelected();
    }

    @Override
    protected void setMenuButtonSelected() {
        view.getAboutButton().setSelected(true);
    }

}
