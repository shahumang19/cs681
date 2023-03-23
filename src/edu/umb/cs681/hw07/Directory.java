package edu.umb.cs681.hw07;

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
        return this.children;
    }

    public void appendChildren(FSElement child){
        this.children.add(child);
    }

    public int countChildren(){
        int count = 0;
        Iterator<FSElement> it = this.children.iterator();

        while(it.hasNext()) {
            count++;
            it.next();
        }
        return count;
    }

    public LinkedList<Directory> getSubDirectories(){
        LinkedList<Directory> directories = new LinkedList<Directory>();
        FSElement temp;
        
        Iterator<FSElement> it = this.children.iterator();

        while(it.hasNext()) {
            temp = (FSElement) it.next();
            if (temp.isDirectory()){
                directories.add((Directory)temp);
            }

        }
        return directories;
    }

    public LinkedList<File> getFiles(){
        LinkedList<File> files = new LinkedList<File>();
        FSElement temp;
        
        Iterator<FSElement> it = this.children.iterator();

        while(it.hasNext()) {
            temp = (FSElement) it.next();
            if (!temp.isDirectory()){
                files.add((File)temp);
            }

        }
        return files;
    }

    public int getTotalSize(){
        int totalSize=0;
        FSElement temp;
        
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
