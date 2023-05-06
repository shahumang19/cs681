package edu.umb.cs681.hw15;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class StockQuoteObservable extends Observable<StockEvent> {
    private Map<String, Double> hashmap = new HashMap<String,Double>();
    private ReentrantLock lockTQ = new ReentrantLock();

    public void changeQuote(String t, Double q){
        lockTQ.lock();
        try{
            hashmap.put(t, q);
        }finally{
            lockTQ.unlock();
        }
        notifyObservers(new StockEvent(t, q));
    }


    public Map<String,Double> getHashmap() {
        lockTQ.lock();
        try{
            return this.hashmap;
        }finally{
            lockTQ.unlock();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Stock Quote Observable");
    }
}
