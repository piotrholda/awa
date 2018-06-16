package awa.eventsource;

import awa.event.EventFactory;
import awa.event.EventListener;
import awa.persistence.Entity;
import awa.persistence.Key;
import awa.persistence.Repository;

import java.util.Map;
import java.util.function.Consumer;

import static awa.eventsource.EntityRequestEvent.Type.DELETE;
import static awa.eventsource.EntityRequestEvent.Type.GET;
import static awa.eventsource.EntityRequestEvent.Type.SAVE;


public abstract class EwentSource<E extends Entity<K>, K extends Key> extends EventFactory<EntityStateEvent<E>> implements EventListener<EntityRequestEvent<E, K>> {

    private Repository<E, K> repository;

    private Map<EntityRequestEvent.Type, Consumer<EntityRequestEvent<E, K>>> eventConsumers = Map.of(
            SAVE, event -> repository.save(event.getEntity()),
            DELETE, event -> repository.delete(event.getKey()),
            GET, event -> {
                E entity = repository.get(event.getKey());
                EntityStateEvent.Builder<E> builder = new EntityStateEvent.Builder<>();
                EntityStateEvent<E> response = builder.entity(entity).build();
                notify(response);
            }
    );

    @Override
    public void accept(EntityRequestEvent<E, K> event) {

        Consumer<EntityRequestEvent<E, K>> eventConsumer = eventConsumers.get(event.getType());
        eventConsumer.accept(event);
    }

}
