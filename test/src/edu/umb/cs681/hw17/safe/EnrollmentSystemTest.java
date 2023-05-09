package edu.umb.cs681.hw17.safe;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class EnrollmentSystemTest {
    
    @Test
    public void verifyMultipleRequestHandlers(){
        EnrollmentSystem system = new EnrollmentSystem();

        Thread student1 = new Thread(() -> {
            system.enrollForSubjectA("Student1");
        });

        Thread student2 = new Thread(() -> {
            system.enrollForSubjectB("Student2");
        });

        student1.start();
        student2.start();

        long startTime = System.currentTimeMillis();
        long timeout = 5000; // 5 seconds

        while (student1.isAlive() || student2.isAlive()) {
            if (System.currentTimeMillis() - startTime > timeout) {
                System.out.println("Deadlock Occurred. Timeout reached. Exiting program.");
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        assertTrue(student1.getState() == Thread.State.TERMINATED);
        assertTrue(student2.getState() == Thread.State.TERMINATED);
    }
}
