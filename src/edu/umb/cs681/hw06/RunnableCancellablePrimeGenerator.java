package edu.umb.cs681.hw06;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeGenerator extends RunnablePrimeGenerator {

    private ReentrantLock lock = new ReentrantLock();
    private boolean done = false;

    public RunnableCancellablePrimeGenerator(long from, long to) {
        super(from, to);
    }

    public void setDone(){ 
        this.lock.lock();
        try{
            this.done = true;
        }finally{ 
            this.lock.unlock(); 
        }
    }

    public void generatePrimes(){
        for(long n = from; n <= to; n++){
            this.lock.lock();
            try{
                if(this.done){
                    this.primes.clear();
                    break;
                }

                if(isPrime(n)){this.primes.add(n);}
            }finally{ 
                this.lock.unlock();
            }
        }
    }

    public void run(){
        generatePrimes(); 
    }
    
}
