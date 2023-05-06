package edu.umb.cs681.hw15;

public class LineChartObserver implements Observer<StockEvent> {

    @Override
    public void update(Observable<StockEvent> o, StockEvent arg) {
        System.out.println(Thread.currentThread().getName()+ " - [Line Chart Observer] - Ticker("+arg.ticker()+") - Quote("+arg.quote()+")");
    }
}