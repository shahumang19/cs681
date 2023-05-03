package edu.umb.cs681.hw09;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class AircraftTest {
    @Test
    public void verifyAircraftSync(){
        Aircraft aircraft = new Aircraft(new Position(0, 1, 2));

        Thread thread1 = new Thread(() -> {
            Position pos = aircraft.getPosition();
            aircraft.setPosition(
                pos.latitude() + 0.01,
                pos.longitude(),
                pos.altitude()
            );
        });

        Thread thread2 = new Thread(() -> {
            Position pos = aircraft.getPosition();
            System.out.println(Thread.currentThread().getName() + " Aircraft Position : " + pos);
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final position: " + aircraft.getPosition());

        Position expected = new Position(0.01, 1, 2);
        
        assertEquals(expected, aircraft.getPosition());
    }
}
