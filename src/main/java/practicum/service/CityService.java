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
        // TODO Implement fetching from database (or file) or any other resource
        List<City> cities = new ArrayList<>();

        for (int i=0; i<10; i++) {
            cities.add(new City(i, "Stad " + i + " in " + country.getName(), "Omschrijving", country));
        }
         return cities;
       // return ((CityPersistentDao) persistentDao).getForCountryID(country.getId());
    }
}
