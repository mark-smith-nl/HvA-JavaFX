package practicum.dao.file;

import practicum.dao.file.AbstractPersistentObjectDao;
import practicum.models.Country;

import static java.lang.String.format;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class CountryPersistentObjectDao extends AbstractPersistentObjectDao<Country> {

    @Override
    protected String getFileExtension() {
        return ".Country";
    }


}
