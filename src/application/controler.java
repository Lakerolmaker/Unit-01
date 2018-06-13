package application;

import JackeLibrary.*;
import consoleWindow.consoleFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class controler {

	public ProgressBar progressbar1;
    public TextField input;
    public Button button1;
    public ImageView img1;

	public NumberAxis yAxis;
	public CategoryAxis xAxis;
	public LineChart<String, Integer> chart;
	public XYChart.Series series = new XYChart.Series();
	
	
	public void buttonCallback(ActionEvent event) {
		System.out.println("called");
		
		Image newimage =  new Image("file:src/images/blackhorse.png");
		img1.setImage(newimage);
	
		button1.setText("yo");
		
		try {
			int parsedint = Integer.valueOf(input.getText());
			shiftSeriesYValue(series, parsedint);
		} catch (Exception e) {
			System.out.println("no number");
		}
		
		
	}
	
	private static double value = 0.0;
	private static boolean inc = true;
	
	public void initialize() {

		for(int i=0; i<100; i++){
			series.getData().add(new XYChart.Data(String.valueOf(i), 0));
        }

		chart.getData().addAll(series);
		this.start();

		//: updates the CPU usage every second
		$.time.setInterval("mainFX" ,0, 1000, ()->{
			
				int usedMem = (int) $.computer.getUsedMem();
				int totallMem = (int) $.computer.getVMTotalMem();
				int memProcentage = totallMem/usedMem;
			shiftSeriesYValue(series, memProcentage);
		});
		
	}
	
	
	
	
	public void start() {
		
		$.time.setInterval("progressBarMainFX" , 0, 100, ()->{
			if(  ((value < 0.01) && (inc == false)) || (value > 1 )) 
				inc =  !inc;	
			
			if(inc == true) {
				value += 0.01;

			}else {
				value -= 0.01;
			}

		progressbar1.setProgress(value);
			
		});

	}

	
    //Shift all YValue
    //add the new item
    public void shiftSeriesYValue(XYChart.Series series, int newValue){
        int numOfPoint = series.getData().size();
        for(int i=0; i<numOfPoint-1; i++){
            XYChart.Data<Number, Number> ShiftDataUp = 
                    (XYChart.Data<Number, Number>)series.getData().get(i+1);
            Number shiftValue = ShiftDataUp.getYValue();
            XYChart.Data<Number, Number> ShiftDataDn = 
                    (XYChart.Data<Number, Number>)series.getData().get(i);
            ShiftDataDn.setYValue(shiftValue);
        }
        XYChart.Data<Number, Number> lastData = 
            (XYChart.Data<Number, Number>)series.getData().get(numOfPoint-1);
        lastData.setYValue(newValue);
    }
	
    
    
}
