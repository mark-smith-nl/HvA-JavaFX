package practicum.service;

import practicum.dao.AbstractPersistentDao;
import practicum.dao.CityPersistentDao;
import practicum.dao.CountryPersistentDao;
import practicum.utils.Configuration;

import java.io.File;

/**
 * This method <description of functionality>
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
        countryPersistentDao.initializeH2DbTable();
        countryPersistentDao.copyEntitiesFromPostgresToH2Db();
        cityPersistentDao.initializeH2DbTable();
        cityPersistentDao.copyEntitiesFromPostgresToH2Db();
    }

    private void deleteH2DB() {
        File h2Db = new File(Configuration.H2_FILEPATH);
        if (h2Db.delete())
            System.out.printf("Deleted file '%s'\n", Configuration.H2_FILEPATH);
        else
            System.out.printf("Failed to deleted file '%s'\n", Configuration.H2_FILEPATH);
    }

}
