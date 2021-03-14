package practicum.dao;

import practicum.models.AbstractModel;

import java.sql.SQLException;
import java.util.List;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public interface AbstractDao<M extends AbstractModel> {

    // Note the method load is not implemented: all entries are retrieved from a database.
    // A set of entries is not stored in the DAO's.
    List<M> getAll() throws SQLException;

    M getById(int id) throws SQLException;

    void update(M entity) throws SQLException;

    void insert(M entity) throws SQLException;

    void remove(M entity) throws SQLException;
}
