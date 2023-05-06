package edu.umb.cs681.hw18;

import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
    private static ReentrantLock slock = new ReentrantLock();
    private ConcurrentHashMap<Path, AtomicInteger> fileCountMap;
    private static AccessCounter instance = null;

    private AccessCounter(){
        fileCountMap = new ConcurrentHashMap<>();
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

    public void increment(Path path) {
        fileCountMap.computeIfAbsent(path, p -> new AtomicInteger(0)).incrementAndGet();
    }

    public int getCount(Path path) {
        return fileCountMap.getOrDefault(path, new AtomicInteger(0)).get();
    }
}


