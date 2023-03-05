package edu.umb.cs681.hw04;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BostonHousingDataProcessingTest {
    private static List<BostonHousingData> dataset;
    
    @BeforeAll
    public static void setUp(){
        dataset = TestFixtureInitializer.readCSV("data/bos-housing.csv");
    }
    
    @Test
	public void verifyHousesNearCharlesRiverStats() {
        List<Double> expected = List.of(50.0, 5.0, 22.532806324110673);

        List<BostonHousingData> housesNearRiver = dataset.stream().filter(BostonHousingData::getChas).collect(Collectors.toList());
        Double high = housesNearRiver.stream().mapToDouble(BostonHousingData::getMedv).max().getAsDouble();
        Double low = housesNearRiver.stream().mapToDouble(BostonHousingData::getMedv).min().getAsDouble();
        Double average = housesNearRiver.stream().mapToDouble(BostonHousingData::getMedv).average().getAsDouble();
        List<Double> actual = List.of(high, low, average);

        assertArrayEquals(expected.toArray(), actual.toArray());
	}

    @Test
	public void verifytop10CrimeAndPTRatioPriceStats() {
        List<Double> expected = List.of(50.0, 24.1, 42.9800000000000043);

        List<BostonHousingData> lowCrimeRateBlocks = dataset.stream()
                                        .sorted(Comparator.comparing(BostonHousingData::getCrimeRate)) 
                                        .limit((int) (dataset.size() * 0.1)) 
                                        .collect(Collectors.toList());
        
        List<BostonHousingData> lowPtRatioBlocks = dataset.stream()
                                        .sorted(Comparator.comparing(BostonHousingData::getPtRatio)) 
                                        .limit((int) (dataset.size() * 0.1)) 
                                        .collect(Collectors.toList());

        List<BostonHousingData> top10CommonBlocks = lowCrimeRateBlocks.stream()
                                                    .filter(lowPtRatioBlocks::contains)
                                                    .collect(Collectors.toList()); 

        Double high = top10CommonBlocks.stream().mapToDouble(BostonHousingData::getMedv).max().getAsDouble();
        Double low = top10CommonBlocks.stream().mapToDouble(BostonHousingData::getMedv).min().getAsDouble();
        Double average = top10CommonBlocks.stream().mapToDouble(BostonHousingData::getMedv).average().getAsDouble();
        List<Double> actual = List.of(high, low, average);

        assertArrayEquals(expected.toArray(), actual.toArray());
	}

    @Test
	public void verifytop10CrimeAndPTRatioNoxStats() {
        List<Double> expected = List.of(0.422, 0.401, 0.41382);

        List<BostonHousingData> lowCrimeRateBlocks = dataset.stream()
                                        .sorted(Comparator.comparing(BostonHousingData::getCrimeRate)) 
                                        .limit((int) (dataset.size() * 0.1)) 
                                        .collect(Collectors.toList());
        
        List<BostonHousingData> lowPtRatioBlocks = dataset.stream()
                                        .sorted(Comparator.comparing(BostonHousingData::getPtRatio)) 
                                        .limit((int) (dataset.size() * 0.1)) 
                                        .collect(Collectors.toList());

        List<BostonHousingData> top10CommonBlocks = lowCrimeRateBlocks.stream()
                                                    .filter(lowPtRatioBlocks::contains)
                                                    .collect(Collectors.toList()); 

        Double high = top10CommonBlocks.stream().mapToDouble(BostonHousingData::getNox).max().getAsDouble();
        Double low = top10CommonBlocks.stream().mapToDouble(BostonHousingData::getNox).min().getAsDouble();
        Double average = top10CommonBlocks.stream().mapToDouble(BostonHousingData::getNox).average().getAsDouble();
        List<Double> actual = List.of(high, low, average);

        assertArrayEquals(expected.toArray(), actual.toArray());
	}

    @Test
	public void verifytop10CrimeAndPTRatioRoomStats() {
        List<Double> expected = List.of(7.923, 6.162, 7.4846);

        List<BostonHousingData> lowCrimeRateBlocks = dataset.stream()
                                        .sorted(Comparator.comparing(BostonHousingData::getCrimeRate)) 
                                        .limit((int) (dataset.size() * 0.1)) 
                                        .collect(Collectors.toList());
        
        List<BostonHousingData> lowPtRatioBlocks = dataset.stream()
                                        .sorted(Comparator.comparing(BostonHousingData::getPtRatio)) 
                                        .limit((int) (dataset.size() * 0.1)) 
                                        .collect(Collectors.toList());

        List<BostonHousingData> top10CommonBlocks = lowCrimeRateBlocks.stream()
                                                    .filter(lowPtRatioBlocks::contains)
                                                    .collect(Collectors.toList()); 

        Double high = top10CommonBlocks.stream().mapToDouble(BostonHousingData::getNumberOfRooms).max().getAsDouble();
        Double low = top10CommonBlocks.stream().mapToDouble(BostonHousingData::getNumberOfRooms).min().getAsDouble();
        Double average = top10CommonBlocks.stream().mapToDouble(BostonHousingData::getNumberOfRooms).average().getAsDouble();
        List<Double> actual = List.of(high, low, average);

        assertArrayEquals(expected.toArray(), actual.toArray());
	}

    @Test
	public void verifyCrimeRateAndHighwayRelation() {
        // Verifying correlation coefficient between Crime Rate and Accessibility to Highways
        double expectedCorrelation = 0.008368200400565315;

        double crMean = dataset.stream()
                        .mapToDouble(BostonHousingData::getCrimeRate)
                        .average().orElse(Double.NaN);

        double crStd = dataset.stream()
                        .mapToDouble(BostonHousingData::getCrimeRate)
                        .map(cr -> Math.pow(cr - crMean, 2))
                        .average().orElse(Double.NaN);

        double highwayMean = dataset.stream()
                        .mapToDouble(BostonHousingData::getAccessibilityToHighways)
                        .average().orElse(Double.NaN);

        double highwayStd = dataset.stream()
                        .mapToDouble(BostonHousingData::getAccessibilityToHighways)
                        .map(h -> Math.pow(h - highwayMean, 2))
                        .average().orElse(Double.NaN);
        
        double covariance = dataset.stream()
                            .mapToDouble(instance -> (instance.getCrimeRate() - crMean) * (instance.getAccessibilityToHighways() - highwayMean))
                            .average().orElse(Double.NaN);

        double actualCorrelation = covariance / (crStd * highwayStd);

        assertEquals(expectedCorrelation, actualCorrelation);
	}

    @Test
	public void verifyNewestHousesAndHighTaxCrimeStats() {
        // Identify blocks with top 20% newest houses and top 10% high tax houses.
        List<Double> expected = List.of(38.3518, 0.10574, 11.817223666666667);

        List<BostonHousingData> newestHouses = dataset.stream()
                                        .sorted(Comparator.comparing(BostonHousingData::getHouseAge).reversed()) 
                                        .limit((int) (dataset.size() * 0.2)) 
                                        .collect(Collectors.toList());
        
        List<BostonHousingData> highTaxHouses = dataset.stream()
                                        .sorted(Comparator.comparing(BostonHousingData::getTaxRate).reversed()) 
                                        .limit((int) (dataset.size() * 0.1)) 
                                        .collect(Collectors.toList());

        List<BostonHousingData> topCommonBlocks = newestHouses.stream()
                                                    .filter(highTaxHouses::contains)
                                                    .collect(Collectors.toList());

        Double high = topCommonBlocks.stream().mapToDouble(BostonHousingData::getCrimeRate).max().getAsDouble();
        Double low = topCommonBlocks.stream().mapToDouble(BostonHousingData::getCrimeRate).min().getAsDouble();
        Double average = topCommonBlocks.stream().mapToDouble(BostonHousingData::getCrimeRate).average().getAsDouble();
        List<Double> actual = List.of(high, low, average);

        assertArrayEquals(expected.toArray(), actual.toArray());
	}
}
