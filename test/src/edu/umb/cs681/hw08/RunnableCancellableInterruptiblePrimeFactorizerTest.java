package edu.umb.cs681.hw08;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RunnableCancellableInterruptiblePrimeFactorizerTest{

    private static List<Long> arrayToList(Integer... values) {
        List<Long> result = new ArrayList<Long>();
        for (int value : values) {
            result.add(Long.valueOf(value));
        }
        return result;
    }

    @Test
    public void generatePrimeFactorOf287() throws InterruptedException {
        RunnableCancellableInterruptiblePrimeFactorizer gen = new RunnableCancellableInterruptiblePrimeFactorizer(287, 2, 287);
		Thread t1 = new Thread(gen);
		t1.start();
        Thread.sleep(3000);
        gen.setDone();
        t1.interrupt();
        t1.join();
        
        LinkedList<Long> actual = gen.getPrimeFactors();
        
        List<Long> expected = arrayToList(7);
        assertEquals(expected, actual);
    }

    @Test
    public void generatePrimeFactorOf854WithMultipleThreads() throws InterruptedException {
        RunnableCancellableInterruptiblePrimeFactorizer gen1 = new RunnableCancellableInterruptiblePrimeFactorizer(854, 2, (long)854L/2 );
        RunnableCancellableInterruptiblePrimeFactorizer gen2 = new RunnableCancellableInterruptiblePrimeFactorizer(854, 1+(long)854L/2, 854L );
        Thread t1 = new Thread(gen1);
        Thread t2 = new Thread(gen2);

        t1.start();
        t2.start();
        Thread.sleep(1000);
        gen1.setDone(); gen2.setDone();
        t1.interrupt(); t2.interrupt();
        t1.join(); t2.join();
        
        LinkedList<Long> actual = new LinkedList<Long>();
        actual.addAll(gen1.getPrimeFactors());
        actual.addAll(gen2.getPrimeFactors());
        
        // Real output should be 2, 7, 61 but with interruption 2
        List<Long> expected = arrayToList(2);

        assertEquals(expected, actual);
        
    }
}