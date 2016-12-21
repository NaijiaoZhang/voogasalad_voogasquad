package authoring.editorview;

/**
 * 
 * @author Diane Hadley
 *
 */

public class NameIdPair {

    private String name;
    private int id;

    public NameIdPair (String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public int getId () {
        return id;
    }

    public void setName (String name) {
        this.name = name;
    }

    public void setId (int id) {
        this.id = id;
    }

}
