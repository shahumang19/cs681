package edu.umb.cs681.hw14;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class AdmissionMonitorTest {
    @Test
    public void verifyVisitorHandlers() throws InterruptedException {
        int threadCount = 3;
        Thread[] threads = new Thread[threadCount];
        AdmissionMonitor monitor = new AdmissionMonitor();
        EntranceHandler entranceHandler = new EntranceHandler(monitor);
        ExitHandler exitHandler = new ExitHandler(monitor);
        StatsHandler statsHandler = new StatsHandler(monitor);

        threads[0] = new Thread(entranceHandler);
        threads[1] = new Thread(exitHandler);
        threads[2] = new Thread(statsHandler);

        for (int i = 0; i < threadCount; i++) {
            threads[i].start();
        }

        Thread.sleep(3000);
        entranceHandler.setDone();threads[0].interrupt();
        exitHandler.setDone();threads[1].interrupt();
        statsHandler.setDone();threads[2].interrupt();
        

        for (int i = 0; i < threadCount; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < threadCount; i++) {
            assertTrue(threads[i].getState() == Thread.State.TERMINATED);
        }
    }
}
