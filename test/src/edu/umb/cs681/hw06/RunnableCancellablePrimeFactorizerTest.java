package edu.umb.cs681.hw06;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RunnableCancellablePrimeFactorizerTest{

    private static List<Long> arrayToList(Integer... values) {
        List<Long> result = new ArrayList<Long>();
        for (int value : values) {
            result.add(Long.valueOf(value));
        }
        return result;
    }

    @Test
    public void verifyPrimeFactorizerCancellation() throws InterruptedException {
        RunnableCancellablePrimeFactorizer gen = new RunnableCancellablePrimeFactorizer(287, 2, 287);
		Thread t1 = new Thread(gen);
		t1.start();
        gen.setDone();
        t1.join();
        
        LinkedList<Long> actual = gen.getPrimeFactors();

        List<Long> expected = arrayToList();
        assertEquals(expected, actual);
    }

    @Test
    public void verifyThreadCancellationAfterSleep() throws InterruptedException {
        List<RunnableCancellablePrimeFactorizer> factorGenerators;
        List<Thread> GeneratorThreads = new ArrayList<Thread>();

        factorGenerators = List.of(
            new RunnableCancellablePrimeFactorizer(854, 2, (long)854L/2 ),
		    new RunnableCancellablePrimeFactorizer(854, 1+(long)854L/2, 854L )
        );

        for (RunnableCancellablePrimeFactorizer generator: factorGenerators){
            GeneratorThreads.add(new Thread(generator));
        }

        GeneratorThreads.forEach(thread -> thread.start());
        Thread.sleep(100);
        factorGenerators.forEach(generator -> generator.setDone());
        GeneratorThreads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        LinkedList<Long> actual = new LinkedList<Long>();
        factorGenerators.forEach( (factorizer) -> actual.addAll(factorizer.getPrimeFactors()) );

        List<Long> expected = arrayToList(2, 7, 61);

        assertEquals(expected, actual);
    }
}