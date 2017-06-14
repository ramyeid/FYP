package swissknife.modal.timeseriesanalysis.modal;

import swissknife.CSVReader;
import swissknife.Resources;
import swissknife.modal.timeseriesanalysis.Plot.PlotPredictionForecastOnce;
import swissknife.modal.timeseriesanalysis.RunTSAPython;
import swissknife.modal.timeseriesanalysis.TimeSeriesAnalysis;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by ramyeid on 6/2/17.
 */
public class TSAForecastOnce extends TimeSeriesAnalysis {

    private  ArrayList<ArrayList<String>> values;
    public TSAForecastOnce(String inputFile, String keyX, String keyY, int actionTime, String average, String dateFormat){
        super(inputFile,keyX,keyY,actionTime,average,dateFormat);
    }

    public TSAForecastOnce(){
        super();
    }

    private ArrayList<ArrayList<String>> getValuesFromcsv() {
        ArrayList<ArrayList<String>> data = CSVReader.getDataCSVForKeys(Resources.TSA_FORECAST_ONCE_OUTPUT_FILE,CSVReader.getColumnKeys(Resources.TSA_FORECAST_ONCE_OUTPUT_FILE));
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for (int i = 0; i < data.size(); ++i) {
            result.add(new ArrayList<>());
            result.get(i).add(data.get(i).get(0));
            for (int j = data.get(i).size() - actionTime; j < data.get(i).size(); ++j) {
                result.get(i).add(data.get(i).get(j));
            }
        }
        return result;
    }

    public ArrayList<ArrayList<String>> getValues(){
        return values;
    }

    public  void action() {
        new RunTSAPython(inputFile,keyX,keyY,3,actionTime,average,dateFormat,0,0).run();
        values = getValuesFromcsv();
    }

    public JPanel plot() {
        return PlotPredictionForecastOnce.plotForecastOnce(Resources.TSA_FORECAST_ONCE_OUTPUT_FILE, Resources.DATE_FORMAT_MAP.get(dateFormat),actionTime,keyY);
    }


}
