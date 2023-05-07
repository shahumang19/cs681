package edu.umb.cs681.hw16.safe;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class AuctionHandlerTest {
    @Test
    public void verifyMultipleRequestHandlers() throws InterruptedException{
        int threadCount = 5;
        Thread[] threads = new Thread[threadCount];
        AuctionHandler[] handlers = new AuctionHandler[threadCount];
        AuctionItem item = new AuctionItem(1, "Apple Iphone 1st Gen", 50000);

        for (int i = 0; i < threadCount; i++) {
            handlers[i] = new AuctionHandler(item, "User"+(i+1));
            threads[i] = new Thread(handlers[i]);
            threads[i].start();
        }

        Thread.sleep(5000);

        for (int i = 0; i < threadCount; i++) {
            handlers[i].setDone();
            threads[i].interrupt();
            threads[i].join();
        }

        System.out.println("Final Bid : "+item.getCurrentBid());
        
        for (int i = 0; i < threadCount; i++) {
            assertTrue(threads[i].getState() == Thread.State.TERMINATED);
        }
    }
}