package edu.umb.cs681.hw19;

public class TableObserver implements Observer<StockEvent> {

    @Override
    public void update(Observable<StockEvent> o, StockEvent arg) {
        System.out.println(Thread.currentThread().getName()+ " - [Table Observer] - Ticker("+arg.ticker()+") - Quote("+arg.quote()+")");
    }
}