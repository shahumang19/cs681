package edu.umb.cs681.hw13;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AccessCounter {
    private static ReentrantLock slock = new ReentrantLock();
    private HashMap<Path, Integer> fileCountMap;
    private ReentrantReadWriteLock lock;
    private static AccessCounter instance = null;

    private AccessCounter(){
        fileCountMap = new HashMap<>();
        lock = new ReentrantReadWriteLock();
    }

    public static AccessCounter getInstance(){
        slock.lock();
        try{
            if (AccessCounter.instance == null){
                AccessCounter.instance = new AccessCounter();
            }
        }finally{
            slock.unlock();
        }
        return instance;
    }

    public void increment(Path path){
        lock.writeLock().lock();
        try{
            if (fileCountMap.containsKey(path)){
                fileCountMap.put(path, fileCountMap.get(path) + 1);
            }else{
                fileCountMap.put(path, 1);
            }
        }finally{
            lock.writeLock().unlock();
        }
    }

    public int getCount(Path path){
        lock.readLock().lock();
        try{
            if (fileCountMap.containsKey(path)){
                return fileCountMap.get(path);
            }else{
                return 0;
            }   
        }finally{
            lock.readLock().unlock();
        }
    }
}


