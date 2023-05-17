package edu.umb.cs681.hw14;

import java.util.concurrent.atomic.AtomicBoolean;

class StatsHandler implements Runnable{ 
    private AdmissionMonitor monitor;
    private AtomicBoolean done = new AtomicBoolean(false);
    
    public StatsHandler(AdmissionMonitor monitor){
        this.monitor = monitor;
    }

    public void setDone(){
        done.set(true);
    }

    public void run(){
        while (true){
            if (done.get()){
                break;
            }
            System.out.println(Thread.currentThread().getName() + " - Current Count : " + monitor.countCurrentVisitors());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                continue;
            }
        }
    } 
}