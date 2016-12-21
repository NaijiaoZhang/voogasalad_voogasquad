package authoring.editorview;

public class ListCellData {

    private String name;
    private String imagePath;
    private Integer id;

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getImagePath () {
        return imagePath;
    }

    public void setImagePath (String imageFilePath) {
        this.imagePath = imageFilePath;
    }

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

}
