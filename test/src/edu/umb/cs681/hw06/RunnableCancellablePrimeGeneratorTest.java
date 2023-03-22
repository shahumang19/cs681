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
    public void generatePrimeFrom1to100() throws InterruptedException {
        RunnableCancellablePrimeGenerator gen1 = new RunnableCancellablePrimeGenerator(1, 100);
		Thread t1 = new Thread(gen1);
		t1.start();
        t1.join();
        gen1.setDone();
        
        LinkedList<Long> actual = gen1.getPrimes();

        List<Long> expected = arrayToList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97);
        assertEquals(expected, actual);
    }

    @Test
    public void generatePrimeMultiple() throws InterruptedException {
        int expected = 854;
        List<RunnableCancellablePrimeGenerator> primeGenerators;
        List<Thread> primeGeneratorThreads = new ArrayList<Thread>();

        primeGenerators = List.of(
            new RunnableCancellablePrimeGenerator(1, 10),       //4 prime numbers
            new RunnableCancellablePrimeGenerator(11, 40),      //8 prime numbers
            new RunnableCancellablePrimeGenerator(100, 200),    //21 prime numbers
            new RunnableCancellablePrimeGenerator(1, 1000),     //168 prime numbers
            new RunnableCancellablePrimeGenerator(55, 5000)     //653 prime numbers
        );

        for (RunnableCancellablePrimeGenerator generator: primeGenerators){
            primeGeneratorThreads.add(new Thread(generator));
        }

        primeGeneratorThreads.forEach(thread -> {
                                                thread.start();
                                                try {
                                                    thread.join();
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                            });

        primeGenerators.forEach(generator -> generator.setDone());
        Integer actual = primeGenerators.stream().mapToInt(generator -> generator.getPrimes().size()).reduce(0, (a, b) -> a + b);

        assertEquals(expected, actual);
    }
}