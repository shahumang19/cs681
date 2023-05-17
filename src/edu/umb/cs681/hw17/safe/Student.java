package edu.umb.cs681.hw17.safe;

public class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public void enroll(Subject subject1, Subject subject2) {
        if (subject1.hashCode() < subject2.hashCode()){
            System.out.println(name + " is enrolling in " + subject1.getName() + "...");
            subject1.getLock().lock();
            try {
                subject1.enrollStudent(this);

                System.out.println(name + " is enrolling in " + subject2.getName() + "...");
                subject2.getLock().lock();
                subject2.enrollStudent(this);

                System.out.println(name + " has successfully enrolled in both subjects.");
                
            } finally {
                if (subject1.getLock().isLocked()){subject1.getLock().unlock();}
                if (subject2.getLock().isLocked()){subject2.getLock().unlock();}
            }
        }
        else{
            subject2.getLock().lock();
            try {
                System.out.println(name + " is enrolling in " + subject2.getName() + "...");
                subject2.enrollStudent(this);

                System.out.println(name + " is enrolling in " + subject1.getName() + "...");
                subject1.getLock().lock();
                subject1.enrollStudent(this);

                System.out.println(name + " has successfully enrolled in both subjects.");
                
            } finally {
                if (subject2.getLock().isLocked()){subject2.getLock().unlock();}
                if (subject1.getLock().isLocked()){subject1.getLock().unlock();}
            }
        }
        
    }

    public String getName() {
        return name;
    }
}
