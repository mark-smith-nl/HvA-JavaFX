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

    private static  String databaseType = "";

    private static  String password = "";

    public static final String H2_DATABASE_NAME = "h2db";

    public static final String H2_FILENAME = H2_DATABASE_NAME + ".mv.db";

    public static final String H2_FILEPATH = System.getProperty("user.home") + "/" + H2_FILENAME;

    protected Connection getConnection() throws SQLException, ClassNotFoundException {
        return POSTGRES_DATABASE_TYPE.equals(databaseType) ? getPostgresConnection() : getH2DbConnection();
    }

    private Connection getPostgresConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost/msmith";
        Properties props = new Properties();
        props.setProperty("currentSchema", "sandbox");
        props.setProperty("user", "msmith");
        props.setProperty("password", password);

        return DriverManager.getConnection(url, props);
    }

    private Connection getH2DbConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/" + H2_DATABASE_NAME, "sa", "");
    }

    public static String getDatabaseType() {
        return databaseType;
    }

    public static void setDatabaseType(String databaseType) {
        AbstractPersistentDao.databaseType = databaseType;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        AbstractPersistentDao.password = password;
    }

    public abstract List<T> getAll();

    public abstract T getById(int id);

    public abstract void update(T entity);

    public abstract void persist(Country... countries);
}
