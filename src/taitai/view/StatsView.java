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
 * View statistics
 * @author Matthew Taylor, Jaedyn Ward
 */
public class StatsView {

	private Scene _stats;
	private String[] _stringLevel1, _stringLevel2, _stringLevel3, _stringLevel4, _stringLevel5; // needs to get input of these 
	private int[] _intLevel1, _intLevel2, _intLevel3, _intLevel4, _intLevel5;
	private ConfirmBox _cb = new ConfirmBox();
	private PromptBox _pb = new PromptBox();
	private LineChartView _lcv = new LineChartView();
	private ListView<String> _listLevel1, _listLevel2, _listLevel3, _listLevel4, _listLevel5;

	public Scene getStatsView(int width, int height) {

		BorderPane layout;
		Label statistics, level1Title, level2Title, level3Title, level4Title, level5Title;
		Button toMenu, clearStats, graphLevel1, graphLevel2, graphLevel3, graphLevel4, graphLevel5;
		VBox listLevel1Layout, listLevel2Layout, listLevel3Layout, listLevel4Layout, listLevel5Layout, statisticsLayout;
		HBox toMenuLayout, listsLayout;
		
		//read stats from all files if they arent empty
		if (TaitaiModel.readStats("stats/.level1") != null) {
			_stringLevel1 = TaitaiModel.readStats("stats/.level1");
			_intLevel1 = TaitaiModel.convertStats(_stringLevel1);
		}
		if (TaitaiModel.readStats("stats/.level2") != null) {
			_stringLevel2 = TaitaiModel.readStats("stats/.level2");
			_intLevel2 = TaitaiModel.convertStats(_stringLevel2);
		}
		if (TaitaiModel.readStats("stats/.level3") != null) {
			_stringLevel3 = TaitaiModel.readStats("stats/.level3");
			_intLevel3 = TaitaiModel.convertStats(_stringLevel3);
		}
		if (TaitaiModel.readStats("stats/.level4") != null) {
			_stringLevel4 = TaitaiModel.readStats("stats/.level4");
			_intLevel4 = TaitaiModel.convertStats(_stringLevel4);
		}
		if (TaitaiModel.readStats("stats/.level5") != null) {
			_stringLevel5 = TaitaiModel.readStats("stats/.level5");
			_intLevel5 = TaitaiModel.convertStats(_stringLevel5);
		}
		
		layout = new BorderPane();
		clearStats = new Button("Clear Stats");
		toMenu = new Button("Go Back to Menu");
		
		graphLevel1 = new Button("View Graph");
		graphLevel2 = new Button("View Graph");
		graphLevel3 = new Button("View Graph");
		graphLevel4 = new Button("View Graph");
		graphLevel5 = new Button("View Graph");
		
		_listLevel1 = new ListView<String>();
		_listLevel2 = new ListView<String>();
		_listLevel3 = new ListView<String>();
		_listLevel4 = new ListView<String>();
		_listLevel5 = new ListView<String>();
		
		level1Title = new Label("Numbers 1-99");
		level2Title = new Label("Add and Sub");
		level3Title = new Label("Mult and Div");
		level4Title = new Label("Combination");
		level5Title = new Label("Custom");
		statistics = new Label("Statistics");

		_listLevel1 = addArrayToList(_listLevel1, _stringLevel1);
		_listLevel2 = addArrayToList(_listLevel2, _stringLevel2);
		_listLevel3 = addArrayToList(_listLevel3, _stringLevel3);
		_listLevel4 = addArrayToList(_listLevel4, _stringLevel4);
		_listLevel5 = addArrayToList(_listLevel5, _stringLevel5);
		
		//functionality for menu button
		toMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				MainMenuView sv = new MainMenuView();
				Taitai.changeScene(sv.getMainMenuView(width, height));
			}
		});
		
		//functionality for all levels to have graphs pop-up when button is pressed
		graphLevel1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (_intLevel1.length < 1) {
					_pb.displayBox("Error", "There is no data to use for the graph");
				} else {
					_lcv.displayChart("Numbers to 99", _intLevel1);
				}
			}
		});
		
		graphLevel2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (_intLevel2.length < 1) {
					_pb.displayBox("Error", "There is no data to use for the graph");
				} else {
					_lcv.displayChart("Addition and Subtraction", _intLevel2);
				}
			}
		});
		
		graphLevel3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (_intLevel3.length < 1) {
					_pb.displayBox("Error", "There is no data to use for the graph");
				} else {
					_lcv.displayChart("Multiplication and Division", _intLevel3);
				}
			}
		});
		
		graphLevel4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (_intLevel4.length < 1) {
					_pb.displayBox("Error", "There is no data to use for the graph");
				} else {
					_lcv.displayChart("Combination", _intLevel4);
				}
			}
		});
		
		graphLevel5.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (_intLevel5.length < 1) {
					_pb.displayBox("Error", "There is no data to use for the graph");
				} else {
					_lcv.displayChart("Custom", _intLevel5);
				}
			}
		});
		
		//clears all stats after confirming
		clearStats.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Boolean confirmation;
				confirmation = _cb.displayBox("Clear Statistics", "   Are you sure you wish to clear all your statistics?   ");
				if (confirmation) {
					TaitaiModel.clearStats();
					MainMenuView sv = new MainMenuView();
					Taitai.changeScene(sv.getMainMenuView(width, height));
				}
			}
		});
		
		//look and feel
		statisticsLayout = new VBox();
		toMenuLayout = new HBox(20);
		listLevel1Layout = new VBox(15);
		listLevel2Layout = new VBox(15);
		listLevel3Layout = new VBox(15);
		listLevel4Layout = new VBox(15);
		listLevel5Layout = new VBox(15);
		listsLayout = new HBox(10);
		
		_listLevel1.setMaxHeight(300);
		_listLevel2.setMaxHeight(300);
		_listLevel3.setMaxHeight(300);
		_listLevel4.setMaxHeight(300);
		_listLevel5.setMaxHeight(300);
		
		level1Title.setAlignment(Pos.CENTER);
		level2Title.setAlignment(Pos.CENTER);
		level3Title.setAlignment(Pos.CENTER);
		level4Title.setAlignment(Pos.CENTER);
		level5Title.setAlignment(Pos.CENTER);

		clearStats.getStyleClass().add("button-function");
		toMenu.getStyleClass().add("button-back");
		statistics.getStyleClass().add("label-stats");
		graphLevel1.getStyleClass().add("button-graph");
		graphLevel2.getStyleClass().add("button-graph");
		graphLevel3.getStyleClass().add("button-graph");
		graphLevel4.getStyleClass().add("button-graph");
		graphLevel5.getStyleClass().add("button-graph");

		listsLayout.setAlignment(Pos.TOP_CENTER);
		statisticsLayout.setAlignment(Pos.CENTER);
		toMenuLayout.setAlignment(Pos.BOTTOM_RIGHT);

		statisticsLayout.setPadding(new Insets(30, 0, 20, 0));
		toMenuLayout.setPadding(new Insets(0, 40, 40, 0));

		statisticsLayout.getChildren().add(statistics);
		toMenuLayout.getChildren().add(clearStats);
		toMenuLayout.getChildren().add(toMenu);
		listLevel1Layout.getChildren().addAll(level1Title, _listLevel1, graphLevel1);
		listLevel2Layout.getChildren().addAll(level2Title, _listLevel2, graphLevel2);
		listLevel3Layout.getChildren().addAll(level3Title, _listLevel3, graphLevel3);
		listLevel4Layout.getChildren().addAll(level4Title, _listLevel4, graphLevel4);
		listLevel5Layout.getChildren().addAll(level5Title, _listLevel5, graphLevel5);
		listsLayout.getChildren().addAll(listLevel1Layout, listLevel2Layout, listLevel3Layout, listLevel4Layout, listLevel5Layout);

		layout.setTop(statisticsLayout);
		layout.setCenter(listsLayout);
		layout.setBottom(toMenuLayout);

		_stats = new Scene(layout, width, height);
		_stats.getStylesheets().add("taitai/view/TaitaiTheme.css");
		return _stats;
	}
	// creates a list view out of the stats that are in a string array
	private ListView<String> addArrayToList(ListView<String> list, String[] stats) {
		if (stats != null) {
			for (int i = 0; i < stats.length; i++) {
				int num = i + 1;
				list.getItems().add(num + ".) " + stats[i] + " out of 10.");
			}
		}
		return list;
	}


}
