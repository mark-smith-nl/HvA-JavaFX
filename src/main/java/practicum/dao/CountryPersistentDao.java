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
public class CountryPersistentDao extends AbstractPersistentDao<Country> {

    @Override
    public List<Country> getAll() {
        List<Country> countries = new ArrayList<>();

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            String selectSql = "SELECT countryid as id, name FROM countries order by name";
            try (ResultSet resultSet = statement.executeQuery(selectSql)) {
                while (resultSet.next()) {
                    countries.add(new Country(resultSet.getInt("id"), resultSet.getString("name")));
                }
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            System.err.printf("Terminating proces due to %s\n", throwables.getMessage());
            System.exit(2);
        }

        return countries;
    }

    @Override
    public Country getById(int id) {
        Country country = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT countryid as id, name, code, description FROM countries where countryid = ?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    if (country != null) throw new IllegalStateException("Multiple entries found");
                    country = new Country(resultSet.getInt("id"), resultSet.getString("code"), resultSet.getString("name"), resultSet.getString("description"));
                }
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throw new IllegalStateException(throwables.getMessage());
        }

        return country;
    }

    @Override
    public void update(Country entity) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE countries SET name = ?, description = ? WHERE countryid = ?")) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setInt(3, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throw new IllegalStateException(throwables.getMessage());
        }
    }

    @Override
    public void persist(Country... countries) {
        try {
            Connection connection = getConnection();
                        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO countries(countryid, name, code, description) VALUES(?, ?, ?, ?)");
            for (Country country : countries) {
                preparedStatement.setInt(1, country.getId());
                preparedStatement.setString(2, country.getName());
                preparedStatement.setString(3, country.getCode());
                preparedStatement.setString(4, country.getDescription());
                preparedStatement.execute();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throw new IllegalStateException(throwables.getMessage());
        }
    }
}
