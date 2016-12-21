package splashscreen;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.*;

import gameplayer.controller.GamePlayerController;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import statswrapper.Wrapper;

public class XMLGallery {
	
	public static final String TITLE = "VOOGASquad";
	private List<String> fileList;
	private TilePane gallery; 
	private Scene scene;
	
	public XMLGallery() {
		this.fileList = new ArrayList<String>();
		this.gallery = new TilePane();
		this.gallery.setPrefColumns(3);
		this.scene = new Scene(gallery);
		initFileList();
		initGallery();
	}
	
	private List<String> initFileList(){
		File xmlFolder = new File("player.samplexml");
		File[] files = xmlFolder.listFiles(new FilenameFilter() { 
            public boolean accept(File dir, String filename)
            { return filename.endsWith(".xml"); }
		} );
		for(File file : files){
			this.fileList.add(file.getName());
		}
		return this.fileList;
	}
	
	private void initGallery(){
		Stage s = new Stage();
		this.scene.getStylesheets()
		.add(this.getClass().getResource("/gameplayer/view/voogaStyle.css").toExternalForm());
		for(String filename: fileList){
			VBox box = new VBox();
			ImageView image = new ImageView("penguin.jpg");
			image.setFitHeight(100);
			image.setFitWidth(100);
			box.setOnMouseClicked(e -> {
				createNewGame(s, filename);
			});
			box.getChildren().addAll(image, new Label(filename));
			this.gallery.getChildren().add(box);
		}
		this.gallery.getStyleClass().clear();
		this.gallery.getStyleClass().add("galleryPane");
	}

	
	
	public void createNewGame(Stage s, String filename) {
		//System.out.println("File: "+filename);
		GamePlayerController playerController = new GamePlayerController("player.samplexml/"+filename);
		//playerController.init(false); init moved to GamePlayerController constructor
		playerController.setDataStoreOnClose(s);
		s.setTitle(TITLE);
		
		s.setScene(playerController.getMainScene());
		s.show();
	}

	public Scene getScene() {
		return scene;
	}
	
	

}
