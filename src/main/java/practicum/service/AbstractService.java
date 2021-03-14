package practicum.service;

import practicum.dao.AbstractPersistentDao;
import practicum.models.AbstractModel;

import java.sql.SQLException;
import java.util.List;

import static java.lang.String.format;
import static practicum.models.AbstractModel.NEW_ID;

/**
 * Abstract service servicing generic AbstractModel instances.
 *
 * @author m.smithhva.nl
 */
public abstract class AbstractService<M extends AbstractModel> {

    protected final AbstractPersistentDao<M> persistentDao;

    public AbstractService(AbstractPersistentDao<M> persistentDao) {
        this.persistentDao = persistentDao;
    }

    public List<M> getAll() {
        try {
            return persistentDao.getAll();
        } catch (SQLException throwables) {
            throw new IllegalStateException(throwables);
        }
    }

    public M getById(int id) {
        try {
            return persistentDao.getById(id);
        } catch (SQLException throwables) {
            throw new IllegalStateException(throwables);
        }
    }

    public void saveOrUpdate(M... entities) throws Exception {
        for (M entity : entities) {
            if (entity.getId() == NEW_ID) persistentDao.insert(entity);
            else persistentDao.update(entity);
        }
    }

    public void remove(M entity) {
        try {
            persistentDao.remove(entity);
        } catch (SQLException throwables) {
            throw new IllegalStateException(throwables);
        }
    }
}
