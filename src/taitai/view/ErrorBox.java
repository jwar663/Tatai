package taitai.view;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.control.*;
import javafx.geometry.*;

/**
 * Confirmation box for action
 * @author Matthew Taylor, Jaedyn Ward
 */

public class ErrorBox {
	
    private static Stage _window;

    public static void displayBox(String error) {
        
    	Label errorMessage;
    	Button ok;
    	
    	VBox layout;
    	
    	errorMessage = new Label("Error: " + error);
        ok = new Button("OK");
        
        errorMessage.setFont(new Font(14));
        
        layout = new VBox(10);

        ok.setOnAction(e -> {
            _window.close();
        });

        layout.getChildren().addAll(errorMessage, ok);
        
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        
        _window = new Stage();
        _window.initModality(Modality.APPLICATION_MODAL);
        _window.setTitle("Error");
        _window.setMinHeight(150);
       // _window.setMinWidth(400);
        _window.setScene(scene);
        _window.centerOnScreen();
        _window.showAndWait();
        
    }
}
