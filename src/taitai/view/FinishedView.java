package taitai.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import taitai.Taitai;
import taitai.TaitaiModel;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

/**
 * Finished view for Gui
 * @author Matthew Taylor, Jaedyn Ward
 */
public class FinishedView {
	
	private Scene _finished;
	private int _numCorrect, _level;
	private boolean _goNext;
	
	/*
	 * constructor
	 */
	public FinishedView(boolean goNext, int numCorrect, int level) {
		_numCorrect = numCorrect;
		_goNext = goNext;
		_level = level;
	}
	
	public Scene getFinishedView(int width, int height) {
		
		BorderPane layout;
		Label message;
		Button toMenu, nextLevel;
		String messageToConcat;
		VBox messageLayout, toMenuLayout, nextLevelLayout;
		
		messageToConcat = "You got " + _numCorrect + " out of 10 correct!";
		layout = new BorderPane();
		toMenu = new Button("Go Back to Menu");
		message = new Label(messageToConcat);
		messageLayout = new VBox();
		toMenuLayout = new VBox();
		
		// go to menu view
		toMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				MainMenuView sv = new MainMenuView();
				Taitai.changeScene(sv.getMainMenuView(width, height));
			}
		});
		
		// format and style
		message.getStyleClass().add("label-numCorrect");
		toMenu.getStyleClass().add("button-back");
		
		messageLayout.setAlignment(Pos.CENTER);
		toMenuLayout.setAlignment(Pos.BOTTOM_RIGHT);
		
		messageLayout.setPadding(new Insets(100, 0, 20, 0));
		toMenuLayout.setPadding(new Insets(0, 40, 40, 0));
		
		messageLayout.getChildren().add(message);
		toMenuLayout.getChildren().add(toMenu);
		
		layout.setTop(messageLayout);
		layout.setBottom(toMenuLayout);
		
		// if can go to next level, if >8 was achieved in any mode
		if ((_goNext) && (_level < 5)) {
			nextLevel = new Button("Go To Next Level");
			nextLevelLayout = new VBox();
			
			nextLevel.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					QuizView qv = new QuizView(true, 1, 2, 0, TaitaiModel.startTest(_level + 1));
					Taitai.changeScene(qv.getQuizView(width, height));
				}
			});
			
			nextLevel.getStyleClass().add("button-menu");
			nextLevelLayout.setAlignment(Pos.TOP_CENTER);
			messageLayout.setPadding(new Insets(25, 0, 0, 0));
			nextLevelLayout.getChildren().add(nextLevel);
			layout.setCenter(nextLevelLayout);
		}
		
		_finished = new Scene(layout, width, height);
		_finished.getStylesheets().add("taitai/view/TaitaiTheme.css");
		return _finished;
	}
}
