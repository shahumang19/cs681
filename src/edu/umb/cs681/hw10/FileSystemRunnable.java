package edu.umb.cs681.hw10;

import java.util.concurrent.locks.ReentrantLock;

public class FileSystemRunnable implements Runnable {
    private FileSystem fs;
    private ReentrantLock lock = new ReentrantLock();
    private boolean done;

    public void setDone(){
        lock.lock();
        try{
            done = true;
        } finally {
            lock.unlock();
        }
    }

    public FileSystemRunnable(FileSystem fs) {
        this.fs = fs;
        done = false;
    }

    @Override
    public void run() {
        while (true){
            lock.lock();
            try{
                if (done) {
                    System.out.println(Thread.currentThread().getName() + " - terminated.");
                    break;
                }
                Directory root = fs.getRootDirectories().get(0);
                File x = File.searchAndReturnFirstFile(fs, "x");
                x.setSize(x.getSize()+1);
                root.countChildren();
                Directory pictures = Directory.searchAndReturnFirstDirectory(fs, "pictures");
                pictures.getTotalSize();
                Link d = Link.searchAndReturnFirstLink(fs, "d");
                d.setTarget(Directory.searchAndReturnFirstDirectory(fs, "bin"));
            } finally {
                lock.unlock();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                continue;
            }
        }
    }
    
}
