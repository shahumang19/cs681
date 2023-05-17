package edu.umb.cs681.hw17.deadlocked;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class EnrollmentSystemTest {
    
    @Test
    public void verifyMultipleRequestHandlers(){
        Student student1 = new Student("Alice");
        Student student2 = new Student("Bob");

        Subject cs681 = new Subject("CS681");
        Subject cs682 = new Subject("CS682");

        // Create threads for enrolling
        Thread thread1 = new Thread(() -> student1.enroll(cs681, cs682));
        Thread thread2 = new Thread(() -> student2.enroll(cs682, cs681));

        // Start the threads
        thread1.start();
        thread2.start();

        long startTime = System.currentTimeMillis();
        long timeout = 5000; // 5 seconds

        while (thread1.isAlive() || thread2.isAlive()) {
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

        assertTrue(thread1.getState() == Thread.State.WAITING);
        assertTrue(thread2.getState() == Thread.State.WAITING);
    }
}
