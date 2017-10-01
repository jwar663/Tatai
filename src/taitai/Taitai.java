package taitai;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import taitai.view.FeedBackView;
import taitai.view.MenuView;
import taitai.view.QuizView;

/**
 * Taitai Maori number pronounciation practiser
 * @author Matthew Taylor
 */
public class Taitai extends Application {

	private final static int HEIGHT = 700;
	private final static int WIDTH = 850;

	private static Stage _window;
	private Scene _menu;

	@Override
	public void start(Stage primaryStage) {

		try {
			String command;
			command = "mkdir stats";
			ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);
			Process process = pb.start();
			command = " >> stats/.level1";
			pb = new ProcessBuilder("bash", "-c", command);
			process = pb.start();
			command = ">> stats/.level2";
			pb = new ProcessBuilder("bash", "-c", command);
			process = pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//initilising the gui
		MenuView mv = new MenuView();
		_menu = mv.getMenuView(WIDTH, HEIGHT);

		_window = primaryStage;
		_window.setTitle("Taitai");
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