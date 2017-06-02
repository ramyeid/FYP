package swissknife.modal.timeseriesanalysis;

import swissknife.Resources;
import swissknife.RunPythonFile;

/**
 * Created by ramyeid on 6/2/17.
 */
public class RunTSAPython  extends RunPythonFile{

    String dateFormat;
    String inputFile ;
    String keyX;
    String keyY;
    int action ;
    int actionTime ;
    String average;
    final String pythonFile = Resources.TIME_SERIES_PYTHON_FILE;
    int resetCSV; //=1 -> resetCSV // new time series analysus
    //=2 -> don't reset// old time series for comparison.
    float datatoAdd;


    public RunTSAPython(String inputFile,String keyX,String keyY,int action,int actionTime, String average,String dateFormat,int resetCSV, float datatoAdd){
        this.inputFile = inputFile;
        this.keyX = keyX;
        this.keyY = keyY;
        this.action = action;
        this.actionTime = actionTime;
        this.average = average;
        this.dateFormat = dateFormat;
        this.resetCSV  = resetCSV;
        this.datatoAdd = datatoAdd;
    }

    public void run(){
        ProcessBuilder pb =  new ProcessBuilder("python",this.pythonFile,inputFile,keyX,keyY,""+action,""+actionTime,average,dateFormat,""+resetCSV,""+datatoAdd);
        super.run(pb);
    }

}


