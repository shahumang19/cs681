package edu.umb.cs681.hw10;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.*;

public class FileSystemTest {
    private static FileSystem fs;

    @BeforeAll
    public static void setUpFS(){
        fs = TestFixtureInitializer.createFS();
    }
    
    @Test
    public void verifySingleFileSystemInstance() throws InterruptedException{
        FileSystem[] fsArray = new FileSystem[10];
        Thread[] threads = new Thread[10];

        for(int i=0; i<10; i++){
            final int index = i;
            threads[i] = new Thread(
                    () -> {
                        fsArray[index] = FileSystem.getFileSystem();
                    }
                );
            threads[i].start();
        }

        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }
        
        for (int i = 1; i < fsArray.length; i++) {
            assertSame(fsArray[0], fsArray[i]);
        }
    }

    @Test
    public void verifyMultiThreadSafeFS(){
        int numThreads = 15;
        List<Thread> threads = new ArrayList<>();
        List<FileSystemRunnable> runnables = new ArrayList<>(); 
        
        for (int i = 0; i < numThreads; i++) {
            FileSystemRunnable fsr = new FileSystemRunnable(fs);
            Thread t = new Thread(fsr);
            runnables.add(fsr);
            threads.add(t);
            t.start();
         }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < numThreads; i++) {
            runnables.get(i).setDone();
            threads.get(i).interrupt();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted!!!");
            }
        }
        
        for (Thread t : threads) {
            assertTrue(t.getState() == Thread.State.TERMINATED);
        }
    }
}
