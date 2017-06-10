package swissknife.modal.timeseriesanalysis.Plot;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.*;
import swissknife.Resources;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ramyeid on 5/26/17.
 */


public class PlotContinuousForecasting extends JPanel {

    public static String keyY = "";
    public static int forecastNumber = 0;

    public static JPanel plotContinuousForecasting(String forecasts, String csvFile, String dateFormat, String Ykey) {
        keyY = Ykey;
        forecastNumber = 0;
        TimeSeriesCollection timeSeriesCollection = createDatasetContinousForecast(forecasts, csvFile, dateFormat);
        PlotContinuousForecasting temp = new PlotContinuousForecasting();
        try {
            temp.add(new ChartPanel(createChart(timeSeriesCollection)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return temp;
    }


    public static JFreeChart createChart(TimeSeriesCollection timeSeriesCollection) throws NumberFormatException, IOException {
        JFreeChart chart = ChartFactory.createXYLineChart(keyY+" vs. Time", "Time", keyY, timeSeriesCollection, PlotOrientation.VERTICAL, true, true, false);

        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();


        DateAxis dateAxis = new DateAxis();
        dateAxis.setDateFormatOverride(new SimpleDateFormat("HH-dd-MM-yyyy"));
        plot.setDomainAxis(dateAxis);

        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(0, false);


        for (int i = 1; i < timeSeriesCollection.getSeries().size(); ++i) {
            renderer.setSeriesLinesVisible(i, false);
            renderer.setSeriesShapesVisible(i, true);
        }
        plot.setRenderer(renderer);

        return chart;
    }


    //these two functions gather all the data between ----- to XYSeries (Forecast.txt).
    public static TimeSeriesCollection getTimeSeriesFromForecasts(String inputFile, String dateFormat) {
        TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection();
        try {
            BufferedReader in = new BufferedReader(new FileReader(inputFile));
            String line = null;
            List<String> data = new ArrayList<>();
            while ((line = in.readLine()) != null) {
                if (line.contains("--")) {
                    if (data.size() != 0) {
                        timeSeriesCollection.addSeries(getSingleTimeSeriesFromForecast(data, dateFormat));

                    }
                    data = new ArrayList<>();
                    continue;
                } else {
                    data.add(line);
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return timeSeriesCollection;
    }

    public static TimeSeries getSingleTimeSeriesFromForecast(List<String> tmp, String dateFormat) {
        final TimeSeries series = new TimeSeries("#"+forecastNumber, Hour.class);
        forecastNumber++;
        SimpleDateFormat f = new SimpleDateFormat(dateFormat);

        for (int i = 0; i < tmp.size(); ++i) {
            String[] a = tmp.get(i).split(",");
            Date d = null;
            try {
                d = f.parse(a[0]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            float v = Float.valueOf(a[1]);
            series.add(new Hour(d), v);
        }
        return series;

    }


    public static TimeSeriesCollection createDatasetContinousForecast(String forecasts, String csvFile, String dateFormat) {
        TimeSeries tmp = PlotPredictionForecastOnce.addSingleValueFromcsv(csvFile, dateFormat, keyY);
        TimeSeriesCollection tmp_2 = getTimeSeriesFromForecasts(forecasts, dateFormat);
        TimeSeriesCollection result = new TimeSeriesCollection();

        Resources.actualTimeSeries = tmp;
        Resources.predictedTimeSeriesCollection = tmp_2;


        result.addSeries(tmp);
        for (int i = 0; i < tmp_2.getSeries().size(); ++i) {
            result.addSeries(tmp_2.getSeries(i));
        }

        return result;
    }


}