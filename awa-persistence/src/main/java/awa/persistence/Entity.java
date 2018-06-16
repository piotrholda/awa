package awa.persistence;

public abstract class Entity<K extends Key> {

    private K key;

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

}
