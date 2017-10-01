
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
 * @author Matthew
 */
public class QuizView {

	private Scene _quiz;
	private int _number, _questionNumber, _level, _numCorrect;
	private boolean _clickedRecord, _firstTry, _correct, _isAdded;
	private Button _record, _listen, _submit;
	private String _wordSaid;

	/*
	 * constructor
	 */
	public QuizView(boolean firstTry, int questionNumber, int level, int numCorrect, int number) {
		_questionNumber = questionNumber;
		_firstTry = firstTry;
		_clickedRecord = false;
		_level = level;
		_numCorrect = numCorrect;
		_number = number;
		_isAdded = false;
	}

	/*
	 * gets everthing for approbriate gui
	 */
	public Scene getQuizView(int width, int height) {

		// setting up elements of gui
		BorderPane layout;
		Label question;
		Button toMenu;
		VBox questionLayout, toMenuLayout, buttonsLayout;

		layout = new BorderPane();
		toMenu = new Button("Go Back to Menu");
		_record = new Button("Record");
		question = new Label(_number + "");

		questionLayout = new VBox();
		toMenuLayout = new VBox();
		buttonsLayout = new VBox(20);
		
		_listen = new Button("Playback");
		_submit = new Button("Submit Answer");
		
		// submit button submits question and displays next view
		_submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (TaitaiModel.pronouncedCorrectlyBoolean(_wordSaid, TaitaiModel.getWordRequired(_number))) {
					_numCorrect++;
					_correct = true;
				} else {
					_correct = false;
				}
				TaitaiModel.removeAudioFile();
				FeedBackView fbv = new FeedBackView(_firstTry, _questionNumber, _level, _numCorrect, _correct, _number);
				Taitai.changeScene(fbv.getFeedBackView(width, height));
			}
		});
		
		// listen button event handler 
		_listen.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				TaitaiModel.playAudio();
			}
		});

		
		// record button event handler, records and starts timer
		_record.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				_record.setText("Recording..");
				TaitaiModel.recordAudio("3");
				TaitaiModel.writeToRecout();
				_wordSaid = TaitaiModel.readRecoutFile(); // triedd to make this fit but im unable to execute code or anything so dont know if its correct
				// just trying to read user input from mike
				// wanted to have threads here but its too difficult to implement without being able to run any code

				if (!_isAdded) {
					_listen.getStyleClass().add("button-function");
					_submit.getStyleClass().add("button-menu");
					buttonsLayout.getChildren().addAll(_listen, _submit);
				}
				
				_isAdded = true;
				_record.setText("Record");
				// TODO Auto-generated method stub
			}
		});

		// to menu button
		toMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				MenuView sv = new MenuView();
				Taitai.changeScene(sv.getMenuView(width, height));
			}
		});

		// formattion
		toMenu.getStyleClass().add("button-back");
		_record.getStyleClass().add("button-function");
		question.getStyleClass().add("label-quiz");

		questionLayout.setAlignment(Pos.CENTER);
		buttonsLayout.setAlignment(Pos.TOP_CENTER);
		toMenuLayout.setAlignment(Pos.BOTTOM_RIGHT);

		buttonsLayout.setPadding(new Insets(10, 0, 20, 0));
		toMenuLayout.setPadding(new Insets(0, 40, 40, 0));

		buttonsLayout.getChildren().add(_record);
		questionLayout.getChildren().add(question);
		toMenuLayout.getChildren().add(toMenu);

		layout.setTop(questionLayout);
		layout.setCenter(buttonsLayout);
		layout.setBottom(toMenuLayout);


		_quiz = new Scene(layout, width, height);
		_quiz.getStylesheets().add("taitai/view/TaitaiTheme.css");
		return _quiz;

	}

}
