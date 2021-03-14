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
        descriptionField.setText("Demo project voor HvA/OOP2\n\n" +
                "Application to show countries(parent) and cities(child).\n\n" +
                "This is a program to show students how one could create a program\nNOT how it should be created.\n" +
                "It stores its data in a H2DB or Postgres.\n" +
                "It does NOT use files to serialize/deserialize objects.\n\n" +
                "TODO: Add/remove/modify cities.");

        TextField createdField = view.getCreatedField();
        createdField.setText(LocalDate.of(2021, 3, 10).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

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
