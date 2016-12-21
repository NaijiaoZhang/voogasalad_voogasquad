package gameplayer.view.helper.dragdrop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

import gameplayer.view.GameGUI;
import gameplayer.view.GridGUI;
import gameplayer.view.helper.GraphicsLibrary;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

/**
 * DragDrop is responsible for allowing an ImageView to be dragged and dropped
 * into another pane
 * 
 * @author lucyzhang
 *
 */
public class DragDrop extends Observable{

	private ImageView source;
	private double width;
	private double height;
	private GraphicsLibrary graphicLib;
	private double yError;
	private double xError;
	private Node target;
	private ImageView droppedImage;
	private List<int[]> coordinates;
	private HashMap<String, Integer> towerMap;
	private GameGUI view;

	public DragDrop() {
		this.graphicLib = new GraphicsLibrary();
		this.xError = 0;
		this.yError = 0;
		this.coordinates = new ArrayList<int[]>();
	}
	
	public DragDrop(double xError, double yError, GameGUI view) {
		this.graphicLib = new GraphicsLibrary();
		this.xError = xError;
		this.yError = yError;
		this.coordinates = new ArrayList<int[]>();
		this.view = view;
	}
	
	public List<int[]> getCoordinates(){
		return this.coordinates;
	}
	
	public void setTowerMap(HashMap<String, Integer> tower){
		this.towerMap= tower;
	}
	
	public double getxError(){
		return this.xError;
	}
	public double getyError(){
		return this.yError;
	}
	
	public ImageView getSource(){
		return this.source;
	}
	
	public Node getTarget(){
		return this.target;
	}
	
	public ImageView getDroppedImage(){
		return this.droppedImage;
	}

	/**
	 * Initializes the drag functionality for an element and its target location
	 * @param source This is the source that is to be dragged and dropped
	 * @param target This is the target that the image is to be dropped into
	 */
	public void init(ImageView source, Node target) {
		this.target = target;
		detectDrag(source, target);
	}

	private void initDragDetectionIcon(ImageView source) {
		Dragboard db = source.startDragAndDrop(TransferMode.MOVE);
		ClipboardContent content = new ClipboardContent();
		db.setDragView(source.getImage());
		content.putString("blahy poo");
		db.setContent(content);
	}


	private void addImagetoDroppedLoc(double xpos, double ypos, Node target) {
		ImageView copy = new ImageView(this.source.getImage());
		Integer newId = this.towerMap.get(this.source.getId());
		//System.out.println("ADDED TOWER IN DRAGDROP CLASS");
		//System.out.println("Tower coords: "+xpos+","+ypos);
		copy.setId(Integer.toString(newId)); 
		this.droppedImage = copy;
		graphicLib.setImageViewParams(copy, this.width, this.height);
		int gridX = (int)(this.view.pixelToGridCoord(xpos+this.xError) * this.width);
		int gridY = (int)(this.view.pixelToGridCoord(ypos+this.yError) * this.height);
		//System.out.println("New x,y: "+gridX+", "+gridY);
		copy.setX(gridX);
		copy.setY(gridY);
		/*
		copy.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

		     @Override
		     public void handle(MouseEvent event) {
		         System.out.println("Tile pressed ");
		         event.consume();
		     }
		});
		*/
		//System.out.println("Thing: " + ypos+this.yError);
		((Pane) target).getChildren().add(copy);
		int[] coords = {gridX,gridY};
		this.coordinates.add(coords);
		setChanged();
		notifyObservers();
	}

	private void setSourceInfo(ImageView source) {
		this.source = source;
		this.width = source.getFitWidth();
		this.height = source.getFitHeight();
	}

	private void detectDrag(ImageView source, Node target) {
		source.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setSourceInfo(source);
				initDragDetectionIcon(source);
				event.consume();
			}
		});

		target.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				if (event.getGestureSource() != target) {
					event.acceptTransferModes(TransferMode.MOVE);
				}
				event.consume();
			}
		});

		target.setOnDragDropped(event -> {
			addImagetoDroppedLoc(event.getSceneX(), event.getSceneY(), target);
			event.setDropCompleted(true);
		});

	}

}
