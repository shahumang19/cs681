package edu.umb.cs681.hw09;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class AircraftTest {
    @Test
    public void verifyAircraftSync(){
        Aircraft aircraft = new Aircraft(new Position(42.3601, -71.0589, 0));

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                Position pos = aircraft.getPosition();
                aircraft.setPosition(
                        pos.latitude() + 0.0001,
                        pos.longitude(),
                        pos.altitude()
                );
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                Position pos = aircraft.getPosition();
                aircraft.setPosition(
                        pos.latitude(),
                        pos.longitude() + 0.0001,
                        pos.altitude()
                );
            }
        });

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                Position pos = aircraft.getPosition();
                aircraft.setPosition(
                        pos.latitude(),
                        pos.longitude(),
                        pos.altitude() + 0.1
                );
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final position: " + aircraft.getPosition());

        Position expected = new Position(42.4601, -70.0589, 100);
        
        assertEquals(expected, aircraft.getPosition());
    }
}
