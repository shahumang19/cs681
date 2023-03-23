package edu.umb.cs681.hw07;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class FileSystemTest {
    
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
}
