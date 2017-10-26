
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
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

/**
 * Quiz view Gui
 * @author Matthew Taylor, Jaedyn Ward
 */
public class QuizView {

	private Scene _quiz;
	private int _questionNumber, _level, _numCorrect;
	private boolean _clickedRecord, _firstTry, _correct, _isAdded;
	private Button _record, _listen, _submit;
	private String _wordSaid, _expression;
	private ConfirmBox _cb = new ConfirmBox();
	private ConfirmBox _cb2 = new ConfirmBox();

	/*
	 * constructor
	 */
	public QuizView(boolean firstTry, int questionNumber, int level, int numCorrect, String expression) {
		_questionNumber = questionNumber;
		_firstTry = firstTry;
		_clickedRecord = false;
		_level = level;
		_numCorrect = numCorrect;
		_expression = expression;
		_isAdded = false;
	}

	/*
	 * gets everything for appropriate gui
	 */
	public Scene getQuizView(int width, int height) {

		// setting up elements of gui
		BorderPane layout;
		Label question, questionNumberLabel, dynamicScore;
		Button toMenu, skipQuestion;
		VBox questionLayout, toMenuLayout, buttonsLayout, counterLayout;

		layout = new BorderPane();
		toMenu = new Button("Go Back to Menu");
		skipQuestion = new Button("Skip Question");
		_record = new Button("Record");
		question = new Label(_expression + "");
		questionNumberLabel = new Label("Question " + _questionNumber);
		dynamicScore = new Label(_numCorrect + "/" + (_questionNumber - 1));

		counterLayout = new VBox();
		questionLayout = new VBox();
		toMenuLayout = new VBox(10);
		buttonsLayout = new VBox(20);
		
		_listen = new Button("Playback");
		_submit = new Button("Submit Answer");
		
		// submit button submits question and displays next view
		_submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
					if (TaitaiModel.pronouncedCorrectlyBoolean(_wordSaid, TaitaiModel.getWordRequired(_expression))) {
						_correct = true;
						_numCorrect++;
					} else {
						_correct = false;
					}
					FeedBackQuizView fbv = new FeedBackQuizView(_firstTry, _questionNumber, _level, _numCorrect, _correct, _expression, false);
					Taitai.changeScene(fbv.getFeedBackView(width, height));
			}
		});
		
		// listen button event handler 
		_listen.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
					_listen.setText("Playing...");
					TaitaiModel.playAudio();
					_listen.setText("Playback");
			}
		});

		
		// record button event handler, records and starts timer
		_record.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				_record.setText("Recording...");
				if (_level == 1) {
					TaitaiModel.recordAudio("3");
				} else {
					TaitaiModel.recordAudio("5");
				}
				_record.setText("Record");
				TaitaiModel.writeToRecout();
				_wordSaid = TaitaiModel.readRecoutFile();
				// wanted to have threads here but its too difficult to implement without being able to run any code

				if (!_isAdded) {
					_listen.getStyleClass().add("button-function");
					_submit.getStyleClass().add("button-menu");
					buttonsLayout.getChildren().addAll(_listen, _submit);
				}
				
				_isAdded = true;
				_record.setText("Record");
			}
		});

		// to menu button
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
		
		// skip question button
		skipQuestion.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Boolean confirmation;
				confirmation = _cb2.displayBox("Skip Question", "   Are you sure you wish to skip this question? \n	You will not be given a mark for this question.   ");
				if (confirmation) {
					FeedBackQuizView fbv = new FeedBackQuizView(_firstTry, _questionNumber, _level, _numCorrect, _correct, _expression, true);
					Taitai.changeScene(fbv.getFeedBackView(width, height));
				}
			}
		});


		// format
		toMenu.getStyleClass().add("button-back");
		skipQuestion.getStyleClass().add("button-back");
		_record.getStyleClass().add("button-function");
		question.getStyleClass().add("label-quiz");
		dynamicScore.getStyleClass().add("label-dynamicScore");
		questionNumberLabel.getStyleClass().add("label-questionNumber");

		//added extra labels, need to align them properly.
		questionLayout.setAlignment(Pos.CENTER);
		buttonsLayout.setAlignment(Pos.TOP_CENTER);
		toMenuLayout.setAlignment(Pos.BOTTOM_RIGHT);
		//counterLayout.setAlignment(Pos.TOP_LEFT);

		
		buttonsLayout.setPadding(new Insets(10, 0, 20, 0));
		toMenuLayout.setPadding(new Insets(0, 40, 40, 0));
		
		//counterLayout.setSpacing(10);

		buttonsLayout.getChildren().add(_record);
		questionLayout.getChildren().add(question);
		toMenuLayout.getChildren().addAll(skipQuestion, toMenu);
		//counterLayout.getChildren().addAll(questionNumberLabel, dynamicScore);

		layout.setTop(questionLayout);
		layout.setCenter(buttonsLayout);
		layout.setBottom(toMenuLayout);
		//layout.setLeft(counterLayout);
		


		_quiz = new Scene(layout, width, height);
		_quiz.getStylesheets().add("taitai/view/TaitaiTheme.css");
		return _quiz;

	}

}
