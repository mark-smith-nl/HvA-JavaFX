package practicum.dao;

import practicum.models.City;
import practicum.models.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class CityPersistentDao extends AbstractPersistentDao<City> {

    @Override
    public List<City> getAll() {
        List<City> entities = new ArrayList<>();

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            String selectSql = "SELECT city_id as id, name FROM cities order by name";
            try (ResultSet resultSet = statement.executeQuery(selectSql)) {
                while (resultSet.next()) entities.add(new City(resultSet.getInt("id"), resultSet.getString("name")));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            System.err.printf("Terminating proces due to %s\n", throwables.getMessage());
            System.exit(2);
        }

        return entities;
    }

    @Override
    public City getById(int id) {
        City entity = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT city_id as id, name, code, description FROM cities where city_id = ?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    if (entity != null) throw new IllegalStateException("Multiple entries found");
                    entity = new City(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("description"));
                }
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throw new IllegalStateException(throwables.getMessage());
        }

        return entity;
    }

    @Override
    public void update(City entity) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE countries SET name = ?, description = ? WHERE country_id = ?")) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setInt(3, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throw new IllegalStateException(throwables.getMessage());
        }
    }

    @Override
    public void persist(Country... entities) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO countries(country_id, name, code, description) VALUES(?, ?, ?, ?)");
            for (Country entity : entities) {
                preparedStatement.setInt(1, entity.getId());
                preparedStatement.setString(2, entity.getName());
                preparedStatement.setString(3, entity.getCode());
                preparedStatement.setString(4, entity.getDescription());
                preparedStatement.execute();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throw new IllegalStateException(throwables.getMessage());
        }
    }
}
