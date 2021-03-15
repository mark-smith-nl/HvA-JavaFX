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
public class CountryDatabaseDao extends AbstractDatabaseDao<Country> {

    @Override
    public List<Country> getAll() throws SQLException {
        List<Country> entities = new ArrayList<>();

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            String selectSql = "SELECT country_id as id, name FROM countries order by name";
            try (ResultSet resultSet = statement.executeQuery(selectSql)) {
                while (resultSet.next()) entities.add(new Country(resultSet.getInt("id"), resultSet.getString("name")));
            }
        }

        return entities;
    }

    @Override
    public Country getById(int id) throws SQLException {
        Country entity = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT country_id as id, name, code, description, founded, eumember FROM countries where country_id = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
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

        return entity;
    }

    @Override
    public void update(Country entity) throws SQLException {
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
        }
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void insert(Country entity) throws SQLException {
        try (Connection connection = getConnection();) {
            String[] key = new String[]{"country_id"};
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO countries(name, code, description) VALUES(?, ?, ?)", key);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getCode());
            preparedStatement.setString(3, entity.getDescription());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) entity.setId(generatedKeys.getInt(1));
        }
    }

    @Override
    public void remove(Country entity) throws SQLException {
        try (Connection connection = getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE from countries where country_id=?");
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.execute();
        }
    }

    @Override
    public void initializeH2DbTable() throws SQLException {
        try (Connection connection = getH2DbConnection()) {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE countries " +
                    "(country_id integer auto_increment not NULL primary key, " +
                    " name VARCHAR(64), " +
                    " code VARCHAR(2), " +
                    " description VARCHAR(1024), " +
                    " founded DATE, " +
                    " eumember BOOLEAN DEFAULT false, " +
                    " PRIMARY KEY (country_id ))";
            statement.executeUpdate(sql);
        }
    }

    @Override
    public void copyEntitiesFromPostgresToH2Db() throws SQLException {
        try (Connection connection = getConnection();
             Connection h2DbConnection = getH2DbConnection()) {
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
        }
    }
}
