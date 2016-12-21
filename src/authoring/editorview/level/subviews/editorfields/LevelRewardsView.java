package authoring.editorview.level.subviews.editorfields;

import java.util.ResourceBundle;
import authoring.editorview.level.LevelSetView;
import authoring.editorview.level.LevelAuthoringViewDelegate;
import authoring.utilityfactories.BoxFactory;
import authoring.utilityfactories.TextFieldFactory;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * 
 * @author Kayla Schulz
 *
 */
public class LevelRewardsView implements LevelSetView {

    private VBox vbox;
    private LevelAuthoringViewDelegate delegate;

    private TextField rewardHealthTextField;
    private TextField rewardMoneyTextField;
    private TextField rewardScoreTextField;

    // TODO: reduce duplicated code

    private static final String RESOURCE_FILE_NAME = "resources/GameAuthoringLevels";
    private ResourceBundle levelResource = ResourceBundle.getBundle(RESOURCE_FILE_NAME);

    public LevelRewardsView (ResourceBundle levelsResource) {
        vbox = new VBox(10);
        makeHealthRewardTextField();
        makeMoneyRewardTextField();
        makeScoreRewardTextField();
    }

    @Override
    public Node getInstanceAsNode () {
        return vbox;
    }

    @Override
    public void setDelegate (LevelAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    private void makeHealthRewardTextField () {
        rewardHealthTextField = TextFieldFactory.makeTextField("",
                                                               e -> submitRewardHealth(rewardHealthTextField
                                                                       .getText()));
        rewardHealthTextField.setMaxWidth(75);
        HBox rewardHealthBox =
                BoxFactory.createHBoxWithLabelandNode(
                                                      levelResource
                                                              .getString("RewardHealthTextField"),
                                                      rewardHealthTextField);

        vbox.getChildren().add(rewardHealthBox);

    }

    private void makeMoneyRewardTextField () {
        rewardMoneyTextField = TextFieldFactory.makeTextField("",
                                                              e -> submitRewardMoney(rewardMoneyTextField
                                                                      .getText()));
        rewardMoneyTextField.setMaxWidth(75);
        HBox rewardMoneyBox =
                BoxFactory
                        .createHBoxWithLabelandNode(levelResource.getString("RewardMoneyTextField"),
                                                    rewardMoneyTextField);

        vbox.getChildren().add(rewardMoneyBox);

    }

    private void makeScoreRewardTextField () {
        rewardScoreTextField = TextFieldFactory.makeTextField("",
                                                              e -> submitRewardScore(rewardScoreTextField
                                                                      .getText()));
        rewardScoreTextField.setMaxWidth(75);
        HBox rewardScoreBox =
                BoxFactory.createHBoxWithLabelandNode(
                                                      levelResource
                                                              .getString("RewardScoreTextField"),
                                                      rewardScoreTextField);

        vbox.getChildren().add(rewardScoreBox);

    }

    private void submitRewardHealth (String healthString) {
        delegate.onUserEnteredRewardHealth(healthString);
    }

    private void submitRewardMoney (String moneyString) {
        delegate.onUserEnteredRewardMoney(moneyString);
    }

    private void submitRewardScore (String ScoreString) {
        delegate.onUserEnteredRewardScore(ScoreString);
    }

    public void setRewardHealth (String rewardHealth) {
        rewardHealthTextField.setText(rewardHealth);
    }

    public void setRewardMoney (String rewardMoney) {
        rewardMoneyTextField.setText(rewardMoney);
    }

    public void setRewardScore (String Score) {
        rewardScoreTextField.setText(Score);
    }
}
