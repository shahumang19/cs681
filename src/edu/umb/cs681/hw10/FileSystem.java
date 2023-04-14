package edu.umb.cs681.hw10;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class FileSystem {
    private static ReentrantLock lock = new ReentrantLock();
    private static LinkedList<Directory> rootDirectories;
    private static FileSystem instance = null;

    private FileSystem(){}

    public static FileSystem getFileSystem(){
        lock.lock();
        try{
            if(FileSystem.instance == null) {
                FileSystem.instance = new FileSystem();
                FileSystem.rootDirectories = new LinkedList<Directory>();
            }
        }finally{
            lock.unlock();
        }
        
        return instance;
    }

    public void appendRootDirectory(Directory child){
        FileSystem.rootDirectories.add(child);
    }

    public LinkedList<Directory> getRootDirectories(){
        return FileSystem.rootDirectories;
    }
}
