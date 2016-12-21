package gameplayer.view.helper;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;

public class GraphicsLibrary {
	public Image createImage(String imageLocation) {
		int index=imageLocation.lastIndexOf("/")+1;
		String loc = imageLocation.substring(index, imageLocation.length());
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(loc));
		return image;
	}

	public ImageView createImageView(Image image) {
		return new ImageView(image);
	}

	public void setBackgroundImage(Group root, String imageLocation) {
		Image image = createImage(imageLocation);
		ImageView background = createImageView(image);
		root.getChildren().add(background);
		setImageViewParams(background, 0, 0, Screen.getPrimary().getVisualBounds().getWidth(),
				Screen.getPrimary().getVisualBounds().getHeight());
	}

	public void setBackgroundImage(Group root, String imageLocation, double width, double height) {
		Image image = createImage(imageLocation);
		ImageView background = createImageView(image);
		root.getChildren().add(background);
		setImageViewParams(background, 0, 0, width, height);
	}

	public void setImageViewParams(ImageView image, double x, double y, double width, double height) {
		image.setX(x);
		image.setY(y);
		image.setFitHeight(height);
		image.setFitWidth(width);
	}

	public void setImageViewParams(ImageView image, double width, double height) {
		image.setFitHeight(height);
		image.setFitWidth(width);
	}
	
	public Button createButton(String label) {
		Button button = new Button(label);
		return button;
	}
	
	public Button createButton(String label, EventHandler<ActionEvent> e){
		Button button = new Button(label);
		button.setOnAction(e);
		return button;
	}

	public Button createButton(String label, int x, int y) {
		Button button = new Button(label);
		button.setLayoutX(x);
		button.setLayoutY(y);
		return button;
	}

	public VBox createVBoxPane(double width, double height) {
		VBox vbox = new VBox(10);
		vbox.setMaxWidth(width);
		vbox.setMinWidth(width);
		vbox.setMaxHeight(height);
		vbox.setMinHeight(height - 30);
		return vbox;
	}

	public VBox createVBoxPane(int width, int height, Insets insets) {
		VBox vbox = createVBoxPane(width, height);
		vbox.setPadding(insets);
		return vbox;
	}

	public Text createText(Group root, double x, double y, String text) {
		Text t = new Text(x, y, text);
		root.getChildren().add(t);
		return t;
	}
	
	public Text createText(Group root,String text) {
		Text t = new Text(text);
		root.getChildren().add(t);
		return t;
	}

	public void setTextAttributes(Text text, Color color, Font font, String id) {
		text.setFill(color);
		text.setFont(font);
		text.setId(id);
	}

	public BorderPane createBorderPane(Group root, int width, int height) {
		BorderPane border = new BorderPane();
		border.setPrefWidth(width * 1.95);
		border.setPrefHeight(height);
		root.getChildren().add(border);
		return border;
	}
	
	public GridPane creatGridPane(Group root, int hgap, int vgap){
		GridPane grid = new GridPane();
        grid.setHgap(hgap);
        grid.setVgap(vgap);
        //grid.setPadding(new Insets(25, 25, 25, 25));//TODO: change!
        root.getChildren().add(grid);
        return grid;
	}
	
	public TilePane creatTilePane(Group root, int hgap, int vgap){
		TilePane grid = new TilePane();
        grid.setHgap(hgap);
        grid.setVgap(vgap);
        //grid.setPadding(new Insets(25, 25, 25, 25));//TODO: change!
        root.getChildren().add(grid);
        return grid;
	}

}
