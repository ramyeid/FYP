package swissknife.modal.timeseriesanalysis.modal;

import swissknife.Resources;
import swissknife.modal.timeseriesanalysis.Plot.PlotForecastingVsActual;
import swissknife.modal.timeseriesanalysis.RunTSAPython;
import swissknife.modal.timeseriesanalysis.TimeSeriesAnalysis;

import javax.swing.*;

/**
 * Created by ramyeid on 6/2/17.
 */
public class TSAForecastVsActual extends TimeSeriesAnalysis {



    public TSAForecastVsActual(String inputFile, String keyX, String keyY, int actionTime, String average, String dateFormat){
        super(inputFile,keyX,keyY,actionTime,average,dateFormat);

    }

    public TSAForecastVsActual(){
        super();
    }

    public  void action(){
        new RunTSAPython(inputFile,keyX,keyY,2,actionTime,average,dateFormat,0,0).run();

    }

    public  JPanel plot(){
        return PlotForecastingVsActual.plotForecasting(Resources.TSA_FORECAST_VS_ACTUAL_OUTPUT_FILE, Resources.DATE_FORMAT_MAP.get(dateFormat));
    }
}
