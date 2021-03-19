package practicum.models;

import java.io.Serializable;
import java.util.Objects;

/**
 * Entity (Pojo/Domain) class for storing business objects.
 * Domain classes have a unique identifier that does not change once the entity has been persisted.
 * New (unpersistent) entities have a recognizable id (NEW_ID) which signals the persistency layer to generate an id.
 *
 * @author m.smithhva.nl
 */
public abstract class AbstractModel implements Serializable {

    // Note: An id of -1 marks an entity of being a new, not persisted entity.
    public static final long NEW_ID = -1;

    protected long id;

    public AbstractModel(long id) {
        this.id = id;
    }

    public long getId() {
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

    // Note: This id will only be changed if the entity is a new entity. This is the responsibility of the persistency/service layer.
    public void setId(long id) {
        if (this.id != NEW_ID) throw new IllegalStateException("Can not reset the id");
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
