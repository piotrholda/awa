package awa.event;

public interface EventListener<E extends Event> {

    void accept(E event);

}
