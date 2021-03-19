package practicum.controllers;

import practicum.MainApplication;
import practicum.models.City;
import practicum.views.CitiesView;
import practicum.views.CountryView;

import static java.lang.String.valueOf;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class CitiesController extends NavigatorController<CitiesView> implements ModifyEntity<City>{

    private City city;

    public CitiesController(MainApplication mainApplication) {
        super(mainApplication, mainApplication.getViewByClass(CitiesView.class));

        initialize();
    }

    protected void initialize() {
        super.initialize();

        view.setDisable(false, view.getIdField(), view.getCountryField());
    }

    @Override
    public void setControlledEntity(City city) {
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
