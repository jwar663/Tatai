package taitai.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.layout.*;
import taitai.Taitai;
import taitai.TaitaiModel;
import javafx.scene.control.*;

/**
 * The feedback scene for the GUI
 * @author Matthew Taylor
 */
public class FeedBackView {

	private Scene _feedback;
	private boolean _firstTry, _correct, _last;
	int _number, _questionNumber, _width, _height, _level, _numCorrect;
	
	/*
	 * constructor
	 */
	public FeedBackView(boolean firstTry, int questionNumber, int level, int numCorrect, boolean correct, int number) {
		_correct = correct; 
		_firstTry = firstTry; 
		_questionNumber = questionNumber;
		_level = level;
		_numCorrect = numCorrect;
		_number = number;
		
		if (questionNumber == 10) {
			_last = true;
		} else {
			_last = true;
		}
	}
	
	public Scene getFeedBackView(int width, int height) {
		
		_width = width;
		_height = height;
		
		BorderPane layout;
		Label feedback;
		Button toMenu, toQuestion;
		VBox feedbackLayout, toMenuLayout, toQuestionLayout;
		
		// logic for correct message
		if (_correct && !_last) {
			feedback = new Label("Correct!");
			toQuestion = new Button("Next Question");
			feedback.getStyleClass().add("label-correct");
		} else if (_firstTry && !_correct) {
			feedback = new Label("Try Again");
			toQuestion = new Button("Retry");
			feedback.getStyleClass().add("label-tryAgain");
		} else if (_last && _correct) {
			feedback = new Label("Correct!");
			toQuestion = new Button("Finish Quiz");
			feedback.getStyleClass().add("label-correct");
			TaitaiModel.saveStats(_numCorrect, _level);// record stats
		} else if (_last && !_correct) {
			feedback = new Label("Incorrect!");
			toQuestion = new Button("Finish Quiz");
			feedback.getStyleClass().add("label-incorrect");
			TaitaiModel.saveStats(_numCorrect, _level);
		} else {
			feedback = new Label("Incorrect");
			toQuestion = new Button("Next Question");
			feedback.getStyleClass().add("label-incorrect");
		}	
		
		layout = new BorderPane();
		toMenu = new Button("Go Back to Menu");
		
		// next question action listener
		toQuestion.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) { // make questions asked appropriate
				if (_correct && !_last) {
					QuizView qv = new QuizView(true,  _questionNumber + 1, _level, _numCorrect, TaitaiModel.startTest(_level));
					Taitai.changeScene(qv.getQuizView(_width, _height));
					//cont
				} else if (_firstTry && !_correct) {
					QuizView qv = new QuizView(false, _questionNumber, _level, _numCorrect, _number);
					Taitai.changeScene(qv.getQuizView(_width, _height));
					// cont
				} else if (_last) {
					if ((_level == 1) && (_numCorrect >= 8)) {
						TaitaiModel.saveStats(_numCorrect, _level);
						FinishedView fv = new FinishedView(true, _numCorrect);
						Taitai.changeScene(fv.getFinishedView(_width, _height));
					} else {
						TaitaiModel.saveStats(_numCorrect, _level);
						FinishedView fv = new FinishedView(false, _numCorrect);
						Taitai.changeScene(fv.getFinishedView(_width, _height));
					}
				} else {
					QuizView qv = new QuizView(true,  _questionNumber + 1, _level, _numCorrect, TaitaiModel.startTest(_level));
					Taitai.changeScene(qv.getQuizView(_width, _height));
				}	
			}
		});
		
		toMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				MenuView sv = new MenuView();
				Taitai.changeScene(sv.getMenuView(width, height));
			}
		});
		
		
		// look andn feel
		feedbackLayout = new VBox();
		toMenuLayout = new VBox();
		toQuestionLayout = new VBox();
		
		toMenu.getStyleClass().add("button-back");
		toQuestion.getStyleClass().add("button-menu");
		
		feedbackLayout.setAlignment(Pos.CENTER);
		toQuestionLayout.setAlignment(Pos.TOP_CENTER);
		toMenuLayout.setAlignment(Pos.BOTTOM_RIGHT);
		
		toQuestionLayout.setPadding(new Insets(10, 0, 20, 0));
		toMenuLayout.setPadding(new Insets(0, 40, 40, 0));
		
		toQuestionLayout.getChildren().add(toQuestion);
		feedbackLayout.getChildren().add(feedback);
		toMenuLayout.getChildren().add(toMenu);
		
		layout.setTop(feedbackLayout);
		layout.setCenter(toQuestionLayout);
		layout.setBottom(toMenuLayout);
		
		_feedback = new Scene(layout, width, height);
		_feedback.getStylesheets().add("taitai/view/TaitaiTheme.css");
		return _feedback;
	}
	
}