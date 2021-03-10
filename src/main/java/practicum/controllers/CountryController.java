package practicum.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import practicum.MainApplication;
import practicum.models.City;
import practicum.models.Country;
import practicum.service.CityService;
import practicum.service.CountryService;
import practicum.views.CountryView;

import java.util.Set;

import static java.lang.String.*;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class CountryController extends NavigatorController<CountryView> {

    private final CountryService countryService;

    private final CityService cityService;

    public CountryController(MainApplication mainApplication, Set<NavigatorController<?>> abstractControllers) {
        super(mainApplication, abstractControllers, new CountryView());

        this.countryService = new CountryService();
        this.cityService = new CityService();

        initialize();
    }

    protected void initialize() {
        super.initialize();

        ObservableList<Country> countries = FXCollections.observableArrayList(countryService.getAll());
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

        TextField idField = view.getIdField();
        idField.setEditable(false);

        TextField countryField = view.getCountryField();

        TextField codeField = view.getCodeField();
        codeField.setEditable(false);

        TextArea descriptionField = view.getDescriptionField();

        DatePicker foundedField = view.getField();

        CheckBox isEUMemberField = view.getIsEUMemberField();

        ListView<City> citiesListView = view.getCitiesListView();

        countriesField.valueProperty().addListener((observableValue, country, selectedCountry) -> {
            Country countryExtended = countryService.getById(selectedCountry.getId());
            idField.setText(valueOf(countryExtended.getId()));
            countryField.setText(countryExtended.getName());
            codeField.setText(countryExtended.getCode());
            descriptionField.setText(countryExtended.getDescription());
            foundedField.setValue(countryExtended.getFounded());
            isEUMemberField.setSelected(countryExtended.isEUMumber());

            citiesListView.getItems().setAll(cityService.getForCountry(selectedCountry));

            CitiesController citiesController = getControllerByClass(CitiesController.class);
            if (citiesController != null) citiesController.setCity(null);
        });

        citiesListView.setOnMouseClicked(mouseEvent -> {
            CitiesController citiesController = getControllerByClass(CitiesController.class);
            Country country = countriesField.getSelectionModel().getSelectedItem();
            City cityExtended = cityService.getById(citiesListView.getSelectionModel().getSelectedItem().getId());
            cityExtended.setCountry(country);
            citiesController.setCity(cityExtended);
            view.getCitiesButton().fire();
        });

        Button saveButton = view.getSaveButton();
        saveButton.setOnAction(actionEvent -> {
            Country country = countriesField.getValue();
            country.setName(countryField.getText());
            country.setCode(codeField.getText());
            country.setDescription(descriptionField.getText());
            country.setFounded(foundedField.getValue());
            country.setEUMumber(isEUMemberField.isSelected());
            countryService.update(country);
        });

        Button undoButton = view.getUndoButton();
        undoButton.setOnAction(actionEvent -> {
            Country country = countriesField.getValue();
            country = countryService.getById(country.getId());
            countryField.setText(country.getName());
            codeField.setText(country.getCode());
            descriptionField.setText(country.getDescription());
            foundedField.setValue(country.getFounded());
            isEUMemberField.setSelected(country.isEUMumber());
        });

        countriesField.getSelectionModel().selectFirst();

        setMenuButtonSelected();
    }

    @Override
    protected void setMenuButtonSelected() {
        view.getCountriesButton().setSelected(true);
    }

}
