package authoring.view;

import java.util.List;
import authoring.editortabpane.EditorTabPaneDelegate;
import authoring.editortabpane.EditorTabPaneFactory;
import authoring.editortabpane.IEditorTabPane;
import authoring.toolbar.IToolbar;
import authoring.toolbar.ToolbarFactory;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


/**
 * 
 * @author Kayla Schulz
 * @author Andrew Bihl
 * @author Diane Hadley
 *
 */
public class AuthoringView implements IAuthoringView {

    private Scene scene;
    private Group root;
    private IToolbar toolbar;
    private IEditorTabPane editorTabPane;
    // TODO: Get this magic value out
    private static final int SIZE = 700;
    private BorderPane authoringView;
    private Pane editorView;
    private ScrollPane mainEditorView;

    public AuthoringView (int editorWidth, int edit) {
        this.root = new Group();
        this.scene = new Scene(root, SIZE, SIZE);
        this.toolbar = ToolbarFactory.build(SIZE, SIZE);
        this.authoringView = new BorderPane();

        root.getChildren().add(authoringView);
        initScene();
    }

    @Override
    public IEditorTabPane getMySideTabbedToolbar () {
        return editorTabPane;
    }

    @Override
    public IToolbar getMyToolbar () {
        return toolbar;
    }

    private void initScene () {
        authoringView.setTop(toolbar.getInstanceAsNode());
        authoringView.setCenter(mainEditorView);

    }

    private void createEditorView (Node editor) {
        editorView = new Pane();
        mainEditorView = new ScrollPane();
        mainEditorView.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        mainEditorView.setPrefHeight(SIZE * 19 / 20);
        editorView.setPrefHeight(SIZE * 19 / 20);
        editorView.setPrefWidth(SIZE + 250);

    }

    @Override
    public Scene getScene () {
        return scene;
    }

    @Override
    public void setEditorView (Node editor) {
        if (editorView == null) {
            createEditorView(editor);
        }

        mainEditorView.setContent(editorView);
        editorView.getChildren().clear();
        editorView.getChildren().add(editor);
        authoringView.setCenter(mainEditorView);
    }

    @Override
    public void createEditorTabPane (List<String> tabs) {
        editorTabPane = EditorTabPaneFactory.build(SIZE, SIZE, tabs);
        authoringView.setRight(editorTabPane.getInstanceAsNode());
    }

    @Override
    public void setEditorTabPaneDelegate (EditorTabPaneDelegate delegate) {
        editorTabPane.setDelegate(delegate);
    }

}
