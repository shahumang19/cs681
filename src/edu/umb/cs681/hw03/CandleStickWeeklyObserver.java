package edu.umb.cs681.hw03;

public class CandleStickWeeklyObserver implements Observer<WkSummary> {

    @Override
    public void update(Observable<WkSummary> o, WkSummary arg) {
        System.out.println("[Candle Stick Weekly Observer] - (Open : "+arg.getOpen()+", High : "+arg.getHigh()+
                        ", Low : "+arg.getLow()+", Close : "+arg.getClose()+")");
    }
}