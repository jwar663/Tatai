package taitai.view;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.*;
import taitai.CurrentQuiz;
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
public class MenuView {

	//private ErrorBox _eb = new ErrorBox();
	private Scene _menu;
	Button _level1, _level2, _level3, _level4, createQuestions, _stats;

	public Scene getMenuView(int width, int height) {

		Label welcome;

		BorderPane layout;
		VBox buttonLayout, titleLayout;
		welcome = new Label("Welcome to Tātai!");
		_level1 = new Button("Practice Numbers 1-9");
		_level2 = new Button("Practice Numbers 1-99");
		_level3 = new Button("Practice Maths");
		_level4 = new Button("Custom Questions");
		_stats = new Button("View Statistics");
		layout = new BorderPane();
		titleLayout = new VBox();
		buttonLayout = new VBox(25);

		welcome.getStyleClass().add("label-welcome");
		_level1.getStyleClass().add("button-menu");
		_level2.getStyleClass().add("button-menu");
		_level3.getStyleClass().add("button-menu");
		_level4.getStyleClass().add("button-menu");
		_stats.getStyleClass().add("button-menu");

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
		// button event goes to level 1 quiz
		_level1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				QuizView qv = new QuizView(new CurrentQuiz(1));
				Taitai.changeScene(qv.getQuizView(width, height));
			}
		});
		// button event goes to level 2 quiz
		_level2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				QuizView qv = new QuizView(new CurrentQuiz(2));
				Taitai.changeScene(qv.getQuizView(width, height));
			}
		});

		// button event goes to level 3 quiz
		_level3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				QuizView qv = new QuizView(new CurrentQuiz(3));
				Taitai.changeScene(qv.getQuizView(width, height));
			}
		});

		// button event goes to level 3 quiz
		_level4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				QuizView qv = new QuizView(new CurrentQuiz(4));
				Taitai.changeScene(qv.getQuizView(width, height));
			}
		});

		buttonLayout.setAlignment(Pos.TOP_CENTER);
		titleLayout.setAlignment(Pos.CENTER);

		buttonLayout.setPadding(new Insets(0, 0, 20, 0));

		titleLayout.getChildren().add(welcome);
		buttonLayout.getChildren().addAll(_level1, _level2, _level3, _level4, _stats);
		layout.setTop(titleLayout);
		layout.setCenter(buttonLayout);

		_menu = new Scene(layout, width, height);
		_menu.getStylesheets().add("taitai/view/TaitaiTheme.css");

		return _menu;
	}

}