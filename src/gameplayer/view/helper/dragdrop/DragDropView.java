package gameplayer.view.helper.dragdrop;

import java.util.ArrayList;
import java.util.List;

import gameplayer.view.GameGUI;
import gameplayer.view.GridGUI;
import gameplayer.view.helper.GraphicsLibrary;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

/**
 * Responsible for the tab pane that holds all of the defensive structures that
 * can be dragged onto the grid
 * 
 * @author lucyzhang
 *
 */
public class DragDropView {

	private TabPane dragDropPane;
	private ArrayList<Tab> tabs;
	private GraphicsLibrary graphicLib;
	public static final int DEFENSIVEWIDTH = 70;
	public static final int DEFENSIVEHEIGHT = 70;
	private Pane target;
	private DragDrop dragDrop;
	private List<ImageView> objects;
	

	public DragDropView(double xError, double yError, GameGUI view) {
		this.dragDropPane = new TabPane();
		this.tabs = new ArrayList<Tab>();
		this.graphicLib = new GraphicsLibrary();
		this.dragDrop = new DragDrop(xError,yError, view);
		this.objects = new ArrayList<ImageView>();
		
		setTabPaneStyle();
	}
	
	
	
	private void setTabPaneStyle(){
		this.dragDropPane.getStyleClass().add("dragDropPane");
	}
	
	public void setDragTarget(Pane target){
		this.target = target; 
	}
	
	public TabPane getDragDropPane(){
		return this.dragDropPane;
	}
	
	public DragDrop getDragDrop(){
		return this.dragDrop;
	}
	
	/**
	 * Creates a new tab and adds it to the main tabpane
	 * @param title
	 * @return The new tab object
	 */
	public Tab createTab(String title) {
		Tab newTab = new Tab();
		newTab.setText(title);
		this.dragDropPane.getTabs().add(newTab);
		tabs.add(newTab);
		return newTab;
	}


	/**
	 * Populates a tab with images to later drag and drop
	 * @param imageLocations Array of the image location names
	 */
	public void populateImageViewsToTab(Tab tab, List<String> imageLocations) {
		ScrollPane root = new ScrollPane();
		TilePane grid = new TilePane(); //TODO: change, would be set by the xml file
		grid.setHgap(20);
		grid.setVgap(20);
		root.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Horizontal
        root.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Vertical scroll bar
        root.setFitToWidth(true);
        root.setContent(grid);
    	root.getStyleClass().add("towersScrollPane");
        //int idCount = 0;
		for (String image:imageLocations){
			//System.out.println("image: "+ image);
			ImageView currentImage = graphicLib.createImageView(graphicLib.createImage(image));
			currentImage.setId(image);
			//currentImage.setId(idCount+"");
			//idCount++;
			dragDrop.init(currentImage, target);
			objects.add(currentImage);//TODO: do I need this?
			graphicLib.setImageViewParams(currentImage, DEFENSIVEWIDTH, DEFENSIVEHEIGHT);
			grid.getChildren().add(currentImage);// ,cIndex, rIndex);
		}
		tab.setContent(root);

	}
	
	

}
