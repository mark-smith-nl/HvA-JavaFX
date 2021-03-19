package practicum.service;

import practicum.MainApplication;
import practicum.dao.database.CityDatabaseDao;
import practicum.dao.database.CountryDatabaseDao;
import practicum.models.City;
import practicum.models.Country;
import practicum.utils.ApplicationConfiguration;

import java.io.File;
import java.sql.SQLException;

/**
 * Servive for copying a Postgres database to H2DB.
 *
 * @author m.smithhva.nl
 */
public class CopyDatabaseService {

    private final CountryDatabaseDao countryDatabaseDao;

    private final CityDatabaseDao cityDatabaseDao;

    public CopyDatabaseService(MainApplication mainApplication) {
        countryDatabaseDao = (CountryDatabaseDao) mainApplication.getDaoByModelClass(Country.class);
        cityDatabaseDao = (CityDatabaseDao) mainApplication.getDaoByModelClass(City.class);
    }

    public void start() {
        deleteH2DB();
        try {
            countryDatabaseDao.initializeH2DbTable();
            countryDatabaseDao.copyEntitiesFromPostgresToH2Db();
            cityDatabaseDao.initializeH2DbTable();
            cityDatabaseDao.copyEntitiesFromPostgresToH2Db();
            System.out.printf("Created file '%s'\n", ApplicationConfiguration.H2_FILEPATH);
        } catch (SQLException throwables) {
            throw new IllegalStateException("Can not copy database");
        }
    }

    private void deleteH2DB() {
        File h2Db = new File(ApplicationConfiguration.H2_FILEPATH);
        if (h2Db.delete())
            System.out.printf("Deleted file '%s'\n", ApplicationConfiguration.H2_FILEPATH);
        else
            System.out.printf("Failed to deleted file '%s'\n", ApplicationConfiguration.H2_FILEPATH);
    }

}
