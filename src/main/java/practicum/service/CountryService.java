package practicum.service;

import practicum.models.AbstractModel;
import practicum.models.Country;

import java.util.ArrayList;
import java.util.List;
import practicum.dao.CountryPersistentDao;

import static practicum.models.AbstractModel.*;

/**
 * Service servicing {@link Country} instances.
 *
 * @author m.smithhva.nl
 */
public class CountryService extends AbstractService<Country> {

    public CountryService() {
        super(new CountryPersistentDao());
    }

}
