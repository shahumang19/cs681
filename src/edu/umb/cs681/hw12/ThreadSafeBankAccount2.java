package edu.umb.cs681.hw12;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ThreadSafeBankAccount2 implements BankAccount{
	private double balance = 0;
	private ReentrantLock lock = new ReentrantLock();
	private Condition sufficientFundsCondition = lock.newCondition();
	private Condition belowUpperLimitFundsCondition = lock.newCondition();
	
	public void deposit(double amount){
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().threadId() + 
					" (d): current balance: " + balance);
			while(balance >= 300){
				System.out.println(Thread.currentThread().threadId() + 
						" (d): await(): Balance exceeds the upper limit.");
				belowUpperLimitFundsCondition.await();
			}
			balance += amount;
			System.out.println(Thread.currentThread().threadId() + 
					" (d): new balance: " + balance + "" + System.currentTimeMillis());
			sufficientFundsCondition.signalAll();
		}
		catch (InterruptedException exception){
			exception.printStackTrace();
		}
		finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}
	
	public void withdraw(double amount){
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().threadId() + 
					" (w): current balance: " + balance);
			while(balance <= 0){
				System.out.println(Thread.currentThread().threadId() + 
						" (w): await(): Insufficient funds");
				sufficientFundsCondition.await();
			}
			balance -= amount;
			System.out.println(Thread.currentThread().threadId() + 
					" (w): new balance: " + balance + "" + System.currentTimeMillis());
			belowUpperLimitFundsCondition.signalAll();
		}
		catch (InterruptedException exception){
			exception.printStackTrace();
		}
		finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}

	public double getBalance() { return this.balance; }

	public static void main(String[] args){
		int threadCount = 2;
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

        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
	}
}
