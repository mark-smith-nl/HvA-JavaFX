package practicum.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public abstract class AbstractPersistentDao<T> {

    protected Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost/msmith";
        Properties props = new Properties();
        props.setProperty("user","msmith");
        props.setProperty("password",System.getProperty("password"));

        return DriverManager.getConnection(url, props);
    }

    public abstract List<T> getAll();

    public abstract void update(T entity);
}
