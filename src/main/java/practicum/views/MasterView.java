package practicum.views;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import practicum.models.Country;

import java.time.LocalDate;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class MasterView implements View {

    private static final double WIDTH = 400;

    private final GridPane gridPane;

    private final Label labelCountries;
    private final ComboBox<Country> countries;

    private final Label labelId;
    private final TextField id;

    private final Label labelCountry;
    private final TextField country;

    private final Label labelCode;
    private final TextField code;

    private final Label labelDescription;
    private final TextArea description;

    private final Label labelFounded;
    private final DatePicker founded;

    private final Label labeIsEUMember;
    private final CheckBox isEUMember;

    private final Button save;

    private ListView<String> listView;

    private final Button nieuw, remove, terug;

    public MasterView() {
        gridPane = new GridPane();

        labelCountries = new Label("Countries");
        countries = new ComboBox<>();
        countries.setMinWidth(WIDTH);

        labelId = new Label("Id");
        id = new TextField("");
        id.setBorder(new Border(new BorderStroke(Color.RED,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        id.setTooltip(new Tooltip("Immutable"));

        labelCountry = new Label("Country");
        country = new TextField();

        labelCode = new Label("Code");
        code = new TextField();
        code.setBorder(new Border(new BorderStroke(Color.RED,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        code.setTooltip(new Tooltip("Immutable"));

        labelDescription = new Label("Description");
        description = new TextArea();
        description.setMinHeight(100);

        labelFounded = new Label("Founded");
        founded = new DatePicker(LocalDate.now());
        founded.setMinWidth(WIDTH);

        labeIsEUMember = new Label("Member of EU");
        isEUMember = new CheckBox("Yes or No");

        save = new Button("Save");
        save.setMinWidth(WIDTH);
        save.setTooltip(new Tooltip("Persist country"));

        listView = new ListView<>();
        listView.getItems().addAll("ListView<TodoRegel>", "test2");

        nieuw = new Button("Nieuw");
        remove = new Button("Verwijderen");
        remove.setMinWidth(WIDTH);

        terug = new Button("Terug naar overzicht");

        initialize();
    }

    private void initialize() {
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.getColumnConstraints().add(new ColumnConstraints(80, 80, 80));

        int row = 0;

        gridPane.add(labelCountries, 0, row);
        gridPane.add(countries, 1, row++, 2, 1);

        gridPane.add(labelId, 0, row);
        gridPane.add(id, 1, row++, 2, 1);

        gridPane.add(labelCountry, 0, row);
        gridPane.add(country, 1, row++, 2, 1);

        gridPane.add(labelCode, 0, row);
        gridPane.add(code, 1, row++, 2, 1);

        gridPane.add(labelDescription, 0, row);
        gridPane.add(description, 1, row++, 2, 1);

        gridPane.add(labelFounded, 0, row);
        gridPane.add(founded, 1, row++, 2, 1);

        gridPane.add(labeIsEUMember, 0, row);
        gridPane.add(isEUMember, 1, row++);

        gridPane.add(save, 0, row++, 3, 1);
        // gridPane.add(opslaan, 1, row++);

        gridPane.add(listView, 0, row++, 3, 1);

        gridPane.add(nieuw, 0, row);
        gridPane.add(remove, 1, row);
        gridPane.add(terug, 2, row);
    }

    @Override
    public Parent getRoot() {
        return gridPane;
    }

    public ComboBox<Country> getCountries() {
        return countries;
    }

    public TextField getId() {
        return id;
    }

    public TextField getCountry() {
        return country;
    }

    public TextField getCode() {
        return code;
    }

    public TextArea getDescription() {
        return description;
    }

    public DatePicker getFounded() {
        return founded;
    }

    public CheckBox getIsEUMember() {
        return isEUMember;
    }

    public Button getSave() {
        return save;
    }

    public Button getNieuw() {
        return nieuw;
    }

    public Button getRemove() {
        return remove;
    }

}
