package edu.umb.cs681.hw06;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer {

    private ReentrantLock lock = new ReentrantLock();
    private boolean done = false;

    public RunnableCancellablePrimeFactorizer(long dividend, long from, long to) {
        super(dividend, from, to);
    }

    public void setDone(){ 
        this.lock.lock();
        try{
            this.done = true;
        }finally{ this.lock.unlock(); }
    }

    public void generatePrimeFactors() {
		long divisor = from;
	    while( dividend != 1 && divisor <= to ){
            this.lock.lock();
            try{
                if(this.done){
                    System.out.println("Stopped generating prime factors.");
                    this.factors.clear();
                    break;
                }

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
	    	
		}
	}
	
	public void run() {
		generatePrimeFactors();
		System.out.println("Thread #" + Thread.currentThread().getId() + " generated " + factors);
	}
    
}
