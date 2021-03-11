package practicum.dao;

import java.util.List;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public interface AbstractDao<T> {

    // Note the method load is not implemented: all entries are retrieved from a database.
    // A set of entries is not stored in the DAO's.
    List<T> getAll();

    T getById(int id);

    void update(T entity);

    void persist(List<T> entities);

}
