package edu.umb.cs681.hw01;

import java.util.*;

import java.util.stream.Collectors;

public class main {
    public static void main(String[] args) {
        Car instance1 = new Car("Mercedes", "A-Class", 2022, 25, 34000.0F);
        Car instance2 = new Car("Mercedes", "A-Class", 2018, 40, 68000.0F);
        Car instance3 = new Car("Audi", "C-Class", 2021, 20, 38000.0F);
        Car instance4 = new Car("Toyota", "Fortuner", 2015, 10, 45000.0F);
        Car instance5 = new Car("BMW", "x5", 2012, 14, 58000.0F);
        Car instance6 = new Car("Ford", "Fusion", 2023, 30, 25000.0F);

        List<Car> carList = List.of(instance1, instance2, instance3, instance4, instance5, instance6);

        //%%%%%%%%%%%%%%%%%%%% Mileage Based %%%%%%%%%%%%%%%%%%%%%%%%%%

        Map<Boolean, List<Car>> mileageBasedList = carList.stream().sorted(
                                        Comparator.comparing(Car::getMileage)).collect(
                                        Collectors.partitioningBy((Car car) -> car.getMileage() > 20));

        System.out.println("Mileage Based Sorting");
        System.out.println("----------------------------");
        System.out.println("High group: ");
        mileageBasedList.get(true).forEach(System.out::println);System.out.println("\n");
        System.out.println("Min Mileage: " + mileageBasedList.get(true).stream().mapToInt(Car::getMileage).min().getAsInt());
        System.out.println("Max Mileage: " + mileageBasedList.get(true).stream().mapToInt(Car::getMileage).max().getAsInt());
        System.out.println("Average Mileage: " + mileageBasedList.get(true).stream().mapToInt(Car::getMileage).average().getAsDouble());
        System.out.println("Count Cars: " + mileageBasedList.get(true).stream().count() + "\n");

        System.out.println("Low group: ");
        mileageBasedList.get(false).forEach(System.out::println);System.out.println("\n");
        System.out.println("Min Mileage: " + mileageBasedList.get(false).stream().mapToInt(Car::getMileage).min().getAsInt());
        System.out.println("Max Mileage: " + mileageBasedList.get(false).stream().mapToInt(Car::getMileage).max().getAsInt());
        System.out.println("Average Mileage: " + mileageBasedList.get(false).stream().mapToInt(Car::getMileage).average().getAsDouble());
        System.out.println("Count Cars: " + mileageBasedList.get(false).stream().count()+"\n");


        //%%%%%%%%%%%%%%%%%%%% Price Based %%%%%%%%%%%%%%%%%%%%%%%%%%

        Map<Boolean, List<Car>> priceBasedList = carList.stream().sorted(
                                        Comparator.comparing(Car::getPrice)).collect(
                                        Collectors.partitioningBy((Car car) -> car.getPrice() > 50000.0F));

        System.out.println("Price Based Sorting");
        System.out.println("----------------------------");
        System.out.println("High group: ");
        priceBasedList.get(true).forEach(System.out::println);System.out.println("\n");
        System.out.println("Min Price: " + priceBasedList.get(true).stream().mapToDouble(Car::getPrice).min().getAsDouble());
        System.out.println("Max Price: " + priceBasedList.get(true).stream().mapToDouble(Car::getPrice).max().getAsDouble());
        System.out.println("Average Price: " + priceBasedList.get(true).stream().mapToDouble(Car::getPrice).average().getAsDouble());
        System.out.println("Count Cars: " + priceBasedList.get(true).stream().count()+ "\n");

        System.out.println("Low group: ");
        priceBasedList.get(false).forEach(System.out::println);System.out.println("\n");
        System.out.println("Min Price: " + priceBasedList.get(false).stream().mapToDouble(Car::getPrice).min().getAsDouble());
        System.out.println("Max Price: " + priceBasedList.get(false).stream().mapToDouble(Car::getPrice).max().getAsDouble());
        System.out.println("Average Price: " + priceBasedList.get(false).stream().mapToDouble(Car::getPrice).average().getAsDouble());
        System.out.println("Count Cars: " + priceBasedList.get(false).stream().count()+"\n");


        //%%%%%%%%%%%%%%%%%%%% Year Based %%%%%%%%%%%%%%%%%%%%%%%%%%

        Map<Boolean, List<Car>> yearBasedList = carList.stream().sorted(
                                        Comparator.comparing(Car::getYear)).collect(
                                        Collectors.partitioningBy((Car car) -> car.getYear() > 2015));

        System.out.println("Year Based Sorting");
        System.out.println("----------------------------");
        System.out.println("High group: ");
        yearBasedList.get(true).forEach(System.out::println);System.out.println("\n");
        System.out.println("Min Year: " + yearBasedList.get(true).stream().mapToInt(Car::getYear).min().getAsInt());
        System.out.println("Max Year: " + yearBasedList.get(true).stream().mapToInt(Car::getYear).max().getAsInt());
        System.out.println("Average Year: " + yearBasedList.get(true).stream().mapToDouble(Car::getYear).average().getAsDouble());
        System.out.println("Count Cars: " + yearBasedList.get(true).stream().count()+ "\n");

        System.out.println("Low group: ");
        yearBasedList.get(false).forEach(System.out::println);System.out.println("\n");
        System.out.println("Min Year: " + yearBasedList.get(false).stream().mapToInt(Car::getYear).min().getAsInt());
        System.out.println("Max Year: " + yearBasedList.get(false).stream().mapToInt(Car::getYear).max().getAsInt());
        System.out.println("Average Year: " + yearBasedList.get(false).stream().mapToDouble(Car::getYear).average().getAsDouble());
        System.out.println("Count Cars: " + yearBasedList.get(false).stream().count()+"\n");

        //%%%%%%%%%%%%%%%%%%%% Domination Count Based %%%%%%%%%%%%%%%%%%%%%%%%%%

        carList.stream().forEach(car -> car.setDominationCount(carList));
        // for(Car car: carList){
        //     car.setDominationCount(carList);
        // }

        Map<Boolean, List<Car>> dominationBasedList = carList.stream().sorted(
                                        Comparator.comparing(Car::getDominationCount)).collect(
                                        Collectors.partitioningBy((Car car) -> car.getDominationCount() > 1));

        System.out.println("Domination Count Based Sorting");
        System.out.println("----------------------------");
        System.out.println("High group: ");
        dominationBasedList.get(true).forEach(System.out::println);System.out.println("\n");
        System.out.println("Min Domination Count: " + dominationBasedList.get(true).stream().mapToInt(Car::getDominationCount).min().getAsInt());
        System.out.println("Max Domination Count: " + dominationBasedList.get(true).stream().mapToInt(Car::getDominationCount).max().getAsInt());
        System.out.println("Average Domination Count: " + dominationBasedList.get(true).stream().mapToDouble(Car::getDominationCount).average().getAsDouble());
        System.out.println("Count Cars: " + dominationBasedList.get(true).stream().count()+ "\n");

        System.out.println("Low group: ");
        dominationBasedList.get(false).forEach(System.out::println);System.out.println("\n");
        System.out.println("Min Domination Count: " + dominationBasedList.get(false).stream().mapToInt(Car::getDominationCount).min().getAsInt());
        System.out.println("Max Domination Count: " + dominationBasedList.get(false).stream().mapToInt(Car::getDominationCount).max().getAsInt());
        System.out.println("Average Domination Count: " + dominationBasedList.get(false).stream().mapToDouble(Car::getDominationCount).average().getAsDouble());
        System.out.println("Count Cars: " + dominationBasedList.get(false).stream().count()+"\n");

    }
}
