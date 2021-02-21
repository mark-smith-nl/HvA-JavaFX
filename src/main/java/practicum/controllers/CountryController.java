package practicum.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import practicum.MainApplication;
import practicum.models.Country;
import practicum.service.CountryService;
import practicum.views.CountryView;

import java.util.Map;
import java.util.Set;

import static java.lang.String.*;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class CountryController extends NavigatorController<CountryView> {

    private final CountryService countryService;

    public CountryController(MainApplication mainApplication, Set<NavigatorController<?>> abstractControllers) {
        super(mainApplication, abstractControllers, new CountryView());

        this.countryService = new CountryService();
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

        countriesField.valueProperty().addListener((observableValue, country, newCountry) -> {
            idField.setText(valueOf(newCountry.getId()));
            countryField.setText(newCountry.getName());
            codeField.setText(newCountry.getCode());
            descriptionField.setText(newCountry.getDescription());
            foundedField.setValue(newCountry.getFounded());
            isEUMemberField.setSelected(newCountry.isEUMumber());
        });

        Button saveButton = view.getButton();
        saveButton.setOnAction(actionEvent -> {
            Country country = countriesField.getValue();
            country.setName(countryField.getText());
            country.setDescription(descriptionField.getText());
            country.setFounded(foundedField.getValue());
            country.setEUMumber(isEUMemberField.isSelected());
            countryService.update(country);
        });

        countriesField.getSelectionModel().selectFirst();

        setSelected();
    }

    @Override
    protected void setSelected() {
        view.getCountriesButton().setSelected(true);
    }

}
