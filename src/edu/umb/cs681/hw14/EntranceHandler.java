package edu.umb.cs681.hw14;

import java.util.concurrent.atomic.AtomicBoolean;

class EntranceHandler implements Runnable{ 
    private AdmissionMonitor monitor;
    private AtomicBoolean done = new AtomicBoolean(false);
    
    public EntranceHandler(AdmissionMonitor monitor){
        this.monitor = monitor;
    }

    public void setDone(){
        done.set(true);
    }

    public void run(){
        while (true){
            if (done.get()){
                System.out.println("Entrance Done!!!");
                break;
            }
            System.out.println(Thread.currentThread().getName() + " - Visitor Entry!!!");
            monitor.enter();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " (enter) Interrupted!!!");
            }
        }
    } 
}