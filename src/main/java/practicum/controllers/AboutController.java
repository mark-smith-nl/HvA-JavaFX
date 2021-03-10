package practicum.controllers;

import javafx.scene.control.*;
import practicum.MainApplication;
import practicum.views.AboutView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import static java.lang.String.valueOf;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class AboutController extends NavigatorController<AboutView> {

    public AboutController(MainApplication mainApplication, Set<NavigatorController<?>> abstractControllers) {
        super(mainApplication, abstractControllers, new AboutView());

        initialize();
    }

    protected void initialize() {
        super.initialize();

        TextField developerField = view.getDeveloperField();
        developerField.setText("Mark Smith");
        developerField.setEditable(false);

        TextArea descriptionField = view.getDescriptionField();
        descriptionField.setText("Application to show countries\n and cities.");
        descriptionField.setEditable(false);

        TextField createdField = view.getCreatedField();
        createdField.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        createdField.setEditable(false);

        setMenuButtonSelected();
    }

    @Override
    protected void setMenuButtonSelected() {
        view.getAboutButton().setSelected(true);
    }

}
