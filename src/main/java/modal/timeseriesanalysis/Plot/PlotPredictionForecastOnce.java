package modal.timeseriesanalysis.Plot;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Hour;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ramyeid on 5/27/17.
 */

//Prediction.
//Forecast Once.
public class PlotPredictionForecastOnce extends JPanel {




    public static JPanel plotForecastOnce(String inputFile,String dateFormat,int actionTime){
        TimeSeriesCollection timeSeriesCollection = getTimeSeriesForecastOnce(inputFile,dateFormat,actionTime);
        PlotPredictionForecastOnce temp = new PlotPredictionForecastOnce();
        try{
            temp.add(new ChartPanel(createChart(timeSeriesCollection)));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return temp;
    }


    public static JPanel plotPrediction(String inputFile, String dateFormat){
        TimeSeriesCollection timeSeriesCollection = getTimeSeriesPrediction(inputFile,dateFormat);
        PlotPredictionForecastOnce temp = new PlotPredictionForecastOnce();

        try {
            temp.add(new ChartPanel(createChart(timeSeriesCollection)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return temp;
    }




    public static JFreeChart createChart(TimeSeriesCollection timeSeriesCollection) throws NumberFormatException, IOException {
        JFreeChart chart = ChartFactory.createXYLineChart("Y vs Time", "Time", "Y", timeSeriesCollection, PlotOrientation.VERTICAL, true, true, false);

        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        DateAxis dateAxis = new DateAxis();
        dateAxis.setDateFormatOverride(new SimpleDateFormat("HH-dd-MM-yyyy"));
        plot.setDomainAxis(dateAxis);

        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(0, false);

        renderer.setSeriesLinesVisible(1,false);
        renderer.setSeriesLinesVisible(1,true);

        plot.setRenderer(renderer);

        return chart;
    }




    public static TimeSeriesCollection getTimeSeriesPrediction(String inputFile, String dateFormat) {

        TimeSeriesCollection result = new TimeSeriesCollection();

        TimeSeries seriesPredicted = addSingleValueFromcsv(inputFile,dateFormat,"Actual");
        result.addSeries(seriesPredicted);
        return result;
    }


    public static TimeSeriesCollection getTimeSeriesForecastOnce(String inputFile,String dateFormat,int actionTime){
        TimeSeries predicted = new TimeSeries("Predicted",Hour.class);
        TimeSeries actual = new TimeSeries("actual",Hour.class);
        TimeSeriesCollection result = new TimeSeriesCollection();

        TimeSeries tmp = addSingleValueFromcsv(inputFile,dateFormat,"tmp");

        for(int i=0;i<tmp.getItemCount();++i){
            if (i>=tmp.getItemCount()-actionTime){
                predicted.add(tmp.getDataItem(i));
            }
            else{
                actual.add(tmp.getDataItem(i));
            }
        }
        result.addSeries(actual);
        result.addSeries(predicted);
        return result;
    }



    public static TimeSeries addSingleValueFromcsv(String inputFile,String dateFormat,String timeSeriesName){
        TimeSeries tmp = new TimeSeries(timeSeriesName,Hour.class);
        try {
            BufferedReader in = new BufferedReader(new FileReader(inputFile));
            SimpleDateFormat f = new SimpleDateFormat(dateFormat);
            String s = null;
            while ((s = in.readLine()) != null) {
                if (s.matches(".*[a-zA-Z]+.*"))
                    continue;

                String[] a = s.split(",");
                Date d = f.parse(a[0]);
                float value = Float.valueOf(a[1]);

                tmp.add(new Hour(d), value);
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tmp;
    }


}
