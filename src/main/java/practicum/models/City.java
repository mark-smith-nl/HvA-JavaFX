package practicum.models;

import java.time.LocalDate;

import static java.lang.String.format;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class City {

    private final int id;

    private Country country;

    private String name;

    private String description;

    public City(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
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

    @Override
    public String toString() {
        return format("%s " + " (%s)....", name, id);
    }
}
