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
public class PlotForecastingVsActual extends JPanel {


    public static float errorMSE;
    public static JPanel plotForecasting(String inputFile, String dateFormat) {
        TimeSeriesCollection timeSeriesCollection = getTimeSeriesFromCSV(inputFile, dateFormat);
        PlotForecastingVsActual temp = new PlotForecastingVsActual();

        try {
            temp.add(new ChartPanel(createChart(timeSeriesCollection)));
        } catch (IOException e) {
            e.printStackTrace();
        }

//        RefineryUtilities.centerFrameOnScreen(demo);
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
        renderer.setSeriesShapesVisible(0, true);

        renderer.setSeriesLinesVisible(1,true);
        renderer.setSeriesShapesVisible(1,true);


        plot.setRenderer(renderer);

        return chart;
    }


    public static TimeSeriesCollection getTimeSeriesFromCSV(String inputFile, String dateFormat) {

        final TimeSeries seriesPredicted = new TimeSeries("Predicted", Hour.class);
        final TimeSeries seriesActual = new TimeSeries("Actual", Hour.class);

        TimeSeriesCollection result = new TimeSeriesCollection();
        try {
            BufferedReader in = new BufferedReader(new FileReader(inputFile));
            SimpleDateFormat f = new SimpleDateFormat(dateFormat);
            String s = null;
            while ((s = in.readLine()) != null) {
                if (s.matches(".*[a-zA-Z]+.*")) {
                    if (s.contains("ERROR MSE: ")) {
                        errorMSE = Float.valueOf(s.substring(11, s.length()));
                        System.out.println("ERROR  " + errorMSE);
                    }
                    continue;
                }

                String[] a = s.split(",");
                Date d = f.parse(a[0]);
                float actual = Float.valueOf(a[1]);
                float predicted = Float.valueOf(a[2]);


                seriesPredicted.add(new Hour(d), predicted);
                seriesActual.add(new Hour(d),actual);
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        result.addSeries(seriesPredicted);
        result.addSeries(seriesActual);
        return result;
    }
}