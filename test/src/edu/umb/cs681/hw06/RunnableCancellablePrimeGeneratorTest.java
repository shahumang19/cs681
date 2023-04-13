package edu.umb.cs681.hw06;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RunnableCancellablePrimeGeneratorTest{

    private static List<Long> arrayToList(Integer... values) {
        List<Long> result = new ArrayList<Long>();
        for (int value : values) {
            result.add(Long.valueOf(value));
        }
        return result;
    }

    @Test
    public void verifyThreadCancellation() throws InterruptedException {
        RunnableCancellablePrimeGenerator gen1 = new RunnableCancellablePrimeGenerator(1, 100);
		Thread t1 = new Thread(gen1);
		t1.start();
        gen1.setDone();
        t1.join();
        
        
        LinkedList<Long> actual = gen1.getPrimes();

        List<Long> expected = arrayToList();
        assertEquals(expected, actual);
    }

    @Test
    public void verifyThreadCancellationAfterSleep() throws InterruptedException {
        RunnableCancellablePrimeGenerator gen1 = new RunnableCancellablePrimeGenerator(1, 100);
		Thread t1 = new Thread(gen1);
		t1.start();
        Thread.sleep(100);
        gen1.setDone();
        t1.join();
        
        
        LinkedList<Long> actual = gen1.getPrimes();

        List<Long> expected = arrayToList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97);
        assertEquals(expected, actual);
    }

}