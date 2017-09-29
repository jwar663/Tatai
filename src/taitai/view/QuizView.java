package taitai.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.layout.*;
import taitai.Taitai;
import javafx.scene.control.*;

/**
 * Quiz view Gui
 * @author Matthew
 */
public class QuizView {

	private Scene _quiz;
	private int _number, _questionNumber, _level;
	private boolean _clickedRecord, _firstTry;
	
	public QuizView(boolean firstTry, int questionNumber, int level) {
		_questionNumber = questionNumber;
		_firstTry = firstTry;
		_clickedRecord = false;
		_level = level;
	}
	
	public Scene getQuizView(int width, int height) {
		
		BorderPane layout;
		Label question;
		Button toMenu, record, listen, submit;
		VBox questionLayout, toMenuLayout, buttonsLayout;
		
		layout = new BorderPane();
		toMenu = new Button("Go Back to Menu");
		record = new Button("Record");
		question = new Label(_number + "");
		
		questionLayout = new VBox();
		toMenuLayout = new VBox();
		buttonsLayout = new VBox(20);
		
		record.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				_clickedRecord = true;
				// TODO Auto-generated method stub
			}
		});
		
		toMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				MenuView sv = new MenuView();
				Taitai.changeScene(sv.getMenuView(width, height));
			}
		});
		
		toMenu.getStyleClass().add("button-back");
		record.getStyleClass().add("button-function");
		
		
		questionLayout.setAlignment(Pos.CENTER);
		buttonsLayout.setAlignment(Pos.TOP_CENTER);
		toMenuLayout.setAlignment(Pos.BOTTOM_RIGHT);
		
		buttonsLayout.setPadding(new Insets(10, 0, 20, 0));
		toMenuLayout.setPadding(new Insets(0, 40, 40, 0));
		
		buttonsLayout.getChildren().add(record);
		questionLayout.getChildren().add(question);
		toMenuLayout.getChildren().add(toMenu);
		
		layout.setTop(questionLayout);
		layout.setCenter(buttonsLayout);
		layout.setBottom(toMenuLayout);
		
		if (_clickedRecord) { // might not be correct
			listen = new Button("Playback");
			submit = new Button("Submit Answer");
			
			submit.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
				}
			});
			
			listen.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
				}
			});
			
			listen.getStyleClass().add("button-function");
			submit.getStyleClass().add("button-menu");
			
			buttonsLayout.getChildren().addAll(listen, submit);
		}
		
		_quiz = new Scene(layout, width, height);
		_quiz.getStylesheets().add("taitai/view/TaitaiTheme.css");
		return _quiz;
	}
	
	
	
}
