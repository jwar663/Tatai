package taitai.view;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.*;
import taitai.Taitai;
import taitai.TaitaiModel;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;

/** 
 * Menu GUI
 * @author Matthew Taylor
 */
public class MenuView {
	
	private static PromptBox _pb = new PromptBox();
	private Scene _menu;
	Button _level1, _level2, _level3, _level4, _level5;
	int _mode;
	int _customSize = TaitaiModel.findCustomSize();
	/*
	 * constructor
	 */
	public MenuView(int mode) {
		_mode = mode;
	}
	
	public Scene getMenuView(int width, int height) {
		
		Label questionType;
		Button toMenu;
		
		BorderPane layout;
		VBox buttonLayout, titleLayout, toMenuLayout;
		
		questionType = new Label("Please choose the type of questions.");
		_level1 = new Button("Numbers 1-99");
		_level2 = new Button("Addition and Subtraction");
		_level3 = new Button("Multiplication and Division");
		_level4 = new Button("Combination");
		_level5 = new Button("Custom");
		toMenu = new Button("Go Back to Menu");
		
		layout = new BorderPane();
		titleLayout = new VBox();
		buttonLayout = new VBox(25);
		toMenuLayout = new VBox();
		
		questionType.getStyleClass().add("label-menulabel");
		_level1.getStyleClass().add("button-menu");
		_level2.getStyleClass().add("button-menu");
		_level3.getStyleClass().add("button-menu");
		_level4.getStyleClass().add("button-menu");
		_level5.getStyleClass().add("button-menu");
		toMenu.getStyleClass().add("button-back");
		
		// button event goes to level 2 quiz
				_level1.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(_mode == 1) {
							PracticeView qv = new PracticeView(1, 1, 0, TaitaiModel.startTest(1));
							Taitai.changeScene(qv.getPracticeView(width, height));
						} else {
							QuizView qv = new QuizView(true, 1, 1, 0, TaitaiModel.startTest(1));
							Taitai.changeScene(qv.getQuizView(width, height));
						}
					}
				});
		// button event goes to level 2 quiz
		_level2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(_mode == 1) {
					PracticeView qv = new PracticeView(1, 2, 0, TaitaiModel.startTest(2));
					Taitai.changeScene(qv.getPracticeView(width, height));
				} else {
					QuizView qv = new QuizView(true, 1, 2, 0, TaitaiModel.startTest(2));
					Taitai.changeScene(qv.getQuizView(width, height));
				}
			}
		});
		
		// button event goes to level 2 quiz
				_level3.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(_mode == 1) {
							PracticeView qv = new PracticeView(1, 3, 0, TaitaiModel.startTest(3));
							Taitai.changeScene(qv.getPracticeView(width, height));
						} else {
							QuizView qv = new QuizView(true, 1, 3, 0, TaitaiModel.startTest(3));
							Taitai.changeScene(qv.getQuizView(width, height));
						}
					}
				});
				
				// button event goes to level 2 quiz
				_level4.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(_mode == 1) {
							PracticeView qv = new PracticeView(1, 4, 0, TaitaiModel.startTest(4));
							Taitai.changeScene(qv.getPracticeView(width, height));
						} else {
							QuizView qv = new QuizView(true, 1, 4, 0, TaitaiModel.startTest(4));
							Taitai.changeScene(qv.getQuizView(width, height));
						}
					}
				});
				
				// button event goes to level 2 quiz
				_level5.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(_customSize < 10) {
							_pb.displayBox("Error", "You do not have enough custom questions.");
						} else {
							if(_mode == 1) {
								PracticeView qv = new PracticeView(1, 5, 0, TaitaiModel.startTest(5));
								Taitai.changeScene(qv.getPracticeView(width, height));
							} else {
								QuizView qv = new QuizView(true, 1, 5, 0, TaitaiModel.startTest(5));
								Taitai.changeScene(qv.getQuizView(width, height));
							}
						}
					}
				});
				
				// to menu button
				toMenu.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						MainMenuView sv = new MainMenuView();
						Taitai.changeScene(sv.getMainMenuView(width, height));
					}
				});
		
		
		buttonLayout.setAlignment(Pos.TOP_CENTER);
		titleLayout.setAlignment(Pos.CENTER);
		toMenuLayout.setAlignment(Pos.BOTTOM_RIGHT);
		
		buttonLayout.setPadding(new Insets(0, 0, 20, 0));
		toMenuLayout.setPadding(new Insets(0, 40, 40, 0));
		
		titleLayout.getChildren().add(questionType);
		buttonLayout.getChildren().addAll(_level1, _level2, _level3, _level4, _level5);
		toMenuLayout.getChildren().add(toMenu);
		
		layout.setTop(titleLayout);
		layout.setCenter(buttonLayout);
		layout.setBottom(toMenuLayout);
		
		
		_menu = new Scene(layout, width, height);
		_menu.getStylesheets().add("taitai/view/TaitaiTheme.css");
		
		return _menu;
	}
	
}