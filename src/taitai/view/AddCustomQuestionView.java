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
 * The add custom question view for the GUI
 * @author Matthew Taylor, Jaedyn Ward
 */

public class AddCustomQuestionView {

	private Scene _customQuestionView;
	int _width, _height;
	private static PromptBox _pb = new PromptBox();
	
	//checks what sort of error or what the answer to the expression is
		//need to implement adding the equation.
		//needs to be moved to the class that handles creating custom questions
		public static void checkExpression(String expression, String questionType) {
			String message = TaitaiModel.computeExpression(expression);
			if(message.equals("error bash")) {
				System.out.println("you provided an invalid expression");
				//add pop-up error box
				_pb.displayBox("Error", "You provided an invalid expression");
			} else if(message.equals("error bounds")) {
				System.out.println("your expression equates to more than 99 or less than 1");
				//add pop-up error box
				_pb.displayBox("Error", "Your expression equates to more than 99 or less than 1");
			} else {
				System.out.println(message);
				TaitaiModel.customQuestions.addElement(expression);
				TaitaiModel.saveQuestion(expression, questionType);
				_pb.displayBox("Success", "Success: Your question has now been added!");
			}
		}
	
	public Scene getAddCustomQuestionView(int width, int height) {
		_width = width;
		_height = height;
		
		BorderPane layout;
		Label enter, warning;
		Button toMenu, submitQuestion;
		VBox cqLayout, toMenuLayout;
		TextField customExpressionText;
		//final String customExpression;
		
		layout = new BorderPane();
		toMenu = new Button("Go Back to Menu");
		customExpressionText = new TextField("Please enter your expression here");
		enter = new Label("Please enter an expression below");
		warning = new Label("Make sure your expression is valid and has an answer between 1-99");
		submitQuestion = new Button("Submit your custom expression");
		
		submitQuestion.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				final String customExpression = customExpressionText.getText();
				checkExpression(customExpression, "custom");		
			}
		});
		
		toMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				MainMenuView sv = new MainMenuView();
				Taitai.changeScene(sv.getMainMenuView(width, height));
			}
		});
		
		
		// look and feel
		cqLayout = new VBox();
		toMenuLayout = new VBox();
		
		toMenu.getStyleClass().add("button-back");
		warning.getStyleClass().add("label-root");
		enter.getStyleClass().add("label-welcome");
		submitQuestion.getStyleClass().add("button-function");
		
		cqLayout.setAlignment(Pos.CENTER);
		toMenuLayout.setAlignment(Pos.BOTTOM_RIGHT);
		
		cqLayout.setPadding(new Insets(10, 0, 20, 0));
		toMenuLayout.setPadding(new Insets(0, 40, 40, 0));
		
		cqLayout.getChildren().addAll(enter, warning, customExpressionText, submitQuestion);
		toMenuLayout.getChildren().add(toMenu);
		
		layout.setCenter(cqLayout);
		layout.setBottom(toMenuLayout);
		
		_customQuestionView = new Scene(layout, width, height);
		_customQuestionView.getStylesheets().add("taitai/view/TaitaiTheme.css");
		return _customQuestionView;
	}
}
