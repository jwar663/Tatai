package taitai;

import java.io.IOException;
import java.util.Vector;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import taitai.view.MenuView;

/**
 * Taitai Maori number pronounciation practiser
 * @author Matthew Taylor, Jaedyn Ward
 */

public class Taitai extends Application {

	private final static int HEIGHT = 650;
	private final static int WIDTH = 850;

	private static Stage _window;
	private Scene _menu;
	

	@Override
	public void start(Stage primaryStage) {
		
		//initialises maoriNumbers vector.
		TaitaiModel.assignMaoriNumbers();

		//creates folders/files for statistics and questions
		TaitaiModel.createAllFiles();
		
		//initialising the gui
		MenuView mv = new MenuView();
		_menu = mv.getMenuView(WIDTH, HEIGHT);

		_window = primaryStage;
		_window.setTitle("Tātai");
		_window.setScene(_menu);
		_window.centerOnScreen();
		_window.setResizable(false);
		_window.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void changeScene(Scene scene) {
		_window.setScene(scene);
	}

	public static int getSHeight() {
		return HEIGHT;
	}

	public static int getSWidth() {
		return WIDTH;
	}
}