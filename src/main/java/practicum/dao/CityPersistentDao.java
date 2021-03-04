package practicum.dao;

import practicum.models.City;
import practicum.models.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class CityPersistentDao extends AbstractPersistentDao<City> {

    @Override
    public List<City> getAll() {
        List<City> cities = new ArrayList<>();
        // TODO Implement
        return cities;
    }

    public List<City> getForCountryID(int id) {
        List<City> cities = new ArrayList<>();
        // TODO Implement
        return cities;
    }

    @Override
    public City getById(int id) {
        return null;
    }

    @Override
    public void update(City entity) {
        // TODO Implement
    }

    @Override
    public void persist(Country... countries) {
        // TODO Implement
    }
}
