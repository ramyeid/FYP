package modal.timeseriesanalysis;

import java.io.IOException;

/**
 * Created by ramyeid on 4/24/17.
 */
public class RunPythonFile {

    String dateFormat;
    String inputFile ;
    String keyX;
    String keyY;
    int action ;
    int actionTime ;
    String average;
    final String pythonFile ;
    int resetCSV; //=1 -> resetCSV // new time series analysus
                  //=2 -> don't reset// old time series for comparison.
    float datatoAdd;


    public RunPythonFile(String inputFile,String keyX,String keyY,int action,int actionTime, String average,String dateFormat,int resetCSV, float datatoAdd){
        pythonFile = System.getProperty("user.dir")+"/src/main/java/modal/timeseriesanalysis/script.py";
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
        ProcessBuilder pb = new ProcessBuilder("python",this.pythonFile,inputFile,keyX,keyY,""+action,""+actionTime,average,dateFormat,""+resetCSV,""+datatoAdd);
        pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        pb.redirectError(ProcessBuilder.Redirect.INHERIT);

        Process p;
        try {
            p = pb.start();
            p.waitFor();

        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
