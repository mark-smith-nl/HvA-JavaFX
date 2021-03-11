package practicum.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import practicum.MainApplication;
import practicum.models.City;
import practicum.models.Country;
import practicum.service.CityService;
import practicum.service.CountryService;
import practicum.views.CountryView;

import java.util.Collections;
import java.util.List;

import static java.lang.String.*;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class CountryController extends NavigatorController<CountryView> implements ModifyEntity<Country> {

    private Country country;

    public CountryController(MainApplication mainApplication) {
        super(mainApplication, new CountryView());

        initialize();
    }

    protected void initialize() {
        super.initialize();

        ObservableList<Country> countries = FXCollections.observableArrayList(mainApplication.getServiceByClass(CountryService.class).getAll());
        ComboBox<Country> countriesField = view.getCountriesField();
        countriesField.getItems().addAll(countries);
        countriesField.setConverter(new StringConverter<>() {
            @Override
            public String toString(Country country) {
                return country != null ? country.getName() : "";
            }

            @Override
            public Country fromString(String string) {
                return null;
            }
        });

        countriesField.valueProperty().addListener((observableValue, country, selectedCountry) -> countriesFieldChanged(selectedCountry));

        view.getNewCountryButton().setOnAction(actionEvent -> newCountry());

        view.getSaveButton().setOnAction(actionEvent -> saveCountry());

        view.getUndoCountryChangesButton().setOnAction(actionEvent -> undoChanges());

        view.getCitiesField().setOnMouseClicked(this::citySelected);

        view.getNewCityButton().setOnAction(actionEvent -> {
            Country country = countriesField.getValue();
            City city = new City(-1, "Specify a name", country);
            CitiesController citiesController = mainApplication.getControllerByClass(CitiesController.class);
            citiesController.setControlledEntity(city);
            citiesController.setMenuButtonSelected();
            mainApplication.switchController(citiesController);
        });

        // TODO What if there are no countries?
        countriesField.getSelectionModel().select(mainApplication.getServiceByClass(CountryService.class).getById(154));

        view.setEditable(false, view.getIdField(), view.getCodeField());

        setMenuButtonSelected();
    }

    private void countriesFieldChanged(Country selectedCountry) {
        Country country = mainApplication.getServiceByClass(CountryService.class).getById(selectedCountry.getId());
        country.setCities(mainApplication.getServiceByClass(CityService.class).getForCountry(selectedCountry));
        setControlledEntity(country);
    }

    private void newCountry() {
        country = new Country(0, "");
        country.setCities(Collections.emptyList());
        setControlledEntity(country);
        view.setEditable(true, view.getCodeField());
        view.getCountriesField().setDisable(true);
        view.getCitiesField().setDisable(true);
    }

    private void saveCountry() {
        country.setName(view.getCountryField().getText());
        country.setCode(view.getCodeField().getText());
        country.setDescription(view.getDescriptionField().getText());
        country.setFounded(view.getField().getValue());
        country.setEUMumber(view.getIsEUMemberField().isSelected());

        mainApplication.getServiceByClass(CountryService.class).update(country);
    }

    private void undoChanges() {
        view.getCountryField().setText(country.getName());
        view.getCodeField().setText(country.getCode());
        view.getDescriptionField().setText(country.getDescription());
        view.getField().setValue(country.getFounded());
        view.getIsEUMemberField().setSelected(country.isEUMumber());
    }

    private void citySelected(MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            CitiesController citiesController = mainApplication.getControllerByClass(CitiesController.class);
            City city = view.getCitiesField().getSelectionModel().getSelectedItem();
            if (city != null) {
                city = mainApplication.getServiceByClass(CityService.class).getById(city.getId());
                city.setCountry(country);
                citiesController.setControlledEntity(city);
                view.getCitiesButton().fire();
            }
        } else if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
            System.out.println("To be implemented: delete on context click");
        }
    }

    @Override
    public void setControlledEntity(Country country) {
        this.country = country;
        view.getIdField().setText(valueOf(country.getId()));
        view.getCountryField().setText(country.getName());
        view.getCodeField().setText(country.getCode());
        view.getDescriptionField().setText(country.getDescription());
        view.getField().setValue(country.getFounded());
        view.getIsEUMemberField().setSelected(country.isEUMumber());

        view.getCitiesField().getItems().setAll(country.getCities());

        CitiesController citiesController = mainApplication.getControllerByClass(CitiesController.class);
        if (citiesController != null) citiesController.setControlledEntity(null);
        view.setEditable(false, view.getIdField(), view.getCodeField());
    }

    @Override
    protected void setMenuButtonSelected() {
        view.getCountriesButton().setSelected(true);
    }

}
