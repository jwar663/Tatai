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
public class ConfirmBox {

    private static boolean _confirm;
    private static Stage _window;

    public static boolean displayBox(String title, String prompt) {
        
    	Label confirmation;
    	Button yes, no;
    	
    	VBox layout;
    	HBox buttonLayout;
    	
    	confirmation = new Label(prompt);
        no = new Button("No");
        yes = new Button("Yes");
        
        confirmation.setFont(new Font(14));
        
        layout = new VBox(10);
        buttonLayout = new HBox(15);

        yes.setOnAction(e -> {
            _confirm = true;
            _window.close();
        });
        no.setOnAction(e -> {
            _confirm = false;
            _window.close();
        });

        buttonLayout.getChildren().addAll(yes, no);
        layout.getChildren().addAll(confirmation, buttonLayout);
        
        layout.setAlignment(Pos.CENTER);
        buttonLayout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        
        _window = new Stage();
        _window.initModality(Modality.APPLICATION_MODAL);
        _window.setTitle(title);
        _window.setMinHeight(150);
        _window.setScene(scene);
        _window.centerOnScreen();
        _window.showAndWait();

        return _confirm;
    }
}