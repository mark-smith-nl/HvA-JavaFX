package practicum.service;

import practicum.models.Country;

import practicum.dao.CountryDatabaseDao;

/**
 * Service servicing {@link Country} instances.
 *
 * @author m.smithhva.nl
 */
public class CountryService extends AbstractService<Country> {

    public CountryService() {
        super(new CountryDatabaseDao());
    }

}
