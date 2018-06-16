package awa.eventsource;

import awa.event.Event;
import awa.persistence.Entity;
import awa.persistence.Key;

public class EntityRequestEvent<E extends Entity<K>, K extends Key> implements Event {

    public enum Type {
        SAVE,
        DELETE,
        GET
    }

    private Type type;
    private E entity;
    private K key;

    private EntityRequestEvent(Builder<E, K> builder) {
        this.type = builder.getType();
        this.entity = builder.getEntity();
        this.key = builder.getKey();
    }

    public Type getType() {
        return type;
    }

    public E getEntity() {
        return entity;
    }

    public K getKey() {
        return key;
    }

    public static class Builder<E extends Entity<K>, K extends Key> {
        private Type type;
        private E entity;
        private K key;

        EntityRequestEvent<E, K> build() {
            return new EntityRequestEvent<>(this);
        }

        public void type(Type type) {
            this.type = type;
        }

        public void entity(E entity) {
            this.entity = entity;
        }

        public void key(K key) {
            this.key = key;
        }

        private Type getType() {
            return type;
        }

        private E getEntity() {
            return entity;
        }

        private K getKey() {
            return key;
        }
    }
}
