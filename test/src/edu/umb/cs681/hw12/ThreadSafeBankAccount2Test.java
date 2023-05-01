package edu.umb.cs681.hw12;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ThreadSafeBankAccount2Test {

    @Test
    public void verifyThreadInterruptionOnAwait() throws InterruptedException{
        int threadCount = 5;
        Thread[] depositThreads = new Thread[threadCount];
        Thread[] withdrawThreads = new Thread[threadCount];
        ThreadSafeBankAccount2 bankAccount = new ThreadSafeBankAccount2();
        DepositRunnable[] depositRunnables = new DepositRunnable[threadCount];
        WithdrawRunnable[] withdrawRunnables = new WithdrawRunnable[threadCount];

        for (int i = 0; i < threadCount; i++) {
            depositRunnables[i] = new DepositRunnable(bankAccount);
            withdrawRunnables[i] = new WithdrawRunnable(bankAccount);
            depositThreads[i] = new Thread(depositRunnables[i]);
            withdrawThreads[i] = new Thread(withdrawRunnables[i]);
            depositThreads[i].start();
            withdrawThreads[i].start();
        }

        Thread.sleep(1000);

        for (int i = 0; i < threadCount; i++) {
            depositRunnables[i].setDone();
            depositThreads[i].interrupt();
            withdrawRunnables[i].setDone();
            withdrawThreads[i].interrupt();
        }

        for (int i = 0; i < threadCount; i++) {
            try{
                depositThreads[i].join();
                
            } catch (InterruptedException e){
                System.out.println("Thread interrupted!!!");
            }
            try{
                withdrawThreads[i].join();
            } catch (InterruptedException e){
                System.out.println("Thread interrupted!!!");
            }
        }

        for (int i = 0; i < threadCount; i++) {
            assertTrue(depositThreads[i].getState() == Thread.State.TERMINATED);
            assertTrue(withdrawThreads[i].getState() == Thread.State.TERMINATED);
        }
    }
}
