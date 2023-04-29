package edu.umb.cs681.hw11;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
    private static ReentrantLock slock = new ReentrantLock();
    private HashMap<Path, Integer> fileCountMap;
    private ReentrantLock lock;
    private static AccessCounter instance = null;

    private AccessCounter(){
        fileCountMap = new HashMap<>();
        lock = new ReentrantLock();
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
        lock.lock();
        try{
            if (fileCountMap.containsKey(path)){
                fileCountMap.put(path, fileCountMap.get(path) + 1);
            }else{
                fileCountMap.put(path, 1);
            }
        }finally{
            lock.unlock();
        }
    }

    public int getCount(Path path){
        lock.lock();
        try{
            if (fileCountMap.containsKey(path)){
                return fileCountMap.get(path);
            }else{
                return 0;
            }   
        }finally{
            lock.unlock();
        }
    }
}


