package edu.umb.cs681.hw07;

import java.util.Iterator;

public class File extends FSElement {

    public File(Directory parent, String name, int size) {
        super(parent, name, size);
        parent.appendChildren(this);
    }

    @Override
    public boolean isDirectory() {
        return false;
    }
    
    protected static File search(Directory dir, String name){
        Iterator<FSElement> feIterator = dir.getChildren().iterator();
        while (feIterator.hasNext()){
            FSElement currentElement = (FSElement)feIterator.next();
            if (currentElement.isDirectory()){
                currentElement = search((Directory)currentElement, name);
                if (currentElement != null){
                    return (File)currentElement;
                }
            }else{
                if (currentElement.name == name){
                    return (File)currentElement;
                }
            }  
        }
        return null;
    }

    public static File searchAndReturnFirstFile(FileSystem fs, String name){
        Directory child=null;
        File f = null;
        Iterator<Directory> fsIterator = fs.getRootDirectories().iterator();

        while (fsIterator.hasNext()){
            child = (Directory)fsIterator.next();
            f = search(child, name);
            if (f != null){
                return f;
            }
        }
        return f;
    }

}
