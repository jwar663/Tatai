package taitai.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LineChartView {

	private static Stage chartStage;
	
	
	public static void displayChart(String questionType, int[] statArray) {
		
		String title = "Line Chart for " + questionType;
		VBox chartLayout = new VBox();
		Scene scene = new Scene(chartLayout, 500, 450);
		Button confirm = new Button("OK");
		
		NumberAxis xAxis = new NumberAxis("Attempt Number", 1.0 , statArray.length, 1.0);
		
		NumberAxis yAxis = new NumberAxis("Score", 0.0 , 10.0, 1.0);
		
		
		LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis,yAxis);
		lineChart.setTitle(title);
		XYChart.Series<Number, Number> data = new XYChart.Series<>();
		
		for(int i = 0; i < statArray.length; i++) {
			data.getData().add(new XYChart.Data<Number, Number>(i + 1, statArray[i]));
		}
		
		confirm.setOnAction(e -> {
          chartStage.close();
        });
		
		
		confirm.setAlignment(Pos.CENTER);
		data.setName(questionType);
		chartLayout.setAlignment(Pos.CENTER);
		lineChart.getData().add(data);
		chartLayout.getChildren().addAll(lineChart, confirm);
		
		chartStage = new Stage();
		chartStage.initModality(Modality.APPLICATION_MODAL);
        chartStage.setTitle(title);
        chartStage.setScene(scene);
        chartStage.centerOnScreen();
        chartStage.showAndWait();
		
	}
	

}
