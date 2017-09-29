package taitai.view;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.*;
import taitai.Taitai;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;

/**
 * Finished view Gui
 */
public class FinishedView {
	
	private Scene _finished;
	private int _numCorrect;
	private boolean _goNext;
	
	public FinishedView(boolean goNext) {
		_goNext = goNext;
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
		
		toMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				MenuView sv = new MenuView();
				Taitai.changeScene(sv.getMenuView(width, height));
			}
		});
		
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
		
		if (_goNext) {
			nextLevel = new Button("Go To Next Level");
			nextLevelLayout = new VBox();
			
			nextLevel.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					QuizView qv = new QuizView(true, 1, 2);
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
