package practicum;

import javafx.application.Application;
import javafx.stage.Stage;
import practicum.controllers.*;
import practicum.dao.AbstractDao;
import practicum.dao.database.CityDatabaseDao;
import practicum.dao.database.CountryDatabaseDao;
import practicum.models.AbstractModel;
import practicum.service.AbstractService;
import practicum.service.CityService;
import practicum.service.CountryService;
import practicum.utils.ApplicationConfiguration;
import practicum.views.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

import static java.lang.String.format;
import static practicum.utils.ApplicationConfiguration.usesPostgresDatabase;

/**
 * MainApplication: this is the starting point of the FX application and act as an application context.
 * In this class all singletons are being instantiated.
 */
public class MainApplication extends Application {

    public static final String TITLE = "Alleen samen krijgen we OOP2 eronder";

    public static final double WIDTH = 800;

    public static final double HEIGHT = 700;

    private Stage stage;

    Set<NavigatorView> views = new HashSet<>();

    Set<AbstractDao<?>> daos = new HashSet<>();

    Set<AbstractService<?>> services = new HashSet<>();

    Set<NavigatorController<?>> controllers = new HashSet<>();

    @Override
    public void start(Stage stage) {
        this.stage = stage;

        /* First instantiate views.
         * These are being retrieved from the MainApplication instance when controllers are being instantiated.
         * See: getViewByClass
         */
        views.addAll(Arrays.asList(
                new AboutView(),
                new CountryView(),
                new CitiesView(),
                new CopyView.Files()
        ));
        if (usesPostgresDatabase()) views.add(new CopyView.Database());

        /* First instantiate Dao's.
         * These are being retrieved from the MainApplication instance when services are being instantiated.
         * See: getDaoByClass
         */
        daos.addAll(Arrays.asList(
                new CountryDatabaseDao(),
                new CityDatabaseDao()
        ));

        /* First instantiate services's.
         * These are being retrieved from the MainApplication instance when controllers are being instantiated.
         * See: getServiceByClass
         *  Note: CopyDatabaseService is instantiated in the associated controller since it is not a generic services and uses two dao's.
         */
        services.addAll(Arrays.asList(
                new CountryService(this),
                new CityService(this)
        ));

        controllers.addAll(Arrays.asList(
                new AboutController(this),
                new CountryController(this),
                new CitiesController(this),
                new CopyFilesController(this))
        );
        // Note: CopyDatabaseService is instantiated in the associated controller since it is not a generic services and uses two dao's.
        if (usesPostgresDatabase()) controllers.add(new CopyDatabaseController(this));

        stage.setTitle(TITLE);

        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

        switchController(getControllerByClass(AboutController.class));

        stage.show();

        printConfiguration();

        System.out.printf("Entities will be stored either in a (Postgress/H2Db) database or file (dependent of the Dao implementation choosen).\n" +
                "Entity files (one entity per file) will be stored in %s.", ApplicationConfiguration.TEMP_DIR);
    }

    /* Note: this is an instance method to be able to test it */
    public void switchController(NavigatorController<?> controller) {
        stage.setScene(controller.getScene());
    }

    // Method searches for an instance of the specified class.
    public <V extends NavigatorView> V getViewByClass(Class<V> clazz) {
        return (V) views.stream().filter(v -> v.getClass().equals(clazz)).findFirst().orElse(null);
    }

    // Method searches for an generic instance using the specified model class.
    public <M extends AbstractModel> AbstractDao<M> getDaoByModelClass(Class<M> modelClass) {
        for (AbstractDao<?> dao : daos) {
            Type type = ((ParameterizedType) dao.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            if (type == modelClass) return (AbstractDao<M>) dao;
        }

        throw new IllegalStateException(format("No concrete instance of type %s<%s> found.", AbstractDao.class.getCanonicalName(), modelClass.getCanonicalName()));

    }

    // Method searches for an instance of the specified class.
    public <S extends AbstractService<?>> S getServiceByClass(Class<S> clazz) {
        return (S) services.stream().filter(s -> s.getClass().equals(clazz)).findFirst().orElse(null);
    }

    // Method searches for an instance of the specified class.
    public <C extends NavigatorController<?>> C getControllerByClass(Class<C> clazz) {
        return (C) controllers.stream().filter(c -> c.getClass().equals(clazz)).findFirst().orElse(null);
    }

    private void printConfiguration() {
        System.out.println("Configuration (singletons):");

        System.out.println("\nViews:");
        views.forEach(v -> System.out.printf("View: %s\n", v.getClass().getCanonicalName()));

        System.out.println("\nDao's:");
        daos.forEach(d -> System.out.printf("Dao: %s\n", d.getClass().getCanonicalName()));

        System.out.println("\nServices:");
        daos.forEach(s -> System.out.printf("Service: %s\n", s.getClass().getCanonicalName()));

        System.out.println("\nControllers:");
        daos.forEach(c -> System.out.printf("Controller: %s\n", c.getClass().getCanonicalName()));

        System.out.println();
    }
}
