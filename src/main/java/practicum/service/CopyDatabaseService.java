package practicum.service;

import practicum.dao.CityDatabaseDao;
import practicum.dao.CountryDatabaseDao;
import practicum.utils.PersistencyConfiguration;

import java.io.File;
import java.sql.SQLException;

/**
 * Servive for copying a Postgres database to H2DB.
 *
 * @author m.smithhva.nl
 */
public class CopyDatabaseService {

    private final CountryDatabaseDao countryPersistentDao;

    private final CityDatabaseDao cityPersistentDao;

    public CopyDatabaseService() {
        countryPersistentDao = new CountryDatabaseDao();
        cityPersistentDao = new CityDatabaseDao();
    }

    public void start() {
        deleteH2DB();
        try {
            countryPersistentDao.initializeH2DbTable();
            countryPersistentDao.copyEntitiesFromPostgresToH2Db();
            cityPersistentDao.initializeH2DbTable();
            cityPersistentDao.copyEntitiesFromPostgresToH2Db();
            System.out.printf("Created file '%s'\n", PersistencyConfiguration.H2_FILEPATH);
        } catch (SQLException throwables) {
            throw new IllegalStateException("Can not copy database");
        }
    }

    private void deleteH2DB() {
        File h2Db = new File(PersistencyConfiguration.H2_FILEPATH);
        if (h2Db.delete())
            System.out.printf("Deleted file '%s'\n", PersistencyConfiguration.H2_FILEPATH);
        else
            System.out.printf("Failed to deleted file '%s'\n", PersistencyConfiguration.H2_FILEPATH);
    }

}
