package edu.umb.cs681.hw09;

import java.util.concurrent.locks.ReentrantLock;

public class Aircraft {
    private Position position; // Shared (non-final) variable
    protected final ReentrantLock lock = new ReentrantLock();
    
    public Aircraft(Position pos){ 
        this.position = pos; 
    }
    
    public void setPosition(double newLat, double newLong, double newAlt){
        lock.lock();
        try{
            this.position = this.position.change(newLat, newLong, newAlt);
        }finally{
            lock.unlock();
        }
        
    }

    public Position getPosition(){ 
        lock.lock();
        try{
            return position;
        }finally{
            lock.unlock();
        } 
    }
}