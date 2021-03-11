package practicum.models;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public abstract class AbstractModel {

    protected final int id;

    public AbstractModel(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
