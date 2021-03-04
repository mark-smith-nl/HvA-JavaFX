package practicum.models;

import java.time.LocalDate;
import java.util.Set;

import static java.lang.String.format;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class Country {

    private int id;

    private String code;

    private String name;

    private String description;

    private LocalDate founded;

    private boolean isEUMumber;

    private Set<City> cities;

    public Country(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Country(int id, String code, String name, String description) {
        this(id, name);
        this.code = code;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getFounded() {
        return founded;
    }

    public void setFounded(LocalDate founded) {
        this.founded = founded;
    }

    public boolean isEUMumber() {
        return isEUMumber;
    }

    public void setEUMumber(boolean EUMumber) {
        isEUMumber = EUMumber;
    }

    @Override
    public String toString() {
        return format("%s %s (%s)", name, code, id);
    }
}
