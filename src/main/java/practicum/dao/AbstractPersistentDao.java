package practicum.dao;

import org.h2.tools.Server;
import practicum.models.Country;

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

    public static final String POSTGRES_DATABASE_TYPE = "POSTGRES";

    public static final String H2_DATABASE_NAME = "h2db";

    public static final String H2_FILENAME = H2_DATABASE_NAME + ".mv.db";

    public static final String H2_FILEPATH = System.getProperty("user.home") + "/" + H2_FILENAME;

    protected Connection getConnection() throws SQLException, ClassNotFoundException {
        return POSTGRES_DATABASE_TYPE.equals(System.getProperty("databaseType")) ? getPostgresConnection() : getH2DbConnection();
    }

    private Connection getPostgresConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost/msmith?searchpath=sandbox";
        Properties props = new Properties();
        props.setProperty("user", "msmith");
        props.setProperty("password", System.getProperty("password"));

        return DriverManager.getConnection(url, props);
    }

    private Connection getH2DbConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/" + H2_DATABASE_NAME, "sa", "");
    }

    public abstract List<T> getAll();

    public abstract T getById(int id);

    public abstract void update(T entity);

    public abstract void persist(Country... countries);
}
