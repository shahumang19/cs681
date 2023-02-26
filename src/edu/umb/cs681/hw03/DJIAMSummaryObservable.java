package edu.umb.cs681.hw03;

import java.time.LocalDate;
import java.util.*;

public class DJIAMSummaryObservable extends Observable<MSummary> {
    private List<DSummary> dSummaryCollection;
    
    public DJIAMSummaryObservable() {
        this.dSummaryCollection = new ArrayList<>();
    }

    public void addSummary(DSummary dSummary){
        this.dSummaryCollection.add(dSummary);

        if (this.dSummaryCollection.stream().count() == 20) {
            double open, close, high, low;
            open = this.dSummaryCollection.stream().findFirst().get().getOpen();
            close = this.dSummaryCollection.stream().reduce((first, second) -> second).get().getClose();
            high = this.dSummaryCollection.stream().mapToDouble(DSummary::getHigh).max().getAsDouble();
            low = this.dSummaryCollection.stream().mapToDouble(DSummary::getLow).min().getAsDouble();
            
            this.notifyObservers(new MSummary(dSummary.getDate(), open, high, low, close));
            this.dSummaryCollection.clear();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("DJIA Quote Observable");
    }
}
