package edu.umb.cs681.hw08;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellableInterruptiblePrimeFactorizer
	extends RunnableCancellablePrimeFactorizer{
	
	private boolean done = false;
	private final ReentrantLock lock = new ReentrantLock();
	
	public RunnableCancellableInterruptiblePrimeFactorizer(long dividend, long from, long to) {
		super(dividend, from, to);
	}
	
	public void setDone(){
		lock.lock();
		try {
			this.done = true;
		}
		finally {
			lock.unlock();
		}
	}

	public void generatePrimeFactors(){
		long divisor = from;

	    while(!done && dividend != 1 && divisor <= to ){
            this.lock.lock();
            try{
                if( divisor > 2 && isEven(divisor)) {
                    divisor++;
                    continue;
                }
                if(dividend % divisor == 0) {
                    factors.add(divisor);
                    dividend /= divisor;
                }else {
                    if(divisor==2){ divisor++; }
                    else{ divisor += 2; }
                }
            }finally{
                this.lock.unlock();
            }

			try {
				Thread.sleep(500);
			}catch(InterruptedException e) {
				System.out.println(e.toString());
				continue;
			}
	    	
		}
	}

	public void run() {
		this.generatePrimeFactors();
		System.out.println("Thread " + Thread.currentThread().getId() + " generated " + factors);
	}

	

	public static void main(String[] args) {
		System.out.println("Test1");
        RunnableCancellableInterruptiblePrimeFactorizer gen = new RunnableCancellableInterruptiblePrimeFactorizer(287, 2, 287);
		Thread t1 = new Thread(gen);
		t1.start();
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        gen.setDone();
        t1.interrupt();
        try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        LinkedList<Long> actual = gen.getPrimeFactors();
        
		actual.forEach((Long prime)-> System.out.print(prime + ", "));System.out.println();

		// ************

		System.out.println("Test2");
        RunnableCancellableInterruptiblePrimeFactorizer gen1 = new RunnableCancellableInterruptiblePrimeFactorizer(854, 2, (long)854L/2 );
        RunnableCancellableInterruptiblePrimeFactorizer gen2 = new RunnableCancellableInterruptiblePrimeFactorizer(854, 1+(long)854L/2, 854L );
        Thread tt1 = new Thread(gen1);
        Thread tt2 = new Thread(gen2);

        tt1.start();
        tt2.start();
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        gen1.setDone(); gen2.setDone();
        tt1.interrupt(); tt2.interrupt();
        try {
			tt1.join();tt2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
        
        LinkedList<Long> actual1 = new LinkedList<Long>();
        actual1.addAll(gen1.getPrimeFactors());
        actual1.addAll(gen2.getPrimeFactors());
        
		actual1.forEach((Long prime)-> System.out.print(prime + ", "));System.out.println();
	}

}
