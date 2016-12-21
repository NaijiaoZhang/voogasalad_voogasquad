package gameplayer.controller;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import engine.tower.Tower;
import gameplayer.model.GamePlayModel;
import gameplayer.view.GameGUI;
import gameplayer.view.GridGUI;
import gameplayer.view.helper.dragdrop.DragDrop;
import gameplayer.view.helper.dragdrop.DragDropView;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

public class DragDropController implements Observer{
	private DragDrop dragDrop;
	private GamePlayModel model;
	private GameGUI game;
	private HashMap<String, Integer>towerTypes; 
	
	
	public DragDropController(GameGUI game, GamePlayModel model, HashMap<String, Integer>towerTypes){
		this.dragDrop = game.getDragDrop();
		this.model = model;
		this.dragDrop.addObserver(this);
		this.game = game;
		this.towerTypes = towerTypes; 
		this.dragDrop.setTowerMap(towerTypes);
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof DragDrop){
			String towerId = dragDrop.getDroppedImage().getId();
			int x = (int)(dragDrop.getDroppedImage().getX());//* (this.model.getCellWidth()));
			int y = (int)(dragDrop.getDroppedImage().getY());//* (this.model.getCellHeight()));
			boolean canPlace = this.model.placeTower(Integer.parseInt(towerId), x, y);
			//System.out.println("Can place? :"+canPlace);
			if (!canPlace){
				this.dragDrop.getCoordinates().remove(this.dragDrop.getCoordinates().size() - 1);
				(this.game.getGrid().getGrid()).getChildren().remove(dragDrop.getDroppedImage());// remove((Node) dragDrop.getDroppedImage());
			}

		}
		
	}

}
