package edu.umb.cs681.hw14;

import java.util.concurrent.atomic.AtomicBoolean;

class ExitHandler implements Runnable{ 
    private AdmissionMonitor monitor;
    private AtomicBoolean done = new AtomicBoolean(false);
    
    public ExitHandler(AdmissionMonitor monitor){
        this.monitor = monitor;
    }

    public void setDone(){
        done.set(true);
    }

    public void run(){
        while (true){
            if (done.get()){
                System.out.println("Exit Done!!!");
                break;
            }
            System.out.println(Thread.currentThread().getName() + " - Visitor Exit!!!");
            monitor.exit();
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " (exit) Interrupted!!!");
            }
        }
    } 
}