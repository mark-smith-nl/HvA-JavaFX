package practicum.service;

import practicum.MainApplication;
import practicum.models.Country;

/**
 * Service servicing {@link Country} instances.
 *
 * @author m.smithhva.nl
 */
public class CountryService extends AbstractService<Country> {

    public CountryService(MainApplication mainApplication) {
        super(mainApplication, mainApplication.getDaoByModelClass(Country.class));
    }

}
