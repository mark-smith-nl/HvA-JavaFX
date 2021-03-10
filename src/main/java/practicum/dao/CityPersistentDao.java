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
                     "SELECT city_id as id, name, description FROM cities where city_id = ?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    if (entity != null) throw new IllegalStateException("Multiple entries found");
                    entity = new City(resultSet.getInt("id"), resultSet.getString("name"), null, resultSet.getString("description"));
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
                     "UPDATE countries SET name = ?, description = ? WHERE city_id = ?")) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setInt(3, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throw new IllegalStateException(throwables.getMessage());
        }
    }

    @Override
    public void persist(List<City> entities) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO cities(city_id, name, description, country_id) VALUES(?, ?, ?, ?)");
            for (City entity : entities) {
                preparedStatement.setInt(1, entity.getId());
                preparedStatement.setString(2, entity.getName());
                preparedStatement.setString(3, entity.getDescription());
                preparedStatement.setInt(4, entity.getCountry().getId());
                preparedStatement.execute();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throw new IllegalStateException(throwables.getMessage());
        }
    }

    public List<City> getForCountry(Country country) {
        List<City> entities = new ArrayList<>();

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT city_id as id, name from cities where country_id = ? order by name");
            preparedStatement.setInt(1, country.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) entities.add(new City(resultSet.getInt("id"), resultSet.getString("name"), country ));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            System.err.printf("Terminating proces due to %s\n", throwables.getMessage());
            System.exit(2);
        }

        return entities;
    }

    @Override
    public void initializeH2DbTable() {
        try (Connection connection = getH2DbConnection()) {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE cities " +
                    "(city_id INTEGER not NULL, " +
                    " name VARCHAR(64), " +
                    " description VARCHAR(1024), " +
                    " country_id INTEGER REFERENCES countries (country_id))";
            statement.executeUpdate(sql);
        } catch (SQLException | ClassNotFoundException throwables) {
            throw new IllegalStateException(throwables.getMessage());
        }
    }

    @Override
    public void copyEntitiesFromPostgresToH2Db() {
        try (Connection connection = getConnection(); Connection h2DbConnection = getH2DbConnection()) {
            Statement statement = connection.createStatement();
            String selectSql = "SELECT city_id, name, description, country_id FROM cities";
            try (ResultSet resultSet = statement.executeQuery(selectSql)) {
                while (resultSet.next()) {
                    PreparedStatement preparedStatement = h2DbConnection.prepareStatement("INSERT INTO cities(city_id, name, description, country_id) " +
                            "VALUES(?, ?, ?, ?)");
                    preparedStatement.setInt(1, resultSet.getInt("city_id"));
                    preparedStatement.setString(2, resultSet.getString("name"));
                    preparedStatement.setString(3, resultSet.getString("description"));
                    preparedStatement.setInt(4, resultSet.getInt("country_id"));
                    preparedStatement.execute();
                }
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            System.err.printf("Terminating proces due to %s\n", throwables.getMessage());
            System.exit(2);
        }
    }
}
