package edu.umb.cs681.hw17.safe;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Subject {
    String name;
    private ReentrantLock lock = new ReentrantLock();
    ArrayList<String> students = new ArrayList<>();

    public Subject(String n){
        name = n;
    }

    public String getName(){
        return name;
    }

    public ReentrantLock getLock(){
        return lock;
    }

    public void enrollStudent(Student student){
        lock.lock();
        students.add(student.getName());
        try {
            System.out.println(student.getName() + " enrolled in " + name + "!!!");
            Thread.sleep(500); // Processing time
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}
