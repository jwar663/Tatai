
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
import javafx.animation.Timeline;

/**
 * Quiz view Gui
 * @author Matthew
 */
public class QuizView {

	private Scene _quiz;
	private int _number, _questionNumber, _level, _numCorrect;
	private boolean _clickedRecord, _firstTry, _correct;
	private Button _record;
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
	}
	
	/*
	 * gets everthing for approbriate gui
	 */
	public Scene getQuizView(int width, int height) {
		
		// setting up elements of gui
		BorderPane layout;
		Label question;
		Button toMenu, listen, submit;
		VBox questionLayout, toMenuLayout, buttonsLayout;
		
		layout = new BorderPane();
		toMenu = new Button("Go Back to Menu");
		_record = new Button("Record");
		question = new Label(_number + "");
		
		questionLayout = new VBox();
		toMenuLayout = new VBox();
		buttonsLayout = new VBox(20);
		
		// record button event handler, records and starts timer
		_record.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				doTime();
				String command = "sh /home/se206/Documents/HTK/MaoriNumbers/GoSpeech";
				createNewProcess(command);
				_wordSaid = readRecoutFile(); // triedd to make this fit but im unable to execute code or anything so dont know if its correct
				// just trying to read user input from mike
				// wanted to have threads here but its too difficult to implement without being able to run any code
				
				
				listen = new Button("Playback");
				submit = new Button("Submit Answer");
				
				// submit button submits question and displays next view
				submit.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if (pronouncedCorrectlyBoolean(_wordSaid, TaitaiModel.getWordRequired(_number))) {
							correct = true;
						} else {
							correct = false;
						}
						
						FeedBackView fbv = new FeedBackView(_firstTry, _questionNumber, _level, _numCorrect, correct, _number);
						Taitai.changeScene(fbv.getFeedBackView(width, height));
					}
				});
				
				// listen button event handler 
				listen.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
					}
				});
				
				listen.getStyleClass().add("button-function");
				submit.getStyleClass().add("button-menu");
				
				buttonsLayout.getChildren().addAll(listen, submit);
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
	
	// countdown timer for recording
	private void doTime() {
		Integer seconds = 3;
		Timeline time = new Timeline();
		time.setCycleCount(Timeline.INDEFINITE);
		if (time != null) {
			time.stop();
		}
		
		KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event) {
				seconds--;
				_record.setText("Recoriding..." + seconds.toString());
				if (seconds == 0) {
					time.stop();
				}
			}
			time.getKeyFrames().add(frame);
			time.playFromStart();
		});
	}
	
}