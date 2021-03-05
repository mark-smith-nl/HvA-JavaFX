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
        view.getIdField().setText(valueOf(city.getId()));
        view.getNameField().setText(city.getName());
        view.getCountryField().setText(city.getCountry().getName());
    }

    @Override
    protected void setSelected() {
        view.getCitiesButton().setSelected(true);
    }

}
