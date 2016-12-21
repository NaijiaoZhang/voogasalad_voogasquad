package engine;

public interface GameData {

    String getTitle ();

    void setTitle (String title);

    String getAuthor ();

    void setAuthor (String author);

    ManagerMediator getManagerMediator();
}
