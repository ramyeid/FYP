package swissknife.modal.timeseriesanalysis.modal;

import org.jfree.data.time.*;
import swissknife.Resources;
import swissknife.modal.timeseriesanalysis.Plot.PlotContinuousForecasting;
import swissknife.modal.timeseriesanalysis.RunTSAPython;
import swissknife.modal.timeseriesanalysis.TimeSeriesAnalysis;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Created by ramyeid on 6/2/17.
 */
public class TSAContinuousForecast extends TimeSeriesAnalysis {

    int resetcsv;

    ArrayList<ArrayList<String>> values;

    public TSAContinuousForecast(String inputFile, String keyX, String keyY, int actionTime, String average, String dateFormat) {
        super(inputFile, keyX, keyY, actionTime, average, dateFormat);
    }


    public void setResetCsv(int resetcsv) {
        this.resetcsv = resetcsv;
    }

    public void action() {
        new RunTSAPython(inputFile, keyX, keyY, 4, actionTime, average, dateFormat, resetcsv, 0).run();
    }

    public ArrayList<ArrayList<String>> getValues() {
        return values;
    }


    private ArrayList<TimePeriod> getDates(TimeSeries actual, TimeSeriesCollection collection) {

        LinkedHashSet<TimePeriod> dates = new LinkedHashSet<>();
        for (int i = 0; i < actual.getTimePeriods().size(); ++i) {
            TimePeriod a = actual.getTimePeriod(i);
            dates.add(a);
        }
        for (int i = 0; i < collection.getSeriesCount(); ++i) {
            TimeSeries tmp = collection.getSeries(i);
            for (int j = 0; j < tmp.getTimePeriods().size(); ++j) {
                TimePeriod a = tmp.getTimePeriod(j);
                dates.add(a);
            }
        }
        ArrayList<TimePeriod> datesList = new ArrayList<>(dates);
        return datesList;
    }


    private ArrayList<ArrayList<String>> gatherValues() {
        TimeSeries actual = Resources.actualTimeSeries;
        TimeSeriesCollection collection = Resources.predictedTimeSeriesCollection;
        ArrayList<TimePeriod> dates = getDates(actual, collection);

        ArrayList <String> actualList = new ArrayList<>();
        ArrayList<ArrayList<String>> predicted = new ArrayList<ArrayList<String>>();
        for(int i=0;i<collection.getSeriesCount();++i){
            predicted.add(new ArrayList<String>());
        }
        for(int i=0;i<dates.size();++i){
            for(int j=0;j<collection.getSeriesCount();++j){
                TimeSeries tmp = collection.getSeries(j);
                TimeSeriesDataItem dataItem = tmp.getDataItem((RegularTimePeriod) dates.get(i));
                if(dataItem==null){
                    predicted.get(j).add("X");
                }
                else{
                    predicted.get(j).add(""+dataItem.getValue().floatValue());
                }
            }
            TimeSeriesDataItem dataItem = actual.getDataItem((RegularTimePeriod) dates.get(i));
            if(dataItem == null){
                actualList.add("X");
            }
            else {
                actualList.add(""+dataItem.getValue().floatValue());
            }
        }

        ArrayList<ArrayList<String>> result = new ArrayList<>();
        actualList.add(0,"Actual");
        result.add(actualList);
        for(int i=0;i<predicted.size();++i) {
            predicted.get(i).add(0,"forecast #"+i);
            result.add(predicted.get(i));
        }

        return result;
    }

    public void addValue(float value) {
        new RunTSAPython(inputFile, keyX, keyY, 5, 0, average, dateFormat, 0, value).run();
    }

    public JPanel plot() {
        JPanel tmp = PlotContinuousForecasting.plotContinuousForecasting(Resources.CONTINUOS_FORECAST_OUTPUT_FILE + "Forecasts.txt", Resources.CONTINUOS_FORECAST_OUTPUT_FILE + "Continuous_output.csv", Resources.DATE_FORMAT_MAP.get(dateFormat), keyY);
        values = gatherValues();
        return tmp;
    }

    public TSAContinuousForecast() {
        super();
    }

    public void setActionTime(int actionTime) {
        this.actionTime = actionTime;
    }


}
