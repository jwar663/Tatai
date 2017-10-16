package taitai.view;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.*;
import taitai.Taitai;
import taitai.TaitaiModel;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;

/** 
 * Menu GUI
 * @author Matthew Taylor
 */
public class MainMenuView {
	
	//private ErrorBox _eb = new ErrorBox();
	private Scene _mainMenu;
	Button _practice, _quiz, _stats, _custom;
	
	public Scene getMainMenuView(int width, int height) {
		
		Label welcome;
		
		BorderPane layout;
		VBox buttonLayout, titleLayout;
		welcome = new Label("Welcome to Tātai!");
		_practice = new Button("Practice Mode");
		_quiz = new Button("Quiz Mode");
		_stats = new Button("View Statistics");
		_custom = new Button("Create Custom Questions");
		layout = new BorderPane();
		titleLayout = new VBox();
		buttonLayout = new VBox(40);
		
		welcome.getStyleClass().add("label-welcome");
		_practice.getStyleClass().add("button-menu");
		_quiz.getStyleClass().add("button-menu");
		_stats.getStyleClass().add("button-menu");
		_custom.getStyleClass().add("button-menu");
		
		// goes to stats view
		_stats.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//testing error box
				//_eb.displayBox("sfjashfbhadfksdavbkdsahbvksvsadjkdvb");
				StatsView sv = new StatsView();
				Taitai.changeScene(sv.getStatsView(width, height));
			}
		});
		// button event goes to practice
		_practice.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				MenuView mv = new MenuView(1);
				Taitai.changeScene(mv.getMenuView(width, height));
			}
		});
		// button event goes to quiz
		_quiz.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				MenuView mv = new MenuView(2);
				Taitai.changeScene(mv.getMenuView(width, height));
			}
		});
		// button event goes to quiz
				_custom.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						//create new custom question class
					AddCustomQuestionView cqv = new AddCustomQuestionView();
						Taitai.changeScene(cqv.getAddCustomQuestionView(width, height));
					}
				});
		
		buttonLayout.setAlignment(Pos.TOP_CENTER);
		titleLayout.setAlignment(Pos.CENTER);
		
		buttonLayout.setPadding(new Insets(0, 0, 20, 0));
		
		titleLayout.getChildren().add(welcome);
		buttonLayout.getChildren().addAll(_practice, _quiz, _stats, _custom);
		layout.setTop(titleLayout);
		layout.setCenter(buttonLayout);
		
		_mainMenu = new Scene(layout, width, height);
		_mainMenu.getStylesheets().add("taitai/view/TaitaiTheme.css");
		
		return _mainMenu;
	}
	
}
