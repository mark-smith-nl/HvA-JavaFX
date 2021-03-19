package practicum.dao.file;

import practicum.dao.file.AbstractPersistentObjectDao;
import practicum.models.City;

import java.io.*;
import java.sql.SQLException;

import static java.lang.String.format;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class CityPersistentObjectDao extends AbstractPersistentObjectDao<City> {

    @Override
    protected String getFileExtension() {
        return ".City";
    }

    @Override
    protected String getParentId(City entity) {
        return "_"+ entity.getCountry().getId();
    }

    public static void main(String[] args) throws SQLException, IOException {

    }
}
