package practicum.dao;

import org.h2.tools.Server;
import practicum.models.Country;
import practicum.utils.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import static practicum.utils.Configuration.*;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public abstract class AbstractPersistentDao<T> implements AbstractDao<T> {

    protected Connection getConnection() throws SQLException, ClassNotFoundException {
        return isPostgresDatabase() ? getPostgresConnection() : getH2DbConnection();
    }

    private static Connection getPostgresConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost/msmith";
        Properties props = new Properties();
        props.setProperty("currentSchema", "sandbox");
        props.setProperty("user", "msmith");
        props.setProperty("password", getPassword());

        return DriverManager.getConnection(url, props);
    }

    protected static Connection getH2DbConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/" + H2_DATABASE_NAME, "sa", "");
    }

    public abstract void initializeH2DbTable();


    //TODO set sequence to highest id
    public abstract void copyEntitiesFromPostgresToH2Db();
}

