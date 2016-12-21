package engine;

public interface Type {

    String getName ();

    void setName (String name);

    String getImagePath ();

    void setImagePath (String imagePath);

    double getSize ();

    void setSize (double size);
    
    int getId ();
    
    String getSound();
    
    void setSound(String soundPath);

}
