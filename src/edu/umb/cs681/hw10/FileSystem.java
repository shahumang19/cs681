package edu.umb.cs681.hw10;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class FileSystem {
    private static ReentrantLock slock = new ReentrantLock();
    private ReentrantLock lock = new ReentrantLock();
    private static LinkedList<Directory> rootDirectories;
    private static FileSystem instance = null;

    private FileSystem(){}

    public static FileSystem getFileSystem(){
        slock.lock();
        try{
            if(FileSystem.instance == null) {
                FileSystem.instance = new FileSystem();
                FileSystem.rootDirectories = new LinkedList<Directory>();
            }
        }finally{
            slock.unlock();
        }
        
        return instance;
    }

    public void appendRootDirectory(Directory child){
        lock.lock();
        try{
            FileSystem.rootDirectories.add(child);
        }finally{
            lock.unlock();
        }

    }

    public LinkedList<Directory> getRootDirectories(){
        lock.lock();
        try{
            return FileSystem.rootDirectories;
        }finally{
            lock.unlock();
        }
        
    }
}
