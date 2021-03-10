package practicum.dao;

import java.util.List;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public interface AbstractDao<T> {

    List<T> getAll();

    T getById(int id);

    void update(T entity);

    void persist(List<T> entities);

}
