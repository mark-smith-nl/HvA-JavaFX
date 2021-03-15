package practicum.service;

import practicum.dao.CityDatabaseDao;
import practicum.models.City;
import practicum.models.Country;

import java.sql.SQLException;
import java.util.List;

/**
 * Service servicing {@link City} instances.
 *
 * @author m.smithhva.nl
 */
public class CityService extends AbstractService<City> {

    public CityService() {
        super(new CityDatabaseDao());
    }

    public List<City> getForCountry(Country country) {
        try {
            return ((CityDatabaseDao) persistentDao).getForCountry(country);
        } catch (SQLException throwables) {
            throw new IllegalStateException("Can not retrieve cities.", throwables);
        }
    }

}
