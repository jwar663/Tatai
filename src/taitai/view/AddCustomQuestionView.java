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
 * The add custom question view for the GUI
 * @author Matthew Taylor, Jaedyn Ward
 */

public class AddCustomQuestionView {

	private Scene _customQuestionView;
	int _width, _height;
	private static PromptBox _pb = new PromptBox();
	
	//checks what sort of error or what the answer to the expression is
	public static void checkExpression(String expression, String questionType) {
		String message = TaitaiModel.computeExpression(expression);
		if(message.equals("error bash")) {
			_pb.displayBox("Error", "You provided an invalid expression");
		} else if(message.equals("error bounds")) {
			_pb.displayBox("Error", "Your expression equates to more than 99 or less than 1");
		} else {
			TaitaiModel.saveQuestion(expression, questionType);
			_pb.displayBox("Success", "Success: Your question has now been added!");
			}
		}
	
	public Scene getAddCustomQuestionView(int width, int height) {
		_width = width;
		_height = height;
		
		BorderPane layout;
		Label enter, warning, note;
		Button toMenu, submitQuestion;
		VBox cqLayout, toMenuLayout;
		TextField customExpressionText;
		
		//initialising jfx features
		layout = new BorderPane();
		toMenu = new Button("Go Back to Menu");
		customExpressionText = new TextField("Please enter your expression here");
		enter = new Label("Create a Custom Question");
		warning = new Label("Make sure your expression is valid and has an integer answer between 1-99");
		submitQuestion = new Button("Submit");
		note = new Label("Note: Your answer will be rounded appropriately if the answer is not an integer.");
		
		//functionality for submit question  button
		submitQuestion.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				final String customExpression = customExpressionText.getText();
				checkExpression(customExpression, "custom");		
			}
		});
		
		//functionality for menu button
		toMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				MainMenuView sv = new MainMenuView();
				Taitai.changeScene(sv.getMainMenuView(width, height));
			}
		});
		
		
		// look and feel
		cqLayout = new VBox(20);
		toMenuLayout = new VBox();
		
		toMenu.getStyleClass().add("button-back");
		warning.getStyleClass().add("label-root");
		note.getStyleClass().add("label-root");
		enter.getStyleClass().add("label-custom");
		submitQuestion.getStyleClass().add("button-function");
		
		customExpressionText.setMaxWidth(width - 40);
		
		cqLayout.setAlignment(Pos.CENTER);
		toMenuLayout.setAlignment(Pos.BOTTOM_RIGHT);
		
		cqLayout.setPadding(new Insets(10, 0, 20, 0));
		toMenuLayout.setPadding(new Insets(0, 40, 40, 0));
		
		cqLayout.getChildren().addAll(enter, warning, note, customExpressionText, submitQuestion);
		toMenuLayout.getChildren().add(toMenu);
		
		layout.setCenter(cqLayout);
		layout.setBottom(toMenuLayout);
		
		_customQuestionView = new Scene(layout, width, height);
		_customQuestionView.getStylesheets().add("taitai/view/TaitaiTheme.css");
		return _customQuestionView;
	}
}
