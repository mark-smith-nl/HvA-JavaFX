package practicum.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import practicum.models.Country;
import practicum.service.CountryService;
import practicum.views.MasterView;
import practicum.views.View;

import static java.lang.String.*;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class MasterController implements Controller {

    private final MasterView view;

    private final CountryService countryService;

    public MasterController() {
        this.view = new MasterView();
        this.countryService = new CountryService();
        initialize();
    }

    private void initialize() {
        ObservableList<Country> countries = FXCollections.observableArrayList(countryService.getAll());

        ComboBox<Country> countriesField = view.getCountries();
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

        TextField idField = view.getId();
        idField.setEditable(false);
        TextField countryField = view.getCountry();
        TextField codeField = view.getCode();
        codeField.setEditable(false);
        TextArea descriptionField = view.getDescription();
        DatePicker foundedField = view.getFounded();
        CheckBox isEUMemberField = view.getIsEUMember();

        countriesField.valueProperty().addListener(new ChangeListener<Country>() {
            @Override
            public void changed(ObservableValue<? extends Country> observableValue, Country country, Country newCountry) {
                idField.setText(valueOf(newCountry.getId()));
                countryField.setText(newCountry.getName());
                codeField.setText(newCountry.getCode());
                descriptionField.setText(newCountry.getDescription());
                foundedField.setValue(newCountry.getFounded());
                isEUMemberField.setSelected(newCountry.isEUMumber());
            }
        });

        Button saveButton = view.getSave();
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Country country = countriesField.getValue();
                int id = country.getId();
                country.setName(countryField.getText());
                country.setDescription(descriptionField.getText());
                country.setFounded(foundedField.getValue());
                country.setEUMumber(isEUMemberField.isSelected());
                countryService.update(country);
            }
        });

        countriesField.getSelectionModel().selectFirst();
    }

    @Override
    public View getView() {
        return view;
    }
}
