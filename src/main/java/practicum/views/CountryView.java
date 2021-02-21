package practicum.views;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import practicum.models.Country;

import java.time.LocalDate;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class CountryView extends NavigatorView {

    private static final double WIDTH = 400;

    private final GridPane gridPane;

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
    private final DatePicker Field;

    private final Label labeIsEUMember;
    private final CheckBox isEUMemberField;

    private final Button Button;

    private ListView<String> listView;

    private final Button newButton, removeButton, backButton;


    public CountryView() {
        super();
        gridPane = new GridPane();

        labelCountries = new Label("Countries");
        countriesField = new ComboBox<>();
        countriesField.setMinWidth(WIDTH);

        labelId = new Label("Id");
        idField = new TextField("");
        idField.setBorder(new Border(new BorderStroke(Color.RED,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        idField.setTooltip(new Tooltip("Immutable"));

        labelCountry = new Label("Country");
        countryField = new TextField();

        labelCode = new Label("Code");
        codeField = new TextField();
        codeField.setBorder(new Border(new BorderStroke(Color.RED,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        codeField.setTooltip(new Tooltip("Immutable"));

        labelDescription = new Label("Description");
        descriptionField = new TextArea();
        descriptionField.setMinHeight(100);

        labelFounded = new Label("Founded");
        Field = new DatePicker(LocalDate.now());
        Field.setMinWidth(WIDTH);

        labeIsEUMember = new Label("Member of EU");
        isEUMemberField = new CheckBox("Yes or No");

        Button = new Button("Save");
        Button.setMinWidth(WIDTH);
        Button.setTooltip(new Tooltip("Persist country"));

        listView = new ListView<>();
        listView.getItems().addAll("ListView<TodoRegel>", "test2");

        newButton = new Button("Nieuw");
        removeButton = new Button("Verwijderen");
        removeButton.setMinWidth(WIDTH);

        backButton = new Button("Terug naar overzicht");

        initialize();

    }

    public void initialize() {
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
        bodyGridPane.add(Field, 1, row++, 2, 1);

        bodyGridPane.add(labeIsEUMember, 0, row);
        bodyGridPane.add(isEUMemberField, 1, row++);

        bodyGridPane.add(Button, 0, row++, 3, 1);
        // gridPane.add(opslaan, 1, row++);

        bodyGridPane.add(listView, 0, row++, 3, 1);

        bodyGridPane.add(newButton, 0, row);
        bodyGridPane.add(removeButton, 1, row);
        bodyGridPane.add(backButton, 2, row);
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

    public DatePicker getField() {
        return Field;
    }

    public CheckBox getIsEUMemberField() {
        return isEUMemberField;
    }

    public Button getButton() {
        return Button;
    }

    public Button getNewButton() {
        return newButton;
    }

    public Button getRemoveButton() {
        return removeButton;
    }

}
