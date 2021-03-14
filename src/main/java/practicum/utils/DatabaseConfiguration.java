package practicum.utils;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class DatabaseConfiguration {
    private static final String POSTGRES_DATABASE_TYPE = "POSTGRES";

    private static String databaseType = "";

    private static String password = "";

    public static final String H2_DATABASE_NAME = "h2db";

    public static final String H2_FILENAME = H2_DATABASE_NAME + ".mv.db";

    public static final String H2_FILEPATH = System.getProperty("user.home") + "/" + H2_FILENAME;

    public static void setDatabaseType(String databaseType) {
        DatabaseConfiguration.databaseType = databaseType;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        DatabaseConfiguration.password = password;
    }

    public static boolean isPostgresDatabase() {
        return POSTGRES_DATABASE_TYPE.equals(databaseType);
    }

    public static boolean isDatabaseConfigured() {
        return !isPostgresDatabase() || (password != null && password.trim().length() > 1);
}

}
