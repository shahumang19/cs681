package edu.umb.cs681.hw01;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.*;

public class CarTest {

    private static List<Car> cars;
    private static Car instance1, instance2, instance3, instance4, instance5, instance6;

    @BeforeAll
    public static void setUp(){
        instance1 = new Car("Mercedes", "A-Class", 2022, 25, 34000.0F);
        instance2 = new Car("Mercedes", "A-Class", 2018, 40, 68000.0F);
        instance3 = new Car("Audi", "C-Class", 2021, 20, 38000.0F);
        instance4 = new Car("Toyota", "Fortuner", 2015, 10, 45000.0F);
        instance5 = new Car("BMW", "x5", 2012, 14, 58000.0F);
        instance6 = new Car("Ford", "Fusion", 2023, 30, 25000.0F);

        cars = List.of(instance1, instance2, instance3, instance4, instance5, instance6);
        cars.stream().forEach(car -> car.setDominationCount(cars));
    }

    private String[] carToStringArray(Car car){
        String[] carString = {car.getMake(), car.getModel(), String.valueOf(car.getYear())};
        return carString;
    }

    //%%%%%%%%%%%%%%%%%%%% Mileage Based %%%%%%%%%%%%%%%%%%%%%%%%%%

    @Test
    public void verifyCarMileageSort(){
        List<Car> actual = cars.stream().sorted(Comparator.comparing(Car::getMileage)).toList();
        List<Car> expected = List.of(instance4, instance5, instance3, instance1, instance6, instance2);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void verifyMileageBasedHighPartition(){
        Map<Boolean, List<Car>> actual = cars.stream().sorted(
                                        Comparator.comparing(Car::getMileage)).collect(
                                        Collectors.partitioningBy((Car car) -> car.getMileage() > 20));

        List<Car> expected = List.of(instance1, instance6, instance2);
        assertArrayEquals(expected.toArray(), actual.get(true).toArray());
    }

    @Test
    public void verifyMileageBasedLowPartition(){
        Map<Boolean, List<Car>> actual = cars.stream().sorted(
                                        Comparator.comparing(Car::getMileage)).collect(
                                        Collectors.partitioningBy((Car car) -> car.getMileage() > 20));

        List<Car> expected = List.of(instance4, instance5, instance3);
        assertArrayEquals(expected.toArray(), actual.get(false).toArray());
    }

    @Test
    public void verifyMileageBasedHighPartitionStats(){
        double[] expected = {25, 40, 31.666666666666668, 3};
        Map<Boolean, List<Car>> partition = cars.stream().sorted(
                                        Comparator.comparing(Car::getMileage)).collect(
                                        Collectors.partitioningBy((Car car) -> car.getMileage() > 20));

        double min = partition.get(true).stream().mapToDouble(Car::getMileage).min().getAsDouble();
        double max = partition.get(true).stream().mapToDouble(Car::getMileage).max().getAsDouble();
        double average = partition.get(true).stream().mapToDouble(Car::getMileage).average().getAsDouble();
        double count = partition.get(true).stream().count();
        double[] actual = {min, max, average, count};
        
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifyMileageBasedLowPartitionStats(){
        double[] expected = {10, 20, 14.666666666666666, 3};
        Map<Boolean, List<Car>> partition = cars.stream().sorted(
                                        Comparator.comparing(Car::getMileage)).collect(
                                        Collectors.partitioningBy((Car car) -> car.getMileage() > 20));

        double min = partition.get(false).stream().mapToDouble(Car::getMileage).min().getAsDouble();
        double max = partition.get(false).stream().mapToDouble(Car::getMileage).max().getAsDouble();
        double average = partition.get(false).stream().mapToDouble(Car::getMileage).average().getAsDouble();
        double count = partition.get(false).stream().count();
        double[] actual = {min, max, average, count};
        
        assertArrayEquals(expected, actual);
    }

    //%%%%%%%%%%%%%%%%%%%% Price Based %%%%%%%%%%%%%%%%%%%%%%%%%%

    @Test
    public void verifyCarPriceSort(){
        List<Car> actual = cars.stream().sorted(Comparator.comparing(Car::getPrice)).toList();
        List<Car> expected = List.of(instance6, instance1, instance3, instance4, instance5, instance2);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void verifyPriceBasedHighPartition(){
        Map<Boolean, List<Car>> actual = cars.stream().sorted(
                                        Comparator.comparing(Car::getPrice)).collect(
                                        Collectors.partitioningBy((Car car) -> car.getPrice() > 50000.0F));

        List<Car> expected = List.of(instance5, instance2);
        assertArrayEquals(expected.toArray(), actual.get(true).toArray());
    }

    @Test
    public void verifyPriceBasedLowPartition(){
        Map<Boolean, List<Car>> actual = cars.stream().sorted(
                                        Comparator.comparing(Car::getPrice)).collect(
                                        Collectors.partitioningBy((Car car) -> car.getPrice() > 50000.0F));

        List<Car> expected = List.of(instance6, instance1, instance3, instance4);
        assertArrayEquals(expected.toArray(), actual.get(false).toArray());
    }

    @Test
    public void verifyPriceBasedHighPartitionStats(){
        double[] expected = {58000.0, 68000.0, 63000.0, 2};
        Map<Boolean, List<Car>> partition = cars.stream().sorted(
                                        Comparator.comparing(Car::getPrice)).collect(
                                        Collectors.partitioningBy((Car car) -> car.getPrice() > 50000.0F));

        double min = partition.get(true).stream().mapToDouble(Car::getPrice).min().getAsDouble();
        double max = partition.get(true).stream().mapToDouble(Car::getPrice).max().getAsDouble();
        double average = partition.get(true).stream().mapToDouble(Car::getPrice).average().getAsDouble();
        double count = partition.get(true).stream().count();
        double[] actual = {min, max, average, count};
        
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifyPriceBasedLowPartitionStats(){
        double[] expected = {25000.0, 45000.0, 35500.0, 4};
        Map<Boolean, List<Car>> partition = cars.stream().sorted(
                                        Comparator.comparing(Car::getPrice)).collect(
                                        Collectors.partitioningBy((Car car) -> car.getPrice() > 50000.0F));

        double min = partition.get(false).stream().mapToDouble(Car::getPrice).min().getAsDouble();
        double max = partition.get(false).stream().mapToDouble(Car::getPrice).max().getAsDouble();
        double average = partition.get(false).stream().mapToDouble(Car::getPrice).average().getAsDouble();
        double count = partition.get(false).stream().count();
        double[] actual = {min, max, average, count};
        
        assertArrayEquals(expected, actual);
    }

    //%%%%%%%%%%%%%%%%%%%% Year Based %%%%%%%%%%%%%%%%%%%%%%%%%%

    @Test
    public void verifyCarYearSort(){
        List<Car> actual = cars.stream().sorted(Comparator.comparing(Car::getYear)).toList();
        List<Car> expected = List.of(instance5, instance4, instance2, instance3, instance1, instance6);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void verifyYearBasedHighPartition(){
        Map<Boolean, List<Car>> actual = cars.stream().sorted(
                                        Comparator.comparing(Car::getYear)).collect(
                                        Collectors.partitioningBy((Car car) -> car.getYear() > 2015));

        List<Car> expected = List.of(instance2, instance3, instance1, instance6);
        assertArrayEquals(expected.toArray(), actual.get(true).toArray());
    }

    @Test
    public void verifyYearBasedLowPartition(){
        Map<Boolean, List<Car>> actual = cars.stream().sorted(
                                        Comparator.comparing(Car::getYear)).collect(
                                        Collectors.partitioningBy((Car car) -> car.getYear() > 2015));

        List<Car> expected = List.of(instance5, instance4);
        assertArrayEquals(expected.toArray(), actual.get(false).toArray());
    }

    @Test
    public void verifyYearBasedHighPartitionStats(){
        double[] expected = {2018, 2023, 2021.0, 4};
        Map<Boolean, List<Car>> partition = cars.stream().sorted(
                                        Comparator.comparing(Car::getYear)).collect(
                                        Collectors.partitioningBy((Car car) -> car.getYear() > 2015));

        double min = partition.get(true).stream().mapToDouble(Car::getYear).min().getAsDouble();
        double max = partition.get(true).stream().mapToDouble(Car::getYear).max().getAsDouble();
        double average = partition.get(true).stream().mapToDouble(Car::getYear).average().getAsDouble();
        double count = partition.get(true).stream().count();
        double[] actual = {min, max, average, count};
        
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifyYearBasedLowPartitionStats(){
        double[] expected = {2012, 2015, 2013.5, 2};
        Map<Boolean, List<Car>> partition = cars.stream().sorted(
                                        Comparator.comparing(Car::getYear)).collect(
                                        Collectors.partitioningBy((Car car) -> car.getYear() > 2015));

        double min = partition.get(false).stream().mapToDouble(Car::getYear).min().getAsDouble();
        double max = partition.get(false).stream().mapToDouble(Car::getYear).max().getAsDouble();
        double average = partition.get(false).stream().mapToDouble(Car::getYear).average().getAsDouble();
        double count = partition.get(false).stream().count();
        double[] actual = {min, max, average, count};
        
        assertArrayEquals(expected, actual);
    }

    //%%%%%%%%%%%%%%%%%%%% Domination Count Based %%%%%%%%%%%%%%%%%%%%%%%%%%

    @Test
    public void verifyCarParetoSort(){
        List<Car> actual = cars.stream().sorted(Comparator.comparing(Car::getDominationCount)).toList();
        List<Car> expected = List.of(instance2, instance6, instance1, instance3, instance4, instance5);
        System.out.println(actual);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void verifyParetoBasedHighPartition(){
        Map<Boolean, List<Car>> actual = cars.stream().sorted(
                                        Comparator.comparing(Car::getDominationCount)).collect(
                                        Collectors.partitioningBy((Car car) -> car.getDominationCount() > 1));
        System.out.println(actual);
        List<Car> expected = List.of(instance3, instance4, instance5);
        assertArrayEquals(expected.toArray(), actual.get(true).toArray());
    }

    @Test
    public void verifyParetoBasedLowPartition(){
        Map<Boolean, List<Car>> actual = cars.stream().sorted(
                                        Comparator.comparing(Car::getDominationCount)).collect(
                                        Collectors.partitioningBy((Car car) -> car.getDominationCount() > 1));

        List<Car> expected = List.of(instance2, instance6, instance1);
        assertArrayEquals(expected.toArray(), actual.get(false).toArray());
    }

    @Test
    public void verifyParetoBasedHighPartitionStats(){
        double[] expected = {2, 3, 2.6666666666666665, 3};
        Map<Boolean, List<Car>> partition = cars.stream().sorted(
                                        Comparator.comparing(Car::getDominationCount)).collect(
                                        Collectors.partitioningBy((Car car) -> car.getDominationCount() > 1));

        double min = partition.get(true).stream().mapToDouble(Car::getDominationCount).min().getAsDouble();
        double max = partition.get(true).stream().mapToDouble(Car::getDominationCount).max().getAsDouble();
        double average = partition.get(true).stream().mapToDouble(Car::getDominationCount).average().getAsDouble();
        double count = partition.get(true).stream().count();
        double[] actual = {min, max, average, count};
        
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifyParetoBasedLowPartitionStats(){
        double[] expected = {0, 1, 0.3333333333333333, 3};
        Map<Boolean, List<Car>> partition = cars.stream().sorted(
                                        Comparator.comparing(Car::getDominationCount)).collect(
                                        Collectors.partitioningBy((Car car) -> car.getDominationCount() > 1));

        double min = partition.get(false).stream().mapToDouble(Car::getDominationCount).min().getAsDouble();
        double max = partition.get(false).stream().mapToDouble(Car::getDominationCount).max().getAsDouble();
        double average = partition.get(false).stream().mapToDouble(Car::getDominationCount).average().getAsDouble();
        double count = partition.get(false).stream().count();
        double[] actual = {min, max, average, count};
        
        assertArrayEquals(expected, actual);
    }
}
