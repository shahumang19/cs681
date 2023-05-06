package edu.umb.cs681.hw15;

public class ThreeDObserver implements Observer<StockEvent>{
    @Override
    public void update(Observable<StockEvent> o, StockEvent arg) {
        System.out.println(Thread.currentThread().getName()+ " - [3D Observer] - Ticker("+arg.ticker()+") - Quote("+arg.quote()+")");
    }
}
