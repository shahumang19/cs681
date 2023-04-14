package edu.umb.cs681.hw10;

public class TestFixtureInitializer {
    public static FileSystem createFS(){
        FileSystem fs = FileSystem.getFileSystem();
        Directory root = new Directory(null, "root"); 
        fs.appendRootDirectory(root);

        Directory apps = new Directory(root, "apps"); 
        Directory bin = new Directory(root, "bin"); 
        Directory home = new Directory(root, "home"); 
        Directory pictures = new Directory(home, "pictures"); 
        
        File x = new File(apps, "x", 20);
        File y = new File(bin, "y", 30);

        File a = new File(pictures, "a", 200);
        File b = new File(pictures, "b", 300);
        File c = new File(home, "c", 20);

        Link d = new Link(root, "d", pictures);
        Link e = new Link(root, "e", x);

        return fs;
    }
}