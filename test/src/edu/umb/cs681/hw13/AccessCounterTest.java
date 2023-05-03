package edu.umb.cs681.hw13;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class AccessCounterTest {
    
    @Test
    public void verifyMultipleRequestHandlers() throws InterruptedException{
        int threadCount = 20;
        Thread[] threads = new Thread[threadCount];
        RequestHandler[] handlers = new RequestHandler[threadCount];

        for (int i = 0; i < threadCount; i++) {
            handlers[i] = new RequestHandler();
            threads[i] = new Thread(handlers[i]);
            threads[i].start();
        }

        Thread.sleep(5000);

        for (int i = 0; i < threadCount; i++) {
            handlers[i].stop();
            threads[i].interrupt();
            threads[i].join();
        }

        for (int i = 0; i < threadCount; i++) {
            assertTrue(threads[i].isInterrupted());
        }
    }
}
