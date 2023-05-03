package edu.umb.cs681.hw13;

import java.nio.file.Path;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class RequestHandler implements Runnable  {
    private AtomicBoolean stop = new AtomicBoolean(false);
    private Random random = new Random(0);
    private Path[] files = {
        Path.of("data/hw11/a.html"), Path.of("data/hw11/b.html"), Path.of("data/hw11/c.html") 
    };

    @Override
    public void run() {
        while(true){
            if (stop.get()){
                System.out.println("Thread " + Thread.currentThread().getId() + " stopped.");
                break;
            }
            Path path = files[random.nextInt(files.length)];
            int count = AccessCounter.getInstance().getCount(path);
            AccessCounter.getInstance().increment(path);
            System.out.println(Thread.currentThread().getId() + " accessed " + path + " " + count + " times");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stop(){
        stop.set(true);
    }

    public static void main(String[] args) {
        int threadCount = 20;
        Thread[] threads = new Thread[threadCount];
        RequestHandler[] handlers = new RequestHandler[threadCount];

        for (int i = 0; i < threadCount; i++) {
            handlers[i] = new RequestHandler();
            threads[i] = new Thread(handlers[i]);
            threads[i].start();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (int i = 0; i < threadCount; i++) {
            handlers[i].stop();
            threads[i].interrupt();
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
}
