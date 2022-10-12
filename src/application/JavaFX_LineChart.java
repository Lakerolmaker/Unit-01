package application;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @web http://java-buddy.blogspot.com/
 */
public class JavaFX_LineChart extends Application {
    
    Label label1, label2, label3;
    XYChart.Series series1, series2, series3;
    final int MAX_NUM = 20;
    @Override
    public void start(Stage primaryStage) {

        TextField textField = new TextField();
        Button btn = new Button();
        btn.setText("Add item");
        btn.setOnAction((ActionEvent event) -> {
            try{
                int i = Integer.parseInt(textField.getText());
                System.out.println(String.valueOf(i));
                
                removeAndAdd(series1, i);
                reduceSeriesXValue(series2, i);
                shiftSeriesYValue(series3, i);
                
                printSeries(series1, label1);
                printSeries(series2, label2);
                printSeries(series3, label3);
                
            }catch (NumberFormatException ex){
                System.out.println(ex.toString());
            }
        });
        
        label1 = new Label();
        label2 = new Label();
        label3 = new Label();
        
        //prepare LineChart 1
        final NumberAxis xAxis1 = new NumberAxis();
        final NumberAxis yAxis1 = new NumberAxis();
        final LineChart<Number,Number> lineChart1 = 
                new LineChart<>(xAxis1,yAxis1);
        series1 = new XYChart.Series();
        series1.setName("series1");
        lineChart1.getData().add(series1);
        
        //prepare LineChart 2
        final NumberAxis xAxis2 = new NumberAxis();
        final NumberAxis yAxis2 = new NumberAxis();
        final LineChart<Number,Number> lineChart2 = 
                new LineChart<>(xAxis2,yAxis2);
        series2 = new XYChart.Series();
        series2.setName("series2");
        lineChart2.getData().add(series2);
        
        //prepare LineChart 3
        final NumberAxis xAxis3 = new NumberAxis();
        final NumberAxis yAxis3 = new NumberAxis();
        final LineChart<Number,Number> lineChart3 = 
                new LineChart<>(xAxis3,yAxis3);
        series3 = new XYChart.Series();
        series3.setName("series3");
        lineChart3.getData().add(series3);
        
        //init dummy data
        for(int i=0; i<MAX_NUM; i++){
            series1.getData().add(new XYChart.Data(i, i));
            series2.getData().add(new XYChart.Data(i, i));
            series3.getData().add(new XYChart.Data(i, i));
        }
        
        printSeries(series1, label1);
        printSeries(series2, label2);
        printSeries(series3, label3);
        
        VBox vBox1 = new VBox();
        vBox1.getChildren().addAll(lineChart1, label1);
        VBox vBox2 = new VBox();
        vBox2.getChildren().addAll(lineChart2, label2);
        VBox vBox3 = new VBox();
        vBox3.getChildren().addAll(lineChart3, label3);
        
        HBox chartBox = new HBox();
        chartBox.getChildren().addAll(vBox1, vBox2, vBox3);
        
        CheckBox cbAnimated = new CheckBox("animated");
        cbAnimated.setSelected(true);
        cbAnimated.selectedProperty().addListener(
                (ObservableValue<? extends Boolean> observable, 
                        Boolean oldValue, Boolean newValue) -> {
            lineChart1.setAnimated(newValue);
            lineChart2.setAnimated(newValue);
            lineChart3.setAnimated(newValue);
        });
        
        VBox vBox = new VBox();
        vBox.getChildren().addAll(cbAnimated, textField, btn, chartBox);
        
        StackPane root = new StackPane();
        root.getChildren().add(vBox);
        
        Scene scene = new Scene(root, 1000, 650);
        
        primaryStage.setTitle("java-buddy.blogspot.com");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //Remove the first item, and add the new item - WRONG result!
    private void removeAndAdd(XYChart.Series series, int newValue){
        series.getData().remove(0);
        XYChart.Data newData = 
                new XYChart.Data(series.getData().size(), newValue);
        series.getData().add(newData);
    }
    
    //remove the first item
    //reduce XValue of all items by 1
    //add the new item
    public void reduceSeriesXValue(XYChart.Series series, int newValue){
        series.getData().remove(0);
        int numOfPoint = series.getData().size();
        for(int i=0; i<numOfPoint; i++){
            //reduce XValue
            XYChart.Data<Number, Number> data = 
                    (XYChart.Data<Number, Number>)series.getData().get(i);
            int x = (int)data.getXValue();
            data.setXValue(x-1);
        }
        
        series.getData().add(new XYChart.Data(numOfPoint, newValue));

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
    
    //display the data of series
    private void printSeries(XYChart.Series series, Label label){
        String printOut = "";
        int numOfPoint = series.getData().size();
        for(int i=0; i<numOfPoint; i++){
            XYChart.Data<Number, Number> data = 
                    (XYChart.Data<Number, Number>)series.getData().get(i);
            printOut += i + " - " + data.getXValue() 
                    + " : " + data.getYValue() + "\n";
        }
        
        label.setText(printOut);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}