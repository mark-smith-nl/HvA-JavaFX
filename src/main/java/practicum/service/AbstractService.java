package practicum.service;

import practicum.dao.AbstractPersistentDao;
import practicum.models.Country;

import java.util.List;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public abstract class AbstractService<T> {

    protected final AbstractPersistentDao<T> persistentDao;

    public AbstractService(AbstractPersistentDao<T> persistentDao) {
        this.persistentDao = persistentDao;
    }

    public abstract List<T> getAll();

    public abstract void update(T entity);
}
