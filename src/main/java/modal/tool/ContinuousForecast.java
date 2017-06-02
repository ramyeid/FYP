package modal.tool;

import modal.timeseriesanalysis.Resources;
import modal.timeseriesanalysis.Plot.PlotContinuousForecasting;
import modal.timeseriesanalysis.RunPythonFile;

import javax.swing.*;

/**
 * Created by ramyeid on 6/2/17.
 */
public class ContinuousForecast extends TimeSeriesAnalysis{

    String inputFile;
    String keyX;
    String keyY;
    int actionTime;
    String average;
    String dateFormat;
    int resetcsv;

    public ContinuousForecast(String inputFile,String keyX,String keyY,int actionTime,String average,String dateFormat){
        super(inputFile,keyX,keyY,actionTime,average,dateFormat);
    }


    public void setResetCsv(int resetcsv){
        this.resetcsv = resetcsv;
    }

    public void action(){
        new RunPythonFile(inputFile,keyX,keyY,4,actionTime,average,dateFormat,resetcsv,0).run();
    }

    public  void addValue(float value) {
        new RunPythonFile(inputFile,keyX,keyY,5,0,average,dateFormat,0,value).run();
    }

    public JPanel plot() {
        return PlotContinuousForecasting.plotContinuousForecasting(Resources.RESOURCES+"Forecasts.txt", Resources.RESOURCES+"Continuous_output.csv", Resources.DATE_FORMAT_MAP.get(dateFormat));
    }

    public ContinuousForecast(){
        super();
    }
}
