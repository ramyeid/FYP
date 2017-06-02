package modal.tool;

import modal.timeseriesanalysis.Plot.PlotForecastingVsActual;
import modal.timeseriesanalysis.Resources;
import modal.timeseriesanalysis.RunPythonFile;

import javax.swing.*;

/**
 * Created by ramyeid on 6/2/17.
 */
public class ForecastVsActual extends TimeSeriesAnalysis{



    public ForecastVsActual(String inputFile,String keyX,String keyY,int actionTime,String average,String dateFormat){
        super(inputFile,keyX,keyY,actionTime,average,dateFormat);

    }

    public ForecastVsActual(){
        super();
    }

    public  void action(){
        new RunPythonFile(inputFile,keyX,keyY,2,actionTime,average,dateFormat,0,0).run();

    }

    public  JPanel plot(){
        return PlotForecastingVsActual.plotForecasting(Resources.OUTPUT_FILE, Resources.DATE_FORMAT_MAP.get(dateFormat));
    }
}
