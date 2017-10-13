package taitai.view;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.control.*;
import javafx.geometry.*;

/**
 * Error box for notifying users of errors before continuing.
 * @author Matthew Taylor, Jaedyn Ward
 */

public class PromptBox {
	
    private static Stage _window;

    public static void displayBox(String title, String message) {
        
    	Label messageLabel;
    	Button ok;
    	
    	VBox layout;
    	
    	messageLabel = new Label(message);
        ok = new Button("OK");
        
        messageLabel.setFont(new Font(14));
        
        layout = new VBox(10);

        ok.setOnAction(e -> {
            _window.close();
        });

        layout.getChildren().addAll(messageLabel, ok);
        
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        
        _window = new Stage();
        _window.initModality(Modality.APPLICATION_MODAL);
        _window.setTitle(title);
        _window.setMinHeight(150);
       // _window.setMinWidth(400);
        _window.setScene(scene);
        _window.centerOnScreen();
        _window.showAndWait();
        
    }
}
