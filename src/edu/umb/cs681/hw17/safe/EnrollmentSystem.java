package edu.umb.cs681.hw17.safe;

import java.util.concurrent.locks.ReentrantLock;

public class EnrollmentSystem {
    private final ReentrantLock lockA = new ReentrantLock();
    private final ReentrantLock lockB = new ReentrantLock();

    public void enrollForSubjectA(String student) {
        lockA.lock();
        try {
            System.out.println(student + " enrolled for subject A.");
            Thread.sleep(1000);
            lockB.lock();
            System.out.println(student + " enrolled for subject B.");
        } catch(InterruptedException e) {
            e.printStackTrace();
        }finally {
            lockA.unlock();
            if (lockB.isLocked()){
                lockB.unlock();
            }
        }
    }

    public void enrollForSubjectB(String student) {
        lockA.lock();
        try {
            System.out.println(student + " enrolled for subject A.");
            Thread.sleep(1000);
            lockB.lock();
            System.out.println(student + " enrolled for subject B.");
        } catch(InterruptedException e) {
            e.printStackTrace();
        }finally {
            lockA.unlock();
            if (lockB.isLocked()){
                lockB.unlock();
            }
        }
    }
}
