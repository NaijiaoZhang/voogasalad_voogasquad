package engine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;

import authoring.utilityfactories.DialogueBoxFactory;
import javafx.scene.control.Alert;
/**
 * 
 * @author Andrew Bihl
 *
 */
public class FileAggregator {
	private final String IMAGES_DIRECTORY_NAME = "game_images";
	private static FileAggregator defaultInstance;
	private File imageDirectory;
	//Map images used to the number of places used in the program. Remove on count=0
	private HashMap<String, Integer> originalImagePaths;
	//Map original image paths to copied image files
	private HashMap<String, File> images;

	private FileAggregator(){
		this.imageDirectory = new File(IMAGES_DIRECTORY_NAME);
		try {
			Files.createDirectories(imageDirectory.toPath());
		} catch (IOException e) {
			System.out.println("Failed to create image directory");
			e.printStackTrace();
		}
		this.originalImagePaths = new HashMap<String, Integer>();
		this.images = new HashMap<String, File>();
	}
	
	public static FileAggregator defaultInstance(){
		if (defaultInstance==null){
			defaultInstance = new FileAggregator();
		}
		return defaultInstance;
	}
	
	
	public void writeXMLFile(File xmlFile, String xmlContent){
        if (xmlFile != null) {
            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter(xmlFile);
                fileWriter.write(xmlContent);
                fileWriter.close();
            }
            catch (IOException e) {
                Alert fileError =
                        DialogueBoxFactory.createErrorDialogueBox("Error with file. Can't be saved",
                                                                  "File Error");
                fileError.show();
            }
        }
	}
	
	/**
	 * 
	 * @param rootDirectory – This is the folder with user-input title. The working game directory is renamed to match this folder.
	 * @param xmlContent – Game authoring content to be saved as an XML file in the game directory.
	 * @return the game directory with XML file and assets.
	 */
	public File createGameFolder(File rootDirectory, String xmlContent){
		rootDirectory.mkdirs();
		File newImageDirectory = new File(rootDirectory, IMAGES_DIRECTORY_NAME);
		try {
			Files.move(imageDirectory.toPath(), newImageDirectory.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e1) {
			System.out.println("Failed to move image folder to game folder");
			e1.printStackTrace();
			return null;
		}
		//Game folder name and xml file name will match
		String xmlFileName = this.getFileNameFromPath(rootDirectory.getAbsolutePath()+".xml");
		File xmlFile = new File(rootDirectory, xmlFileName);
		try {
			FileWriter fileWriter = new FileWriter(xmlFile);
			fileWriter.write(xmlContent);
			fileWriter.close();
			return rootDirectory;
		}
		catch (IOException e) {
			Alert fileError =
					DialogueBoxFactory.createErrorDialogueBox("Error with file. Can't be saved",
							"File Error");
			fileError.show();
			return null;
		}
	}
	
	
	/**
	 * Adds the image path to the game assets if it is not already included. 
	 * FileAggregator keeps track of references of image paths, and will remove the old image if it is no longer being used in the game.
	 * @param oldImagePath – This may be null
	 * @param newImagePath
	 * @return path of copied asset file
	 */
	public String addImageToAssets(String oldImagePath, String newImagePath){
		decrementImageCount(oldImagePath);
		incrementImageCount(newImagePath);
		return this.images.get(newImagePath).getPath();
	}
	
	private void decrementImageCount(String path){
		Integer count = this.originalImagePaths.get(path);
		if (count==null){
			return;
		}
		if (count <= 1){
			this.originalImagePaths.remove(path);
			removeImageFile(path);
		} else{
			this.originalImagePaths.put(path, count-1);
		}
	}
	
	private void incrementImageCount(String path){
		Integer count = this.originalImagePaths.get(path);
		if (count == null){
			this.originalImagePaths.put(path, 1);
			addImageFile(path);
		}
		else{
			this.originalImagePaths.put(path, count+1);
		}
	}
	
	private void addImageFile(String imagePath){
		File source = new File(imagePath);
		File newImage = new File(imageDirectory, this.getFileNameFromPath(imagePath));
		try {
			Files.copy(source.toPath(), newImage.toPath());
		} catch (IOException e) {
			//System.out.println("FAILED TO COPY IMAGE FILE: "+imagePath);
			e.printStackTrace();
		}
		//The copy will fail if for some reason the file is already created in the directory.
		if (newImage.exists()){
			this.images.put(imagePath, newImage);
		}
	}
	
	private void removeImageFile(String imagePath){
		File image = this.images.get(imagePath);
		image.delete();
		this.images.remove(imagePath);
	}

	public String getFileNameFromPath(String filePath){
		String[] pathComponents = filePath.split("/");
		String name = pathComponents[pathComponents.length-1];
		return name;
	}
	
}
