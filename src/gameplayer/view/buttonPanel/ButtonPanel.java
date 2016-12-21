package gameplayer.view.buttonPanel;

import gameplayer.view.helper.GraphicsLibrary;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ButtonPanel {

	private HBox buttonPane;
	private Button[] buttons;

	public ButtonPanel() {
		this.buttonPane = new HBox();
		
	}
	
	public HBox getButtonPanel(){
		return this.buttonPane;
	}

	public void init(Button[] buttons) {
		this.buttons = buttons;
		styleButtons();
		populatePaneWithButtons();
	}

	private void styleButtons() {
		for (Button button : buttons) {
			button.getStyleClass().add("ipad-dark-grey");
		}
	}

	private void populatePaneWithButtons() {
		buttonPane.getChildren().addAll(buttons);

	}

}
