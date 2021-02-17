package practicum.dao;

import practicum.models.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class CountryPersistentDao extends practicum.dao.AbstractPersistentDao<Country> {

    @Override
    public List<Country> getAll() {
        List<Country> countries = new ArrayList<>();

        try (Connection connection = getConnection()) {
            Statement stmt = connection.createStatement();
            String selectSql = "SELECT countryid as id, name, code, description FROM sandbox.countries order by name";
            try (ResultSet resultSet = stmt.executeQuery(selectSql)) {
                while(resultSet.next()) {
                    countries.add(new Country(resultSet.getInt("id"), resultSet.getString("code"), resultSet.getString("name"), resultSet.getString("description")));
                }
            }
        } catch (SQLException throwables) {
            System.err.printf("Terminating proces due to %s\n", throwables.getMessage());
            System.exit(2);
        }

        return countries;
    }

    @Override
    public void update(Country entity) {
        try (Connection connection = getConnection();
             PreparedStatement updateCountry = connection.prepareStatement(
                     "UPDATE sandbox.countries SET name = ?, description = ? WHERE countryid = ?")) {
            updateCountry.setString(1, entity.getName());
            updateCountry.setString(2, entity.getDescription());
            updateCountry.setInt(3, entity.getId());
            updateCountry.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
