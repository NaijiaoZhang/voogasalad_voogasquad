// package engine.effect;
//
// import authoring.editorview.collisioneffects.EffectUpdateView;
// import javafx.event.ActionEvent;
// import javafx.event.EventHandler;
// import javafx.scene.Group;
// import javafx.scene.Scene;
// import javafx.scene.input.ContextMenuEvent;
// import javafx.scene.input.MouseEvent;
//
// public class EffectViewController {
// EffectUpdateView view;
// EffectManagerController effectDataSource;
//
// EffectViewController(int size, EffectManagerController effectDataSouce) {
// this.view = new EffectEditorView(size);
// this.effectDataSource = effectDataSouce;
// view.updateTriggers(effectDataSource.getAvailableClasses());
// linkTrigger();
// effectDataSource.createType(view);
// }
//
// public void linkTrigger() {
// view.getTriggers().setOnAction(new EventHandler<ActionEvent>() {
// @Override
// public void handle(ActionEvent event) {
// effectDataSource.setTrigger (view.getTriggers().getSelectionModel().getSelectedIndex(),
// view.getTriggers().getSelectionModel().getSelectedItem());
// //view.getTriggerConditions().getItems().setAll(effectDataSource.)
// }
// });
// }
//
//// public void linkEffectBox() {
//// view.getTriggers().setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {//check for
// context menu request
//// @Override
//// public void handle(ContextMenuEvent arg0) {//handle event this way...
//// int didSet = effectDataSource.createType(view);
//// System.out.println(didSet);
//// //view.getTriggerConditions().getItems().setAll(effectDataSource.)
//// }
//// });
//// }
//
// public Scene getScene() {
// return view.getScene();
// }
// }
