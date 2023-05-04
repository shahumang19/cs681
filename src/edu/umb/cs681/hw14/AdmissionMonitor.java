package edu.umb.cs681.hw14;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AdmissionMonitor {
    private int currentVisitors = 0;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private Condition visitorGreaterThanTenCondition = lock.writeLock().newCondition();
    private Condition zeroVisitorCondition = lock.writeLock().newCondition();

    public void enter(){
        lock.writeLock().lock();
        try{
            while (currentVisitors > 10){
                System.out.println(Thread.currentThread().getName() + 
                " : await(): Too many visitors. Please wait for a while.");
                visitorGreaterThanTenCondition.await();
            }
            currentVisitors++;
            zeroVisitorCondition.signalAll();
        }catch (InterruptedException exception){
			exception.printStackTrace();
		}finally{
            lock.writeLock().unlock();
        }
    }

    public void exit(){
        lock.writeLock().lock();
        try{
            while (currentVisitors == 0){
                System.out.println(Thread.currentThread().getName() + 
                " : await(): No visitor present. Please wait for a while.");
                zeroVisitorCondition.await();
            }
            currentVisitors--;
            visitorGreaterThanTenCondition.signalAll();
        }catch (InterruptedException exception){
			exception.printStackTrace();
		}finally{
            lock.writeLock().unlock();
        }
    }

    public int countCurrentVisitors(){
        lock.readLock().lock();
        try{
            return currentVisitors;
        }finally{
            lock.readLock().unlock();
        }
    }
}