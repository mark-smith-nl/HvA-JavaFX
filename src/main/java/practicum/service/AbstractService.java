package practicum.service;

import practicum.MainApplication;
import practicum.dao.AbstractDao;
import practicum.dao.database.AbstractDatabaseDao;
import practicum.models.AbstractModel;

import java.util.List;

import static java.lang.String.format;
import static practicum.models.AbstractModel.NEW_ID;

/**
 * Abstract service servicing generic AbstractModel instances (i.e. domain/business entities).
 *
 * @author m.smithhva.nl
 */
public abstract class AbstractService<M extends AbstractModel> {

    protected final MainApplication mainApplication;

    protected final AbstractDao<M> dao;

    public AbstractService(MainApplication mainApplication, AbstractDao<M> dao) {
        this.mainApplication = mainApplication;
        this.dao = dao;
    }

    public List<M> getAll() {
        try {
            return dao.getAll();
        } catch (Exception throwables) {
            throw new IllegalStateException(throwables);
        }
    }

    public M getById(long id) {
        try {
            return dao.getById(id);
        } catch (Exception throwables) {
            throw new IllegalStateException(throwables);
        }
    }

    public void saveOrUpdate(M... entities) throws Exception {
        for (M entity : entities) {
            if (entity.getId() == NEW_ID) dao.insert(entity);
            else dao.update(entity);
        }
    }

    public void remove(M entity) {
        try {
            dao.remove(entity);
        } catch (Exception throwables) {
            throw new IllegalStateException(throwables);
        }
    }

}
