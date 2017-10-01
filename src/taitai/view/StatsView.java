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
 * @author Matthew Taylor
 */
public class StatsView {

	private Scene _stats;
	private String[] _to9, _to99; // needs to get input of these 
	private ConfirmBox _cb = new ConfirmBox();
	private ListView<String> _list9, _list99;

	public Scene getStatsView(int width, int height) {

		BorderPane layout;
		Label statistics, to9Title, to99Title;
		Button toMenu, clearStats;
		VBox list9Layout, list99Layout, statisticsLayout;
		HBox toMenuLayout, listsLayout;
		

		if (TaitaiModel.readStats("stats/.level1") != null) {
			_to9 = TaitaiModel.readStats("stats/.level1");
		}
		if (TaitaiModel.readStats("stats/.level2") != null) {
			_to99 = TaitaiModel.readStats("stats/.level2");
		}

		layout = new BorderPane();
		clearStats = new Button("Clear Stats");
		toMenu = new Button("Go Back to Menu");
		_list9 = new ListView<String>();
		_list99 = new ListView<String>();
		to9Title = new Label("Stats for numbers 1-9");
		to99Title = new Label("Stats for numbers 1-99");
		statistics = new Label("Statistics");

		_list9 = addArrayToList(_list9, _to9);
		_list99 = addArrayToList(_list99, _to99);

		toMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				MenuView sv = new MenuView();
				Taitai.changeScene(sv.getMenuView(width, height));
			}
		});

		clearStats.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Boolean confirmation;
				confirmation = _cb.displayBox("Clear Statistics", "   Are you sure you wish to clear all your statistics?   ");
				if (confirmation) {
					TaitaiModel.clearStats();
					MenuView sv = new MenuView();
					Taitai.changeScene(sv.getMenuView(width, height));
				}
			}
		});

		statisticsLayout = new VBox();
		toMenuLayout = new HBox(20);
		list9Layout = new VBox(15);
		list99Layout = new VBox(15);
		listsLayout = new HBox(20);

		clearStats.getStyleClass().add("button-function");
		toMenu.getStyleClass().add("button-back");
		statistics.getStyleClass().add("label-stats");

		listsLayout.setAlignment(Pos.TOP_CENTER);
		statisticsLayout.setAlignment(Pos.CENTER);
		toMenuLayout.setAlignment(Pos.BOTTOM_RIGHT);

		statisticsLayout.setPadding(new Insets(30, 0, 20, 0));
		toMenuLayout.setPadding(new Insets(0, 40, 40, 0));

		statisticsLayout.getChildren().add(statistics);
		toMenuLayout.getChildren().add(clearStats);
		toMenuLayout.getChildren().add(toMenu);
		list9Layout.getChildren().addAll(to9Title, _list9);
		list99Layout.getChildren().addAll(to99Title, _list99);
		listsLayout.getChildren().addAll(list9Layout, list99Layout);

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
