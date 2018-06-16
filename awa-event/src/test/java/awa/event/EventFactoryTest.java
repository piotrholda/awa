package awa.event;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(JUnit4.class)
public class EventFactoryTest {

    private class EventFactoryStub extends EventFactory<Event> {

        public String execute() {
            notify(event);
            return "Success!";
        }

        public String executeAsync() {
            notifyAsync(event);
            return "Success!";
        }

    }

    private class RuntimeExceptionStub extends RuntimeException {
    }

    private EventFactoryStub eventFactory;

    @Mock
    private Event event;

    @Mock
    private EventListener<Event> listener1, listener2;

    @Before
    public void setUp() {
        initMocks(this);
        eventFactory = new EventFactoryStub();
    }

    @Test
    public void notifyShouldNotifyListeners() {

        // given
        eventFactory.register(listener1);
        eventFactory.register(listener2);

        // when
        eventFactory.execute();

        // then
        verify(listener1, times(1)).accept(event);
        verify(listener2, times(1)).accept(event);
    }

    @Test(expected = RuntimeExceptionStub.class)
    public void notifyShouldRethrowException() {

        // given
        doThrow(new RuntimeExceptionStub()).when(listener1).accept(event);
        eventFactory.register(listener1);

        // when
        eventFactory.execute();

        // then
        fail();
    }

    @Test
    public void notifyShouldStopNotifyingAfterException() {

        // given
        doThrow(new RuntimeExceptionStub()).when(listener1).accept(event);
        eventFactory.register(listener1);
        eventFactory.register(listener2);

        // when
        try {
            eventFactory.execute();
        } catch (Exception e) {
        }

        // then
        verifyZeroInteractions(listener2);
    }

    @Test
    public void notifyAsyncShouldNotifyListeners() {

        // given
        eventFactory.register(listener1);
        eventFactory.register(listener2);

        // when
        eventFactory.executeAsync();

        // then
        verify(listener1, times(1)).accept(event);
        verify(listener2, times(1)).accept(event);
    }

    @Test(expected = RuntimeExceptionStub.class)
    public void notifyAsyncShouldRethrowException() {

        // given
        doThrow(new RuntimeExceptionStub()).when(listener1).accept(event);
        eventFactory.register(listener1);

        // when
        eventFactory.executeAsync();

        // then
        fail();
    }


    @Test
    public void notifyAsyncShouldKeepNotifyingDespiteException() {

        // given
        doThrow(new RuntimeExceptionStub()).when(listener1).accept(event);
        eventFactory.register(listener1);
        eventFactory.register(listener2);

        // when
        try {
            eventFactory.executeAsync();
        } catch (Exception e) {
        }

        // then
        verify(listener2, times(1)).accept(event);
    }


}