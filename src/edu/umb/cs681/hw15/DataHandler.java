package edu.umb.cs681.hw15;

import java.util.concurrent.atomic.AtomicBoolean;

public class DataHandler implements Runnable {
    StockQuoteObservable observable;
    String stockName;
    private AtomicBoolean done = new AtomicBoolean(false);

    public DataHandler(StockQuoteObservable newObservable, String newStockName){
        observable = newObservable;
        stockName = newStockName;
    }

    public void setDone(){
        done.set(true);
    }

    @Override
    public void run() {
        double initialStockPrice = Math.random()*100;
        while(!done.get()){
            observable.changeQuote(stockName, initialStockPrice + Math.random());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName()+" interrupted!!!");
            }
        }
    }
    
}
