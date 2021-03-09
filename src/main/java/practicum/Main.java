package practicum;

import javafx.application.Application;
import practicum.dao.AbstractPersistentDao;

import java.io.*;

import static practicum.dao.AbstractPersistentDao.*;

public class Main {

    public static void main(String[] args) throws IOException {
        setDatabaseType(System.getProperty("databaseType"));
        setPassword(System.getProperty("password"));

        if(POSTGRES_DATABASE_TYPE.equals(getDatabaseType()) && (getPassword() == null || "".equals(getPassword()))) {
            System.err.println("Please specify a database password as a System property.");
            System.err.println("If executing a jar: java -Dpassword=<pwd> -jar <path_to_jar>");
            System.err.println("If executing from IntelliJ: add -Dpassword=<pwd> to the VM options of the corresponding run configuration.");
            System.exit(1);
        } else {
            System.out.printf("%s H2 database: %s ", extractH2DBIfNotExist() ? "Created" : "Found", H2_FILEPATH);
        }

        Application.launch(MainApplication.class, args);
    }

    static boolean extractH2DBIfNotExist() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File targetFile = new File(H2_FILEPATH);
        if (targetFile.exists()) {
            return false;
        }
        System.out.printf("Extracting H2 databae as: %s", H2_FILEPATH);
        try (InputStream inputStream = classLoader.getResourceAsStream(H2_FILENAME)) {
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            OutputStream outStream = new FileOutputStream(targetFile);
            outStream.write(buffer);
        } catch (IOException e) {
            System.exit(1);
        }

        return true;
    }

}
