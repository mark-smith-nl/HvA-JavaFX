package practicum.controllers;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import practicum.MainApplication;
import practicum.models.City;
import practicum.views.AboutView;
import practicum.views.CitiesView;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

import static java.lang.String.valueOf;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class CitiesController extends NavigatorController<CitiesView> {

    private City city;

    public CitiesController(MainApplication mainApplication, Set<NavigatorController<?>> abstractControllers) {
        super(mainApplication, abstractControllers, new CitiesView());

        initialize();
    }

    protected void initialize() {
        super.initialize();

    }

    public void setCity(City city) {
        this.city = city;
        view.getIdField().setText(city == null ? "" : valueOf(city.getId()));
        view.getNameField().setText(city == null ? "" : city.getName());
        view.getCountryField().setText(city == null ? "" : city.getCountry().getName());
        view.getDescriptionField().setText(city == null ? "" : city.getDescription());
    }

    @Override
    protected void setMenuButtonSelected() {
        view.getCitiesButton().setSelected(true);
    }

}
