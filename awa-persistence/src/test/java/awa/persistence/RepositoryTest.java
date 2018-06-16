package awa.persistence;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Spy;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(JUnit4.class)
public class RepositoryTest {

    @Spy
    private Repository<Entity<Key>, Key> repository = new Repository<>() {

        @Override
        void insert(Entity entity) {

        }

        @Override
        void update(Entity entity) {

        }

        @Override
        public void delete(Key key) {

        }

        @Override
        public Entity<Key> get(Key key) {
            return null;
        }
    };

    @Mock
    private Entity<Key> entity;

    @Mock
    private Key key;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void shouldInsertEntityWhenNoKey() {

        // when
        repository.save(entity);

        // then
        verify(repository, times(1)).insert(entity);
    }

    @Test
    public void shouldUpdateEntityWhenHasKey() {

        // given
        when(entity.getKey()).thenReturn(key);

        // when
        repository.save(entity);

        // then
        verify(repository, times(1)).update(entity);
    }

}
