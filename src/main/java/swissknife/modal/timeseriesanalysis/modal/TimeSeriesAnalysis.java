package swissknife.modal.timeseriesanalysis;

import swissknife.Resources;
import swissknife.modal.Tool;

import javax.swing.*;


public abstract class TimeSeriesAnalysis implements Tool {

    protected String inputFile;
    protected String keyX;
    protected String keyY;
    protected int actionTime;
    protected String average;
    protected String dateFormat;
    protected String pythonFile = Resources.TIME_SERIES_PYTHON_FILE;


    public TimeSeriesAnalysis(String inputFile, String keyX, String keyY, int actionTime, String average, String dateFormat) {
        this.inputFile = inputFile;
        this.keyX = keyX;
        this.keyY = keyY;
        this.actionTime = actionTime;
        this.average = average;
        this.dateFormat = dateFormat;
    }

    public TimeSeriesAnalysis(){

    }

    public abstract JPanel plot();

    public void build(String ... arg){
        this.inputFile = arg[0];
        this.keyX = arg[1];
        this.keyY = arg[2];
        this.actionTime = Integer.valueOf(arg[3]);
        this.average = arg[4];
        this.dateFormat = arg[5];
    }


    public String getInputFile() {
        return inputFile;
    }
}
