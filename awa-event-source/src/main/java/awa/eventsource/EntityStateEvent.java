package awa.eventsource;

import awa.event.Event;
import awa.persistence.Entity;

public class EntityStateEvent<E extends Entity> implements Event {

    private E entity;

    private EntityStateEvent(EntityStateEvent.Builder<E> builder) {
        this.entity = builder.getEntity();
    }

    public E getEntity() {
        return entity;
    }

    public static class Builder<E extends Entity> {

        private E entity;

        EntityStateEvent<E> build() {
            return new EntityStateEvent<>(this);
        }

        public Builder<E> entity(E entity) {
            this.entity = entity;
            return this;
        }

        private E getEntity() {
            return entity;
        }

    }
}
