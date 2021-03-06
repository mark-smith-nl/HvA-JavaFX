package practicum.dao.database;

import practicum.dao.AbstractDao;
import practicum.models.AbstractModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static practicum.utils.ApplicationConfiguration.*;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public abstract class AbstractDatabaseDao<M extends AbstractModel> implements AbstractDao<M> {

    protected Connection getConnection() {
        return usesPostgresDatabase() ? getPostgresConnection() : getH2DbConnection();
    }

    private static Connection getPostgresConnection() {
        String url = "jdbc:postgresql://localhost/msmith";
        Properties props = new Properties();
        props.setProperty("currentSchema", "sandbox");
        props.setProperty("user", "msmith");
        props.setProperty("password", getPassword());

        try {
            return DriverManager.getConnection(url, props);
        } catch (SQLException throwables) {
            throw new IllegalStateException(throwables);
        }
    }

    protected static Connection getH2DbConnection() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }

        try {
            return DriverManager.getConnection("jdbc:h2:~/" + H2_DATABASE_NAME, "sa", "");
        } catch (SQLException throwables) {
            throw new IllegalStateException(throwables);
        }

    }

    public abstract void initializeH2DbTable() throws SQLException;

    public abstract void copyEntitiesFromPostgresToH2Db() throws SQLException;
}

