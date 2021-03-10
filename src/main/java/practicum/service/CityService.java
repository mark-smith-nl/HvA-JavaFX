package practicum.service;

import practicum.dao.CityPersistentDao;
import practicum.dao.CountryPersistentDao;
import practicum.models.City;
import practicum.models.Country;

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

    public List<City> getAll() {
        return persistentDao.getAll();
    }

    @Override
    public City getById(int id) {
        return persistentDao.getById(id);
    }

    @Override
    public void update(City entity) {
        persistentDao.update(entity);
    }

    public List<City> getForCountry(Country country) {
        return ((CityPersistentDao) persistentDao).getForCountry(country);
    }

}
