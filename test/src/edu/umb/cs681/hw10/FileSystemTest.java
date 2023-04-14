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
        List<Thread> threads = new ArrayList<>();
        
        for (int i = 0; i < 15; i++) {
            Thread t = new Thread(() -> {
                Directory root = fs.getRootDirectories().get(0);
                File x = File.searchAndReturnFirstFile(fs, "x");
                x.setSize(x.getSize()+1);
                System.out.println(root.countChildren());
                Directory pictures = Directory.searchAndReturnFirstDirectory(fs, "pictures");
                System.out.println(pictures.getTotalSize());
                Link d = Link.searchAndReturnFirstLink(fs, "d");
                d.setTarget(Directory.searchAndReturnFirstDirectory(fs, "bin"));
            });
            threads.add(t);
            t.start();
         }

         for (Thread t : threads) {
            t.interrupt();
         }
         for (Thread t : threads) {
            try {
               t.join();
            } catch (InterruptedException e) {
               System.out.println("Thread interrupted!!!");
            }
         }
        System.out.println("All threads terminated.");
        
        assertEquals(Link.searchAndReturnFirstLink(fs, "d").getTarget(), Directory.searchAndReturnFirstDirectory(fs, "bin"));
        assertTrue(File.searchAndReturnFirstFile(fs, "x").getSize() > 20 );
    }
}
