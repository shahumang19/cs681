package edu.umb.cs681.hw02;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import org.junit.jupiter.api.*;

public class CarTest {

    @Test
    public void verifyCarAverage(){
        Car instance1 = new Car("Mercedes", "A-Class", 2022, 25, 35000.0F);
        Car instance2 = new Car("Mercedes", "A-Class", 2018, 40, 60000.0F);
        Car instance3 = new Car("Audi", "C-Class", 2021, 20, 30000.0F);
        Car instance4 = new Car("Toyota", "Fortuner", 2015, 10, 40000.0F);
        Car instance5 = new Car("BMW", "x5", 2012, 14, 55000.0F);
        Car instance6 = new Car("Ford", "Fusion", 2023, 30, 20000.0F);

        List<Car> cars = List.of(instance1, instance2, instance3, instance4, instance5, instance6);

        double expected = 40000.00;
        double actual = cars.stream()
                            .map(Car::getPrice).reduce(new CarPriceResultHolder(),
                            (result, price) -> {
                                                result.setAverage(
                                                    (result.getNumCarExamined() * result.getAverage() + price)/(result.getNumCarExamined()+1));
                                                result.setNumCarExamined(result.getNumCarExamined()+1);

                                                return result;},
                            (finalResult, intermediateResult)->finalResult).getAverage();
        
        assertEquals(expected, actual);
    }
}
