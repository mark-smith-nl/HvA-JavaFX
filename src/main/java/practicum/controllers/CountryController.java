package practicum.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import practicum.MainApplication;
import practicum.models.AbstractModel;
import practicum.models.City;
import practicum.models.Country;
import practicum.service.CityService;
import practicum.service.CountryService;
import practicum.views.CountryView;

import java.util.Collections;
import java.util.Comparator;

import static java.lang.String.*;
import static practicum.models.AbstractModel.NEW_ID;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class CountryController extends NavigatorController<CountryView> implements ModifyEntity<Country> {

    /* The entity which is shown and being edited.
    This can either be a persisted entity or a new entity.
     */
    private Country country;

    private final CountryService countryService;

    private final CityService cityService;

    public CountryController(MainApplication mainApplication) {
        super(mainApplication, mainApplication.getViewByClass(CountryView.class));

        countryService = mainApplication.getServiceByClass(CountryService.class);
        cityService = mainApplication.getServiceByClass(CityService.class);

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

        countriesField.setOnAction(actionEvent -> countriesFieldChanged(countriesField.getValue()));

        view.getNewCountryButton().setOnAction(actionEvent -> newCountry());

        view.getSaveButton().setOnAction(actionEvent -> saveCountry());

        view.getUndoCountryChangesButton().setOnAction(actionEvent -> undoChanges());

        view.getCancelNewCountryButton().setOnAction(actionEvent -> cancelNewCountry());

        view.getRemoveCountryButton().setOnAction(actionEvent -> removeCountry());

        view.getCitiesField().setOnMouseClicked(this::citySelected);

        view.getNewCityButton().setOnAction(actionEvent -> newCity());

        // TODO What if there are no countries?

        Country selectedCountry = new Country(154, "The Netherlands...."); // Check the equals.
        countriesField.getSelectionModel().select(selectedCountry);
        countriesFieldChanged(selectedCountry);
        setMenuButtonSelected();
    }

    private void countriesFieldChanged(Country selectedCountry) {
        // Get all the country data from the database
        if (selectedCountry != null) {
            Country country = countryService.getById(selectedCountry.getId());
            // Get all the country data from the database
            country.setCities(cityService.getForCountry(country));
            setControlledEntity(country);
        }
    }

    private void newCountry() {
        country = new Country();
        country.setCities(Collections.emptyList());
        setControlledEntity(country);
    }

    private void saveCountry() {
        country.setName(view.getCountryField().getText());
        country.setCode(view.getCodeField().getText());
        country.setDescription(view.getDescriptionField().getText());
        country.setFounded(view.getFoundedField().getValue());
        country.setEUMumber(view.getIsEUMemberField().isSelected());

        try {
            countryService.saveOrUpdate(country);
            if (!view.getCountriesField().getItems().contains(country)) {
                view.getCountriesField().getItems().add(country);
                view.getCountriesField().getSelectionModel().select(country);
                view.getCountriesField().getItems().sort(Comparator.comparing(Country::getName));
            }
            ;

        } catch (Exception e) {
            new Alert(Alert.AlertType.CONFIRMATION, format("Country %s (%d) was not saved due to:\n%s", country.getName(), country.getId(), e.getMessage()),
                    ButtonType.OK).show();
        }
    }

    private void undoChanges() {
        view.getCountryField().setText(country.getName());
        view.getCodeField().setText(country.getCode());
        view.getDescriptionField().setText(country.getDescription());
        view.getFoundedField().setValue(country.getFounded());
        view.getIsEUMemberField().setSelected(country.isEUMumber());
    }

    private void cancelNewCountry() {
        countriesFieldChanged(view.getCountriesField().getSelectionModel().getSelectedItem());
    }

    private void removeCountry() {
        countryService.remove(country);
        view.getCountriesField().getItems().remove(country);
    }

    private void citySelected(MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            CitiesController citiesController = mainApplication.getControllerByClass(CitiesController.class);
            City city = view.getCitiesField().getSelectionModel().getSelectedItem();
            if (city != null) {
                city = cityService.getById(city.getId());
                city.setCountry(country);
                citiesController.setControlledEntity(city);
                view.getCitiesButton().fire();
            }
        } else if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
            System.out.println("To be implemented: delete on context click");
        }
    }

    private void newCity() {
        Country country = view.getCountriesField().getValue();
        City city = new City(NEW_ID, "Specify a name", country);
        CitiesController citiesController = mainApplication.getControllerByClass(CitiesController.class);
        citiesController.setControlledEntity(city);
        citiesController.setMenuButtonSelected();
        mainApplication.switchController(citiesController);
    }

    @Override
    public void setControlledEntity(Country country) {
        this.country = country;
        view.getIdField().setText(valueOf(country.getId()));
        view.getCountryField().setText(country.getName());
        view.getCodeField().setText(country.getCode());
        view.getDescriptionField().setText(country.getDescription());
        view.getFoundedField().setValue(country.getFounded());
        view.getIsEUMemberField().setSelected(country.isEUMumber());

        view.getCitiesField().getItems().setAll(country.getCities());

        CitiesController citiesController = mainApplication.getControllerByClass(CitiesController.class);
        if (citiesController != null) citiesController.setControlledEntity(null);

        view.setDisable(true, view.getIdField()); // The id field can never be modified by the user
        if (country.isNewEntity()) {
            view.setDisable(false, view.getCodeField());
            view.setDisable(true, view.getCountriesField());
            view.setVisible(false, view.getNewCountryButton(),
                    view.getUndoCountryChangesButton(),
                    view.getRemoveCountryButton(),
                    view.getLabelCities(),
                    view.getCitiesField(),
                    view.getNewCityButton());
            view.setVisible(true, view.getCancelNewCountryButton(),
                    view.getSaveButton());
        } else { // Persisted entity
            view.setDisable(false, view.getCountriesField());
            view.setDisable(true, view.getCodeField());
            view.setVisible(false, view.getCancelNewCountryButton());
            view.setVisible(true, view.getNewCountryButton(),
                    view.getSaveButton(),
                    view.getUndoCountryChangesButton(),
                    view.getRemoveCountryButton(),
                    view.getLabelCities(),
                    view.getCitiesField(),
                    view.getNewCityButton());
        }
    }

    @Override
    protected void setMenuButtonSelected() {
        view.getCountriesButton().setSelected(true);
    }

}
