package edu.umb.cs681.hw07;

import java.util.Iterator;

public class Link extends FSElement{

    private FSElement target;

    public Link(Directory parent, String name, FSElement target) {
        super(parent, name, 0);
        parent.appendChildren(this);
        this.target = target;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    public FSElement getTarget() {
        return this.target;
    }

    public void setTarget(FSElement target) {
        this.target = target;
    }
    
    protected static Link search(Directory dir, String name){
        Iterator<FSElement> feIterator = dir.getChildren().iterator();
        while (feIterator.hasNext()){
            FSElement currentElement = (FSElement)feIterator.next();
            if (currentElement.isDirectory()){
                currentElement = search((Directory)currentElement, name);
                if (currentElement != null){
                    return (Link)currentElement;
                }
            }else{
                if (currentElement.name == name && currentElement instanceof Link){
                    return (Link)currentElement;
                }
            }  
        }
        return null;
    }

    public static Link searchAndReturnFirstLink(FileSystem fs, String name){
        Directory child=null;
        Link l = null;
        Iterator<Directory> fsIterator = fs.getRootDirectories().iterator();

        while (fsIterator.hasNext()){
            child = (Directory)fsIterator.next();
            l = search(child, name);
            if (l != null){
                return l;
            }
        }
        return l;
    }

}
