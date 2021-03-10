package practicum.dao;

import practicum.models.Country;

import java.sql.*;
import java.time.LocalDate;
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
        List<Country> entities = new ArrayList<>();

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            String selectSql = "SELECT country_id as id, name FROM countries order by name";
            try (ResultSet resultSet = statement.executeQuery(selectSql)) {
                while (resultSet.next()) entities.add(new Country(resultSet.getInt("id"), resultSet.getString("name")));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            System.err.printf("Terminating proces due to %s\n", throwables.getMessage());
            System.exit(2);
        }

        return entities;
    }

    @Override
    public Country getById(int id) {
        Country entity = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT country_id as id, name, code, description, founded, eumember FROM countries where country_id = ?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    if (entity != null) throw new IllegalStateException("Multiple entries found");
                    Date date = resultSet.getDate("founded");

                    entity = new Country(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("code"),
                            resultSet.getString("description"),
                            date == null ? null : date.toLocalDate(),
                            resultSet.getBoolean("eumember"));
                }
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throw new IllegalStateException(throwables.getMessage());
        }

        return entity;
    }

    @Override
    public void update(Country entity) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE countries SET name = ?, code = ?, description = ?, founded=?, eumember=?  WHERE country_id = ?")) {
            LocalDate date = entity.getFounded();
            Date founded = date == null ? null : java.sql.Date.valueOf(date);

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getCode());
            preparedStatement.setString(3, entity.getDescription());
            preparedStatement.setDate(4, founded);
            preparedStatement.setBoolean(5, entity.isEUMumber());
            preparedStatement.setInt(6, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throw new IllegalStateException(throwables.getMessage());
        }
    }

    @Override
    public void persist(List<Country> entities) {
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

    @Override
    public void initializeH2DbTable() {
        try (Connection connection = getH2DbConnection()) {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE countries " +
                    "(country_id INTEGER not NULL, " +
                    " name VARCHAR(64), " +
                    " code VARCHAR(2), " +
                    " description VARCHAR(1024), " +
                    " founded DATE, " +
                    " eumember BOOLEAN DEFAULT false, " +
                    " PRIMARY KEY (country_id ))";
            statement.executeUpdate(sql);
        } catch (SQLException | ClassNotFoundException throwables) {
            throw new IllegalStateException(throwables.getMessage());
        }
    }

    @Override
    public void copyEntitiesFromPostgresToH2Db() {
        try (Connection connection = getConnection(); Connection h2DbConnection = getH2DbConnection()) {
            Statement statement = connection.createStatement();
            String selectSql = "SELECT country_id, name, code, description, founded, eumember FROM countries";
            try (ResultSet resultSet = statement.executeQuery(selectSql)) {
                while (resultSet.next()) {
                    PreparedStatement preparedStatement = h2DbConnection.prepareStatement("INSERT INTO countries(country_id, name, code, description, founded, eumember) " +
                            "VALUES(?, ?, ?, ?, ?, ?)");
                    preparedStatement.setInt(1, resultSet.getInt("country_id"));
                    preparedStatement.setString(2, resultSet.getString("name"));
                    preparedStatement.setString(3, resultSet.getString("code"));
                    preparedStatement.setString(4, resultSet.getString("description"));
                    preparedStatement.setDate(5, resultSet.getDate("founded"));
                    preparedStatement.setBoolean(6, resultSet.getBoolean("eumember"));
                    preparedStatement.execute();
                }
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            System.err.printf("Terminating proces due to %s\n", throwables.getMessage());
            System.exit(2);
        }
    }
}
