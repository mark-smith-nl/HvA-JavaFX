package practicum.controllers;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import practicum.MainApplication;
import practicum.views.AboutView;
import practicum.views.CitiesView;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class CitiesController extends NavigatorController<CitiesView> {

    public CitiesController(MainApplication mainApplication, Set<NavigatorController<?>> abstractControllers) {
        super(mainApplication, abstractControllers, new CitiesView());

        initialize();
    }

    protected void initialize() {
        super.initialize();

        TextField citiesField = view.getCitiesField();
        citiesField.setText("Cities");
        citiesField.setEditable(false);

        setSelected();
    }

    @Override
    protected void setSelected() {
        view.getCitiesButton().setSelected(true);
    }

}
