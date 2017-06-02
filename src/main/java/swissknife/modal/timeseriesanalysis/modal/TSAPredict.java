package swissknife.modal.timeseriesanalysis.modal;

import swissknife.Resources;
import swissknife.modal.timeseriesanalysis.Plot.PlotPredictionForecastOnce;
import swissknife.modal.timeseriesanalysis.RunTSAPython;
import swissknife.modal.timeseriesanalysis.TimeSeriesAnalysis;

import javax.swing.*;

/**
 * Created by ramyeid on 6/2/17.
 */
public class TSAPredict extends TimeSeriesAnalysis {



    public TSAPredict(String inputFile, String keyX, String keyY, int actionTime, String average, String dateFormat){
        super(inputFile,keyX,keyY,actionTime,average,dateFormat);
    }

    public TSAPredict(){
        super();

    }

    public void action(){
        new RunTSAPython(inputFile,keyX,keyY,1,actionTime,average,dateFormat,0,0).run();
    }

    public  JPanel plot(){
        return  PlotPredictionForecastOnce.plotPrediction(Resources.OUTPUT_FILE, Resources.DATE_FORMAT_MAP.get(dateFormat));
    }
}
