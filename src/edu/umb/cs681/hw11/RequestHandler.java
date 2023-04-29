package edu.umb.cs681.hw11;

import java.nio.file.Path;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class RequestHandler implements Runnable  {
    private final AtomicBoolean stop = new AtomicBoolean(false);
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
    
}
