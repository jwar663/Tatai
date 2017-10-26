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
	private LineChartView _lcv = new LineChartView();
	private ListView<String> _listLevel1, _listLevel2, _listLevel3, _listLevel4, _listLevel5;

	public Scene getStatsView(int width, int height) {

		BorderPane layout;
		Label statistics, level1Title, level2Title, level3Title, level4Title, level5Title;
		Button toMenu, clearStats, graphLevel1, graphLevel2, graphLevel3, graphLevel4, graphLevel5;
		VBox listLevel1Layout, listLevel2Layout, listLevel3Layout, listLevel4Layout, listLevel5Layout, statisticsLayout;
		HBox toMenuLayout, listsLayout;
		

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
		level1Title = new Label("Stats for numbers 1-99");
		level2Title = new Label("Stats for Addition and Subtraction");
		level3Title = new Label("Stats for Multiplication and Division");
		level4Title = new Label("Stats for Combination");
		level5Title = new Label("Stats for Custom");
		statistics = new Label("Statistics");

		_listLevel1 = addArrayToList(_listLevel1, _stringLevel1);
		_listLevel2 = addArrayToList(_listLevel2, _stringLevel2);
		_listLevel3 = addArrayToList(_listLevel3, _stringLevel3);
		_listLevel4 = addArrayToList(_listLevel4, _stringLevel4);
		_listLevel5 = addArrayToList(_listLevel5, _stringLevel5);

		toMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				MainMenuView sv = new MainMenuView();
				Taitai.changeScene(sv.getMainMenuView(width, height));
			}
		});
		
		graphLevel1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				_lcv.displayChart("Numbers to 99", _intLevel1);
			}
		});
		
		graphLevel2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				_lcv.displayChart("Addition and Subtraction", _intLevel2);
			}
		});
		
		graphLevel3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				_lcv.displayChart("Multiplication and Division", _intLevel3);
			}
		});
		
		graphLevel3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				_lcv.displayChart("Combination", _intLevel3);
			}
		});
		
		graphLevel5.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				_lcv.displayChart("Custom", _intLevel5);
			}
		});

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

		statisticsLayout = new VBox();
		toMenuLayout = new HBox(20);
		listLevel1Layout = new VBox(15);
		listLevel2Layout = new VBox(15);
		listLevel3Layout = new VBox(15);
		listLevel4Layout = new VBox(15);
		listLevel5Layout = new VBox(15);
		listsLayout = new HBox(10);

		clearStats.getStyleClass().add("button-function");
		toMenu.getStyleClass().add("button-back");
		statistics.getStyleClass().add("label-stats");
		graphLevel1.getStyleClass().add("button-function");
		graphLevel2.getStyleClass().add("button-function");
		graphLevel3.getStyleClass().add("button-function");
		graphLevel4.getStyleClass().add("button-function");
		graphLevel5.getStyleClass().add("button-function");

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
