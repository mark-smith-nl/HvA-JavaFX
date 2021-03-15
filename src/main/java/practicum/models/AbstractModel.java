package practicum.models;

import java.io.Serializable;
import java.util.Objects;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public abstract class AbstractModel implements Serializable {

    // Note: An id of -1 marks an entity of being a new, not persisted entity.
    public static final int NEW_ID = -1;

    protected int id;

    public AbstractModel(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isNewEntity() {
        return id == NEW_ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractModel that = (AbstractModel) o;
        return id == that.id;
    }

    public void setId(int id) {
        if (this.id != NEW_ID) throw new IllegalStateException("Can not reset the id");
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
