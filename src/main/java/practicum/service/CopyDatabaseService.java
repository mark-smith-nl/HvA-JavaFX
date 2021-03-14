package practicum.service;

import practicum.dao.CityPersistentDao;
import practicum.dao.CountryPersistentDao;
import practicum.utils.DatabaseConfiguration;

import java.io.File;
import java.sql.SQLException;

/**
 * Servive for copying a Postgres database to H2DB.
 *
 * @author m.smithhva.nl
 */
public class CopyDatabaseService {

    private final CountryPersistentDao countryPersistentDao;

    private final CityPersistentDao cityPersistentDao;

    public CopyDatabaseService() {
        countryPersistentDao = new CountryPersistentDao();
        cityPersistentDao = new CityPersistentDao();
    }

    public void start() {
        deleteH2DB();
        try {
            countryPersistentDao.initializeH2DbTable();
            countryPersistentDao.copyEntitiesFromPostgresToH2Db();
            cityPersistentDao.initializeH2DbTable();
            cityPersistentDao.copyEntitiesFromPostgresToH2Db();
            System.out.printf("Created file '%s'\n", DatabaseConfiguration.H2_FILEPATH);
        } catch (SQLException throwables) {
            throw new IllegalStateException("Can not copy database");
        }
    }

    private void deleteH2DB() {
        File h2Db = new File(DatabaseConfiguration.H2_FILEPATH);
        if (h2Db.delete())
            System.out.printf("Deleted file '%s'\n", DatabaseConfiguration.H2_FILEPATH);
        else
            System.out.printf("Failed to deleted file '%s'\n", DatabaseConfiguration.H2_FILEPATH);
    }

}
