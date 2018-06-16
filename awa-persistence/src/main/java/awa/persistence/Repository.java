package awa.persistence;

import java.util.Optional;

public abstract class Repository<E extends Entity<K>, K extends Key> {

    public void save(E entity) {
        Optional.of(entity)
                .map(Entity::getKey)
                .ifPresentOrElse(o -> update(entity), () -> insert(entity));
    }

    abstract void insert(E entity);

    abstract void update(E entity);

    public abstract void delete(K key);

    public abstract E get(K key);

}
