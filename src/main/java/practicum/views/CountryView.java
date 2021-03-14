package practicum.views;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import practicum.models.City;
import practicum.models.Country;

import java.time.LocalDate;

/**
 * Parent entity with child entities see {@link City}
 *
 * @author m.smithhva.nl
 */
public class CountryView extends NavigatorView {

    private static final double WIDTH = 400;

    private final Label labelCountries;
    private final ComboBox<Country> countriesField;

    private final Label labelId;
    private final TextField idField;

    private final Label labelCountry;
    private final TextField countryField;

    private final Label labelCode;
    private final TextField codeField;

    private final Label labelDescription;
    private final TextArea descriptionField;

    private final Label labelFounded;
    private final DatePicker foundedField;

    private final Label labelIsEUMember;
    private final CheckBox isEUMemberField;

    private final Button newCountryButton, saveButton, removeCountryButton, undoCountryChangesButton, newCityButton, cancelNewCountryButton;

    private final Label labelCities;
    private final ListView<City> citiesField;


    public CountryView() {
        super();

        labelCountries = new Label("Countries");
        countriesField = new ComboBox<>();
        countriesField.setMinWidth(WIDTH);

        labelId = new Label("Id");
        idField = new TextField("");

        labelCountry = new Label("Country");
        countryField = new TextField();

        labelCode = new Label("Code");
        codeField = new TextField();

        labelDescription = new Label("Description");
        descriptionField = new TextArea();
        descriptionField.setMinHeight(100);

        labelFounded = new Label("Founded");
        foundedField = new DatePicker(LocalDate.now());
        foundedField.setMinWidth(WIDTH);

        labelIsEUMember = new Label("Member of EU");
        isEUMemberField = new CheckBox();

        saveButton = new Button("Save");
        saveButton.setMinWidth(WIDTH);
        saveButton.setTooltip(new Tooltip("Persist country"));

        labelCities = new Label("Cities (Click to open in a new pane - Context click to remove)");
        citiesField = new ListView<>();

        newCountryButton = new Button("New");
        newCountryButton.setTooltip(new Tooltip("New country"));

        removeCountryButton = new Button("Remove");
        removeCountryButton.setMinWidth(WIDTH);
        removeCountryButton.setTooltip(new Tooltip("Remove country"));

        undoCountryChangesButton = new Button("Undo");
        undoCountryChangesButton.setTooltip(new Tooltip("Undo changes"));

        cancelNewCountryButton = new Button("Cancel");
        undoCountryChangesButton.setTooltip(new Tooltip("UCancel new country"));

        newCityButton = new Button("New");
        newCityButton.setTooltip(new Tooltip("New city - for selected country"));

        initialize();
    }

    protected void initialize() {
        super.initialize();

        gridPane.add(navigatorGridPane, 0, 0);

        int row = 0;
        GridPane bodyGridPane = new GridPane();
        gridPane.add(bodyGridPane, 1, 0);
        bodyGridPane.setPadding(new Insets(10, 10, 10, 10));
        bodyGridPane.setHgap(10);
        bodyGridPane.setVgap(10);

        bodyGridPane.getColumnConstraints().add(new ColumnConstraints(80, 80, 80));

        bodyGridPane.add(labelCountries, 0, row);
        bodyGridPane.add(countriesField, 1, row++, 2, 1);

        bodyGridPane.add(labelId, 0, row);
        bodyGridPane.add(idField, 1, row++, 2, 1);

        bodyGridPane.add(labelCountry, 0, row);
        bodyGridPane.add(countryField, 1, row++, 2, 1);

        bodyGridPane.add(labelCode, 0, row);
        bodyGridPane.add(codeField, 1, row++, 2, 1);

        bodyGridPane.add(labelDescription, 0, row);
        bodyGridPane.add(descriptionField, 1, row++, 2, 1);

        bodyGridPane.add(labelFounded, 0, row);
        bodyGridPane.add(foundedField, 1, row++, 2, 1);

        bodyGridPane.add(labelIsEUMember, 0, row);
        bodyGridPane.add(isEUMemberField, 1, row++);

        bodyGridPane.add(newCountryButton, 0, row);
        bodyGridPane.add(cancelNewCountryButton, 0, row);
        bodyGridPane.add(saveButton, 1, row, 1, 1);
        bodyGridPane.add(undoCountryChangesButton, 2, row++);

        bodyGridPane.add(removeCountryButton, 1, row++);

        bodyGridPane.add(labelCities, 0, row++, 3, 1);
        bodyGridPane.add(citiesField, 0, row++, 3, 1);

        bodyGridPane.add(newCityButton, 0, row);
    }

    @Override
    public Parent getRoot() {
        return gridPane;
    }

    public ComboBox<Country> getCountriesField() {
        return countriesField;
    }

    public TextField getIdField() {
        return idField;
    }

    public TextField getCountryField() {
        return countryField;
    }

    public TextField getCodeField() {
        return codeField;
    }

    public TextArea getDescriptionField() {
        return descriptionField;
    }

    public DatePicker getFoundedField() {
        return foundedField;
    }

    public CheckBox getIsEUMemberField() {
        return isEUMemberField;
    }

    public Label getLabelCities() {
        return labelCities;
    }

    public ListView<City> getCitiesField() {
        return citiesField;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Button getNewCountryButton() {
        return newCountryButton;
    }

    public Button getRemoveCountryButton() {
        return removeCountryButton;
    }

    public Button getUndoCountryChangesButton() {
        return undoCountryChangesButton;
    }

    public Button getCancelNewCountryButton() {
        return cancelNewCountryButton;
    }

    public Button getNewCityButton() {
        return newCityButton;
    }
}
