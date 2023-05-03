package edu.umb.cs681.hw20;

import java.util.List;
import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestFixtureInitializer {
    public static List<BostonHousingData> readCSV(String filepath){
        List<BostonHousingData> dataset = null; 
        Path path = Paths.get(filepath);

        try( Stream<String> lines = Files.lines(path) ){
            // System.out.println(lines.toList().get(505));
            dataset = lines.skip(1).map( line -> {
            return Stream.of( line.split(",") ).collect( Collectors.toList() ); })
            .map(line -> {
                try {
                    return new BostonHousingData(Double.valueOf(line.get(0)), Double.valueOf(line.get(1)), Double.valueOf(line.get(2)), (line.get(3)=="0")?false:true, Double.valueOf(line.get(4)), Double.valueOf(line.get(5)), Double.valueOf(line.get(6)), Double.valueOf(line.get(7)), Integer.valueOf(line.get(8)), Double.valueOf(line.get(9)), Double.valueOf(line.get(10)), Double.valueOf(line.get(11)), Double.valueOf(line.get(12)), Double.valueOf(line.get(13)));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                return null;
            }).collect( Collectors.toList());
        } catch (IOException ex) {}
        
        return dataset;
    }
}