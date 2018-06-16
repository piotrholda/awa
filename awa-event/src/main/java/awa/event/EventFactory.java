package awa.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class EventFactory<E extends Event> {

    private List<EventListener<E>> listeners = new ArrayList<>();

    public void register(EventListener<E> listener) {
        listeners.add(listener);
    }

    protected void notify(E event) {
        listeners.stream()
                .filter(Objects::nonNull)
                .forEach(l -> l.accept(event));
    }

    protected void notifyAsync(E event) {
        listeners.parallelStream()
                .filter(Objects::nonNull)
                .forEach(l -> l.accept(event));
    }
}
