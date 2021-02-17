package main.java.practicum;

import javafx.application.Application;

public class Main {

    public static void main(String[] args) {
        String password = System.getProperty("password");

        if(password == null || "".equals(password)) {
            System.err.println("Please specify a database password as a System property.");
            System.err.println("If executing a jar: java -Dpassword=<pwd> -jar <path_to_jar>");
            System.err.println("If executing from IntelliJ: add -Dpassword=<pwd> to the VM options of the corresponding run configuration.");
            System.exit(1);
        }

        Application.launch(practicum.MainApplication.class, args);
    }

}
