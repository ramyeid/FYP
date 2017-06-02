package swissknife.modal.timeseriesanalysis.modal;

import swissknife.Resources;
import swissknife.modal.timeseriesanalysis.Plot.PlotPredictionForecastOnce;
import swissknife.modal.timeseriesanalysis.RunTSAPython;
import swissknife.modal.timeseriesanalysis.TimeSeriesAnalysis;

import javax.swing.*;

/**
 * Created by ramyeid on 6/2/17.
 */
public class TSAForecastOnce extends TimeSeriesAnalysis {


    public TSAForecastOnce(String inputFile, String keyX, String keyY, int actionTime, String average, String dateFormat){
        super(inputFile,keyX,keyY,actionTime,average,dateFormat);
    }

    public TSAForecastOnce(){
        super();
    }


    public  void action() {
        new RunTSAPython(inputFile,keyX,keyY,3,actionTime,average,dateFormat,0,0).run();

    }

    public JPanel plot() {
        return PlotPredictionForecastOnce.plotForecastOnce(Resources.TSA_FORECAST_ONCE_OUTPUT_FILE, Resources.DATE_FORMAT_MAP.get(dateFormat),actionTime);
    }
}
