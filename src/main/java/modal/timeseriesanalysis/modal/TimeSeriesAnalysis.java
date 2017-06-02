package modal.timeseriesanalysis;

import modal.Tool;
import modal.Resources;

/**
 * Created by ramyeid on 6/2/17.
 */
public abstract class TimeSeriesAnalysis implements Tool {

    protected String inputFile;
    protected String keyX;
    protected String keyY;
    protected int actionTime;
    protected String average;
    protected String dateFormat;
    protected String pythonFile = Resources.Time_Series_Python_File;


    public TimeSeriesAnalysis(String inputFile,String keyX,String keyY,int actionTime,String average,String dateFormat){
        this.inputFile = inputFile;
        this.keyX = keyX;
        this.keyY = keyY;
        this.actionTime = actionTime;
        this.average = average;
        this.dateFormat = dateFormat;
    }

    public TimeSeriesAnalysis(){

    }

    public void build(String ... arg){
        this.inputFile = arg[0];
        this.keyX = arg[1];
        this.keyY = arg[2];
        this.actionTime = Integer.valueOf(arg[3]);
        this.average = arg[4];
        this.dateFormat = arg[5];
    }




}
