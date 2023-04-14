package edu.umb.cs681.hw10;

import java.util.Iterator;
import java.util.LinkedList;

public class Directory extends FSElement {
    LinkedList<FSElement> children;

    public Directory(Directory parent, String name) {
        super(parent, name, 0);
        if (parent != null){
            parent.appendChildren(this);
        }
        this.children = new LinkedList<FSElement>();
    }

    @Override
    public boolean isDirectory() {
        return true;
    }


    public LinkedList<FSElement> getChildren() {
        lock.lock();
        try{
            return this.children;
        }finally{
            lock.unlock();
        }
    }

    public void appendChildren(FSElement child){
        lock.lock();
        try{
            this.children.add(child);
        }finally{
            lock.unlock();
        }
    }

    public int countChildren(){
        int count = 0;
        lock.lock();
        try{
            Iterator<FSElement> it = this.children.iterator();

            while(it.hasNext()) {
                count++;
                it.next();
            }
            return count;
        }finally{
            lock.unlock();
        }
    }

    public LinkedList<Directory> getSubDirectories(){
        LinkedList<Directory> directories = new LinkedList<Directory>();
        FSElement temp;

        lock.lock();
        try{
            Iterator<FSElement> it = this.children.iterator();

            while(it.hasNext()) {
                temp = (FSElement) it.next();
                if (temp.isDirectory()){
                    directories.add((Directory)temp);
                }

            }
            return directories;
        }finally{
            lock.unlock();
        }
    }

    public LinkedList<File> getFiles(){
        LinkedList<File> files = new LinkedList<File>();
        FSElement temp;

        lock.lock();
        try{
            Iterator<FSElement> it = this.children.iterator();

            while(it.hasNext()) {
                temp = (FSElement) it.next();
                if (!temp.isDirectory()){
                    files.add((File)temp);
                }

            }
            return files;
        }finally{
            lock.unlock();
        }
    }

    public int getTotalSize(){
        int totalSize=0;
        FSElement temp;

        lock.lock();
        try{
            Iterator<FSElement> it = this.children.iterator();

            while(it.hasNext()) {
                temp = (FSElement)it.next();
                if (temp.isDirectory()){
                    totalSize += ((Directory)temp).getTotalSize();
                }
                else{
                    totalSize += temp.getSize();
                }
            }

            return totalSize;
        }finally{
            lock.unlock();
        }
    }

    protected static Directory search(Directory dir, String name){
        if (dir.name == name){
            return dir;
        }
        else{
            Iterator<Directory> dirIterator = dir.getSubDirectories().iterator();
            while (dirIterator.hasNext()){
                Directory currentDir = (Directory)dirIterator.next();
                currentDir = search(currentDir, name);
                if (currentDir != null){
                    return currentDir;
                }
            }
        }
        return null;
    }

    public static Directory searchAndReturnFirstDirectory(FileSystem fs, String name){
        Directory child=null;
        Iterator<Directory> fsIterator = fs.getRootDirectories().iterator();

        while (fsIterator.hasNext()){
            child = (Directory)fsIterator.next();
            child = search(child, name);
            if (child != null){
                return child;
            }
        }
        return child;
    }
    
}
