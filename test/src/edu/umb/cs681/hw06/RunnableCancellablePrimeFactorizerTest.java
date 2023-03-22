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
    public void generatePrimeFactorOf287() throws InterruptedException {
        RunnableCancellablePrimeFactorizer gen = new RunnableCancellablePrimeFactorizer(287, 2, 287);
		Thread t1 = new Thread(gen);
		t1.start();
        t1.join();
        gen.setDone();
        
        LinkedList<Long> actual = gen.getPrimeFactors();

        List<Long> expected = arrayToList(7, 41);
        assertEquals(expected, actual);
    }

    @Test
    public void generatePrimeFactorOf854WithMultipleThreads() throws InterruptedException {
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
        GeneratorThreads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        LinkedList<Long> actual = new LinkedList<Long>();
        factorGenerators.forEach( (factorizer) -> actual.addAll(factorizer.getPrimeFactors()) );

        factorGenerators.forEach(generator -> generator.setDone());
        List<Long> expected = arrayToList(2, 7, 61);

        assertEquals(expected, actual);
    }

    @Test
    public void generatePrimeFactorOf512WithMultipleThreads() throws InterruptedException {
        List<RunnableCancellablePrimeFactorizer> factorGenerators;
        List<Thread> GeneratorThreads = new ArrayList<Thread>();

        factorGenerators = List.of(
            new RunnableCancellablePrimeFactorizer(512, 2, (long)512L/2 ),
		    new RunnableCancellablePrimeFactorizer(512, 1+(long)512L/2, 512L )
        );

        for (RunnableCancellablePrimeFactorizer generator: factorGenerators){
            GeneratorThreads.add(new Thread(generator));
        }

        GeneratorThreads.forEach(thread -> thread.start());
        GeneratorThreads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        LinkedList<Long> actual = new LinkedList<Long>();
        factorGenerators.forEach( (factorizer) -> actual.addAll(factorizer.getPrimeFactors()) );

        factorGenerators.forEach(generator -> generator.setDone());
        List<Long> expected = arrayToList(2, 2, 2, 2, 2, 2, 2, 2, 2);

        assertEquals(expected, actual);
    }
}