package modal.tool;

import modal.timeseriesanalysis.Plot.PlotPredictionForecastOnce;
import modal.timeseriesanalysis.Resources;
import modal.timeseriesanalysis.RunPythonFile;

import javax.swing.*;

/**
 * Created by ramyeid on 6/2/17.
 */
public class ForecastOnce extends TimeSeriesAnalysis{


    public ForecastOnce(String inputFile,String keyX,String keyY,int actionTime,String average,String dateFormat){
        super(inputFile,keyX,keyY,actionTime,average,dateFormat);
    }

    public ForecastOnce(){
        super();
    }


    public  void action() {
        new RunPythonFile(inputFile,keyX,keyY,3,actionTime,average,dateFormat,0,0).run();

    }

    public JPanel plot() {
        return PlotPredictionForecastOnce.plotForecastOnce(Resources.OUTPUT_FILE, Resources.DATE_FORMAT_MAP.get(dateFormat),actionTime);
    }
}
