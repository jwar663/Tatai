package taitai;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import taitai.view.MainMenuView;

/**
 * Taitai Maori number pronounciation and mathematics practiser
 * @author Matthew Taylor, Jaedyn Ward
 */

public class Taitai extends Application {

	private final static int HEIGHT = 650;
	private final static int WIDTH = 850;

	private static Stage _window;
	private Scene _mainMenu;
	

	@Override
	public void start(Stage primaryStage) {
		
		//initialises various vector.
		TaitaiModel.assignMaoriNumbers();
		TaitaiModel.assignRegularQuestions();
		TaitaiModel.assignCustomQuestions();

		//creates folders/files for statistics and questions
		TaitaiModel.createAllFiles();
		
		//initialising the gui
		MainMenuView mv = new MainMenuView();
		_mainMenu = mv.getMainMenuView(WIDTH, HEIGHT);

		_window = primaryStage;
		_window.setTitle("Tātai");
		_window.setScene(_mainMenu);
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