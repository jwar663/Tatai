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
 * The feedback scene for practice for the GUI
 * @author Matthew Taylor, Jaedyn Ward
 */
public class FeedBackPracticeView {

	private Scene _feedbackPractice;
	private boolean _correct;
	int _questionNumber, _width, _height, _level, _numCorrect;
	String _incorrect, _expression, _correctAnswer;
	
	/*
	 * constructor
	 */
	public FeedBackPracticeView(int questionNumber, int level, int numCorrect, boolean correct, String expression) {
		_correct = correct;  
		_questionNumber = questionNumber;
		_level = level;
		_numCorrect = numCorrect;
		_expression = expression;
	}
	
	public Scene getFeedBackPracticeView(int width, int height) {
		
		_width = width;
		_height = height;
		
		BorderPane layout;
		Label feedback;
		Button toMenu, toQuestion;
		VBox feedbackLayout, toMenuLayout, toQuestionLayout;
		
		_correctAnswer = TaitaiModel.getWordRequired(_expression);
		
		//checking if something was said for recout.mlf
		_incorrect = TaitaiModel.readRecoutFile();
		if (_incorrect.equals(" [-8570]  CompleteRecognition: No observations processed in HVite")) {
			_incorrect = "nothing";
		}
		
		// logic for correct/incorrect message and text alignment
		if (_correct) {
			feedback = new Label("Correct!");
			toQuestion = new Button("Next Question");
			feedback.setWrapText(true);
			feedback.setTextAlignment(TextAlignment.CENTER);
			feedback.getStyleClass().add("label-correct");
		} else {
			feedback = new Label("Incorrect, you said " + _incorrect + "." + "\n" + "The required answer was " + _correctAnswer + ".");
			toQuestion = new Button("Next Question");
			feedback.setWrapText(true);
			feedback.setTextAlignment(TextAlignment.CENTER);
			feedback.getStyleClass().add("label-incorrect");
		}	
		
		layout = new BorderPane();
		toMenu = new Button("Go Back to Menu");
		
		// next question action listener
		toQuestion.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) { // make questions asked appropriate
				if (_correct) {
					PracticeView qv = new PracticeView(_questionNumber + 1, _level, _numCorrect, TaitaiModel.startTest(_level));
					Taitai.changeScene(qv.getPracticeView(_width, _height));
				} else {
					PracticeView qv = new PracticeView(_questionNumber + 1, _level, _numCorrect, TaitaiModel.startTest(_level));
					Taitai.changeScene(qv.getPracticeView(_width, _height));
				}	
			}
		});
		
		//menu button functionality
		toMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				MainMenuView sv = new MainMenuView();
				Taitai.changeScene(sv.getMainMenuView(width, height));
			}
		});
		
		
		//look and feel
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
		
		_feedbackPractice = new Scene(layout, width, height);
		_feedbackPractice.getStylesheets().add("taitai/view/TaitaiTheme.css");
		return _feedbackPractice;
	}
	
}
