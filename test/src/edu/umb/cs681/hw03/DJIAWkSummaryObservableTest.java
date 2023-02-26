package edu.umb.cs681.hw03;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;


public class DJIAWkSummaryObservableTest {

    private static List<DSummary> dSummaryList;
    
    @BeforeAll
    public static void setUp(){
        dSummaryList = TestFixtureInitializer.readCSV("data/WeeklyPrices.csv");
    }
    
    @Test
	public void verifyDJIAQuotePeriodicalUpdate() {
		DJIAWkSummaryObservable observable = new DJIAWkSummaryObservable();
        CandleStickWeeklyObserver obs1 = new CandleStickWeeklyObserver();
        observable.addObserver(obs1);

        dSummaryList.stream().forEach(obj -> {
            observable.addSummary(obj);
        });

        observable.removeObserver(obs1);
	}
}
