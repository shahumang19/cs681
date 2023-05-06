package edu.umb.cs681.hw15;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StockQuoteObservableTest {
    
    @Test
	public void verifyStockQuotePeriodicalUpdateThreads() {
		StockQuoteObservable observable = new StockQuoteObservable();
        TableObserver obs1 = new TableObserver();
        LineChartObserver obs2 = new LineChartObserver();
        ThreeDObserver obs3 = new ThreeDObserver();
        observable.addObserver(obs1);
        observable.addObserver(obs2);
        observable.addObserver(obs3);

        int numThreads = 15;
        Thread[] threads = new Thread[numThreads];
        DataHandler[] handlers = new DataHandler[numThreads];

        for (int i=0; i<numThreads; i++){
            handlers[i] = new DataHandler(observable, "Stock"+i);
            threads[i] = new Thread(handlers[i]);
            threads[i].start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("Main Thread Interrupted!!!");
        }

        for (int i=0; i<numThreads; i++){
            handlers[i].setDone();
            threads[i].interrupt();
        }
        
        for (int i=0; i<numThreads; i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.out.println("Thread Interrupted!!!");
            }
        }

        observable.removeObserver(obs1);
        observable.removeObserver(obs2);
        observable.removeObserver(obs3);

        for (int i=0; i<numThreads; i++){
            assertTrue(threads[i].getState()==Thread.State.TERMINATED);
        }
	}
}
