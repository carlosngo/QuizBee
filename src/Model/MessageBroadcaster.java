package Model;

import java.util.*;
import java.util.concurrent.locks.*;

public class MessageBroadcaster implements Runnable {
    private final Object message;
    private final Observable observable;
    private final Lock lock = new ReentrantLock();

    public MessageBroadcaster(Object message, Observable observable) {
        this.message = message;
        this.observable = observable;
    }

    @Override
    public void run() {
        lock.lock();
        try {
            observable.notifyObservers(message);
        } finally {
            lock.unlock();
        }
    }
}
