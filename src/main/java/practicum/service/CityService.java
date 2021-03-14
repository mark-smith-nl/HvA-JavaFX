package practicum.service;

import practicum.dao.CityPersistentDao;
import practicum.dao.CountryPersistentDao;
import practicum.models.City;
import practicum.models.Country;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class CityService extends AbstractService<City> {

    public CityService() {
        super(new CityPersistentDao());
    }

    public List<City> getForCountry(Country country) {
        try {
            return ((CityPersistentDao) persistentDao).getForCountry(country);
        } catch (SQLException throwables) {
            throw new IllegalStateException("Can not retrieve cities.", throwables);
        }
    }

}
