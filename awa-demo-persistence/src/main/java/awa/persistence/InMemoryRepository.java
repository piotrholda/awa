package awa.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class InMemoryRepository<E extends Entity<K>, K extends Key> extends Repository<E, K> {

    private Supplier<K> keyGenerator;

    private enum Operation {
        INSERT,
        UPDATE,
        DELETE
    }

    private List<Change> changeLog = new ArrayList<>();

    private class Change {

        private Operation operation;
        private K key;
        private E entity;

        private K getKey() {
            return key;
        }

        private void setKey(K key) {
            this.key = key;
        }

        private Operation getOperation() {
            return operation;
        }

        private void setOperation(Operation operation) {
            this.operation = operation;
        }

        private E getEntity() {
            return entity;
        }

        private void setEntity(E entity) {
            this.entity = entity;
        }

    }

    private Map<Operation, Function<Change, E>> changeFunctions = Map.of(
            Operation.INSERT, change -> {
                E entity = change.getEntity();
                entity.setKey(change.getKey());
                return entity;
            },
            Operation.UPDATE, Change::getEntity,
            Operation.DELETE, change -> null
    );

    @Override
    void insert(E entity) {
        Change change = new Change();
        change.setOperation(Operation.INSERT);
        change.setKey(keyGenerator.get());
        change.setEntity(entity);
        changeLog.add(change);
    }

    @Override
    void update(E entity) {
        Change change = new Change();
        change.setOperation(Operation.UPDATE);
        change.setKey(entity.getKey());
        change.setEntity(entity);
        changeLog.add(change);
    }

    @Override
    public void delete(K key) {
        Change change = new Change();
        change.setOperation(Operation.UPDATE);
        change.setKey(key);
        changeLog.add(change);
    }

    @Override
    public E get(K key) {
        E entity = null;
        for (Change change: changeLog) {
            if (change.getKey().equals(key)) {
                entity = changeFunctions.get(change.getOperation()).apply(change);
            }
        }
        return entity;
    }

}
