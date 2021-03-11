package practicum.controllers;

import practicum.models.AbstractModel;

public interface ModifyEntity<E extends AbstractModel> {

    void setControlledEntity(E entity);

}
