package practicum.utils;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class ApplicationConfiguration {

    private static final String POSTGRES_DATABASE_TYPE = "POSTGRES";

    private static final String H2DB_DATABASE_TYPE = "H2DB";

    private static String databaseType = "";

    private static String password = "";

    public static final String H2_DATABASE_NAME = "h2db";

    public static final String H2_FILENAME = H2_DATABASE_NAME + ".mv.db";

    public static final String H2_FILEPATH = System.getProperty("user.home") + "/" + H2_FILENAME;

    public static final String TEMP_DIR = System.getProperty("java.io.tmpdir");

    public static void setDatabaseType(String databaseType) {
        ApplicationConfiguration.databaseType = databaseType;
    }

    public static String getDatabaseType() {
        return usesPostgresDatabase() ? POSTGRES_DATABASE_TYPE : H2DB_DATABASE_TYPE;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        ApplicationConfiguration.password = password;
    }

    public static boolean usesPostgresDatabase() {
        return POSTGRES_DATABASE_TYPE.equals(databaseType);
    }

    public static boolean isDatabaseConfigured() {
        return !usesPostgresDatabase() || (password != null && password.trim().length() > 1);
}

}
