package practicum.service;

import practicum.models.Country;

import java.util.ArrayList;
import java.util.List;
import practicum.dao.CountryPersistentDao;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class CountryService extends AbstractService<Country> {

    public CountryService() {
        super(new CountryPersistentDao());
    }

    public List<Country> getAll() {
        return persistentDao.getAll();
    }

    @Override
    public Country getById(int id) {
        return persistentDao.getById(id);
    }

    @Override
    public void update(Country entity) {
        persistentDao.update(entity);
    }

}
