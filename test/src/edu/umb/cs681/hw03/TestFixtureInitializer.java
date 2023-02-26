package edu.umb.cs681.hw03;

import java.util.Comparator;
import java.util.List;
import java.io.IOException;
import java.nio.file.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestFixtureInitializer {
    public static List<DSummary> readCSV(String filepath){
        List<DSummary> dSummaryList = null;
        Path path = Paths.get(filepath);
        // try( Stream<String> lines = Files.lines(path) ){
        //     csv = lines.skip(1).map( line -> {
        //     return Stream.of( line.split(",") ).skip(1).map(value->Double.valueOf(value))
        //     .collect( Collectors.toList() ); }).collect( Collectors.toList());
        // } catch (IOException ex) {}

        try( Stream<String> lines = Files.lines(path) ){
            dSummaryList = lines.skip(1).map( line -> {
            return Stream.of( line.split(",") ).collect( Collectors.toList() ); })
            .map(line -> {
                try {
                    return new DSummary(new SimpleDateFormat("MM/dd/yyyy").parse(line.get(0)), Double.valueOf(line.get(1)), Double.valueOf(line.get(2)), Double.valueOf(line.get(3)), Double.valueOf(line.get(4)));
                } catch (NumberFormatException | ParseException e) {
                    e.printStackTrace();
                }
                return null;
            }).sorted(Comparator.comparing(DSummary::getDate))
            .collect( Collectors.toList());
        } catch (IOException ex) {}
        
        return dSummaryList;
    }
}