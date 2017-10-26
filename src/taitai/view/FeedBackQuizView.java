package taitai.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import taitai.Taitai;
import taitai.TaitaiModel;
import javafx.scene.control.*;

/**
 * The feedback scene for the GUI
 * @author Matthew Taylor, Jaedyn Ward
 */
public class FeedBackQuizView {

	private Scene _feedback;
	private boolean _firstTry, _correct, _last, _skippedQuestion;
	int _questionNumber, _width, _height, _level, _numCorrect;
	String _incorrect, _expression;
	private ConfirmBox _cb = new ConfirmBox();
	private ConfirmBox _cb2 = new ConfirmBox();
	
	/*
	 * constructor
	 */
	public FeedBackQuizView(boolean firstTry, int questionNumber, int level, int numCorrect, boolean correct, String expression, boolean skippedQuestion) {
		_correct = correct; 
		_firstTry = firstTry; 
		_questionNumber = questionNumber;
		_level = level;
		_numCorrect = numCorrect;
		_expression = expression;
		_skippedQuestion = skippedQuestion;
		
		if (_questionNumber == 10) {
			_last = true;
		} else {
			_last = false;
		}
	}
	
	public Scene getFeedBackView(int width, int height) {
		
		_width = width;
		_height = height;
		
		BorderPane layout;
		Label feedback;
		Button toMenu, toQuestion, skipQuestion;
		VBox feedbackLayout, toMenuLayout, toQuestionLayout;
		
		skipQuestion = new Button("Skip Question");
		
		feedbackLayout = new VBox();
		toMenuLayout = new VBox(10);
		toQuestionLayout = new VBox();
		
		//checking if something was said for recout.mlf
		_incorrect = TaitaiModel.readRecoutFile();
		if (_incorrect.equals(" [-8570]  CompleteRecognition: No observations processed in HVite")) {
			_incorrect = "nothing";
		}
		
		// logic for correct message and text alignment
		if (_skippedQuestion) {
			feedback = new Label("You skipped the question, you received no marks.");
			toQuestion = new Button("Next Question");
			feedback.setWrapText(true);
			feedback.setTextAlignment(TextAlignment.CENTER);
			feedback.getStyleClass().add("label-incorrect");
		} else {
			if (_correct && !_last) {
				feedback = new Label("Correct!");
				toQuestion = new Button("Next Question");
				feedback.setWrapText(true);
				feedback.setTextAlignment(TextAlignment.CENTER);
				feedback.getStyleClass().add("label-correct");
			} else if (_firstTry && !_correct) {
				
				feedback = new Label("Try Again, you said " + _incorrect + ".");
				toQuestion = new Button("Retry");
				feedback.setWrapText(true);
				feedback.setTextAlignment(TextAlignment.CENTER);
				feedback.getStyleClass().add("label-tryAgain");
				skipQuestion.getStyleClass().add("button-back");
				
				// skip question button
				skipQuestion.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						Boolean confirmation;
						confirmation = _cb2.displayBox("Back to Menu", "   Are you sure you wish to skip this question? \n	You will not be given a mark for this question.   ");
						if (confirmation) {
							QuizView qv = new QuizView(true,  _questionNumber + 1, _level, _numCorrect, TaitaiModel.startTest(_level));
							Taitai.changeScene(qv.getQuizView(width, height));
							}
						}
					});
							
				toMenuLayout.getChildren().add(skipQuestion);
			} else if (_last && _correct) {
				feedback = new Label("Correct!");
				toQuestion = new Button("Finish Quiz");
				feedback.setWrapText(true);
				feedback.setTextAlignment(TextAlignment.CENTER);
				feedback.getStyleClass().add("label-correct");
			} else if (_last && !_correct) {
				feedback = new Label("Incorrect, you said " + _incorrect + ".");
				toQuestion = new Button("Finish Quiz");
				feedback.setWrapText(true);
				feedback.setTextAlignment(TextAlignment.CENTER);
				feedback.getStyleClass().add("label-incorrect");
			} else {
				feedback = new Label("Incorrect, you said " + _incorrect + ".");
				toQuestion = new Button("Next Question");
				feedback.setWrapText(true);
				feedback.setTextAlignment(TextAlignment.CENTER);
				feedback.getStyleClass().add("label-incorrect");
			}
		}
			
		
		layout = new BorderPane();
		toMenu = new Button("Go Back to Menu");
		
		// next question action listener
		toQuestion.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) { // make questions asked appropriate
				if (_skippedQuestion && !_last) {
					QuizView qv = new QuizView(true,  _questionNumber + 1, _level, _numCorrect, TaitaiModel.startTest(_level));
					Taitai.changeScene(qv.getQuizView(_width, _height));
				} else if (_correct && !_last) {
					QuizView qv = new QuizView(true,  _questionNumber + 1, _level, _numCorrect, TaitaiModel.startTest(_level));
					Taitai.changeScene(qv.getQuizView(_width, _height));
				} else if (_firstTry && !_correct) {
					QuizView qv = new QuizView(false, _questionNumber, _level, _numCorrect, _expression);
					Taitai.changeScene(qv.getQuizView(_width, _height));
				} else if (_last) {
					TaitaiModel.saveStats(_numCorrect, _level);
					if ((_level == 1) && (_numCorrect >= 8)) {
						FinishedView fv = new FinishedView(true, _numCorrect);
						Taitai.changeScene(fv.getFinishedView(_width, _height));
					} else {
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
				Boolean confirmation;
				confirmation = _cb.displayBox("Back to Menu", "   Are you sure you wish to return to the menu? \n	All of your progress will be lost.   ");
				if (confirmation) {
					MainMenuView sv = new MainMenuView();
					Taitai.changeScene(sv.getMainMenuView(width, height));
				}
			}
		});
		
		
		
		
		// look and feel
		
		
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
