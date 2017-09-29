package taitai.view;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.*;
import taitai.Taitai;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;

/** 
 * Menu GUI
 * @author Matthew Taylor
 */
public class MenuView {
	
	private Scene _menu;
	Button _level1, _level2, _stats;
	
	public Scene getMenuView(int width, int height) {
		
		Label welcome;
		
		BorderPane layout;
		VBox buttonLayout, titleLayout;
		welcome = new Label("Welcome to Taitai!");
		_level1 = new Button("Practice Numbers 1-9");
		_level2 = new Button("Practice Numbers 1-99");
		_stats = new Button("View Statistics");
		layout = new BorderPane();
		titleLayout = new VBox();
		buttonLayout = new VBox(40);
		
		welcome.getStyleClass().add("label-welcome");
		_level1.getStyleClass().add("button-menu");
		_level2.getStyleClass().add("button-menu");
		_stats.getStyleClass().add("button-menu");
		
		_stats.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				StatsView sv = new StatsView();
				Taitai.changeScene(sv.getStatsView(width, height));
			}
		});
		
		_level1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				QuizView qv = new QuizView(true, 1, 1);
				Taitai.changeScene(qv.getQuizView(width, height));
				// more
			}
		});
		
		_level2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				QuizView qv = new QuizView(true, 1, 2);
				Taitai.changeScene(qv.getQuizView(width, height));
				// mores
			}
		});
		
		buttonLayout.setAlignment(Pos.TOP_CENTER);
		titleLayout.setAlignment(Pos.CENTER);
		
		buttonLayout.setPadding(new Insets(0, 0, 20, 0));
		
		titleLayout.getChildren().add(welcome);
		buttonLayout.getChildren().addAll(_level1, _level2, _stats);
		layout.setTop(titleLayout);
		layout.setCenter(buttonLayout);
		
		_menu = new Scene(layout, width, height);
		_menu.getStylesheets().add("taitai/view/TaitaiTheme.css");
		
		return _menu;
	}
	
}
