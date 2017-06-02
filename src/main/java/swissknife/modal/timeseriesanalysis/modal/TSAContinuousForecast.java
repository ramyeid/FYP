package swissknife.modal.timeseriesanalysis.modal;

import swissknife.Resources;
import swissknife.modal.timeseriesanalysis.Plot.PlotContinuousForecasting;
import swissknife.modal.timeseriesanalysis.RunTSAPython;
import swissknife.modal.timeseriesanalysis.TimeSeriesAnalysis;

import javax.swing.*;

/**
 * Created by ramyeid on 6/2/17.
 */
public class TSAContinuousForecast extends TimeSeriesAnalysis {

    int resetcsv;

    public TSAContinuousForecast(String inputFile, String keyX, String keyY, int actionTime, String average, String dateFormat){
        super(inputFile,keyX,keyY,actionTime,average,dateFormat);
    }


    public void setResetCsv(int resetcsv){
        this.resetcsv = resetcsv;
    }

    public void action(){
        new RunTSAPython(inputFile,keyX,keyY,4,actionTime,average,dateFormat,resetcsv,0).run();
    }

    public  void addValue(float value) {
        new RunTSAPython(inputFile,keyX,keyY,5,0,average,dateFormat,0,value).run();
    }

    public JPanel plot() {
        return PlotContinuousForecasting.plotContinuousForecasting(Resources.CONTINUOS_FORECAST_OUTPUT_FILE +"Forecasts.txt", Resources.CONTINUOS_FORECAST_OUTPUT_FILE +"Continuous_output.csv", Resources.DATE_FORMAT_MAP.get(dateFormat));
    }

    public TSAContinuousForecast(){
        super();
    }

    public void setActionTime(int actionTime) {
        this.actionTime = actionTime;
    }





}
