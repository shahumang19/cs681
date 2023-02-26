package edu.umb.cs681.hw03;

import java.util.ArrayList;
import java.util.List;

public class DJIAWkSummaryObservable extends Observable<WkSummary> {
    private List<DSummary> dSummaryCollection;
    
    public DJIAWkSummaryObservable() {
        this.dSummaryCollection = new ArrayList<>();
    }

    public void addSummary(DSummary dSummary){
        this.dSummaryCollection.add(dSummary);

        if (this.dSummaryCollection.stream().count() == 5) {
            double open, close, high, low;
            open = this.dSummaryCollection.stream().findFirst().get().getOpen();
            close = this.dSummaryCollection.stream().reduce((first, second) -> second).get().getClose();
            high = this.dSummaryCollection.stream().mapToDouble(DSummary::getHigh).max().getAsDouble();
            low = this.dSummaryCollection.stream().mapToDouble(DSummary::getLow).min().getAsDouble();
            

            this.notifyObservers(new WkSummary(dSummary.getDate(), open, high, low, close));
            this.dSummaryCollection.clear();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("DJIA Quote Observable");
    }
}
