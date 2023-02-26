package edu.umb.cs681.hw03;

public class CandleStickMonthlyObserver implements Observer<MSummary> {

    @Override
    public void update(Observable<MSummary> o, MSummary arg) {
        System.out.println("[Candle Stick Monthly Observer] - (Open : "+arg.getOpen()+", High : "+arg.getHigh()+
                        ", Low : "+arg.getLow()+", Close : "+arg.getClose()+")");
        
    }
    
}
