package swissknife.modal.classifier;

import swissknife.Resources;
import swissknife.RunPythonFile;

/**
 * Created by ramyeid on 6/10/17.
 */
public class RunClassifierPython extends RunPythonFile {

    String inputFile ;
    String keyToPredict;
    int action ;
    int actionTime ;
    String pythonFile = Resources.CLASSIFIER_PYTHON_FILE;
    String algorithmName;


    public RunClassifierPython(String inputFile,String keyToPredict,int action,int actionTime,String algoName){
        this.inputFile = inputFile;
        this.action = action;
        this.actionTime = actionTime;
        this.keyToPredict =keyToPredict;
        this.algorithmName = algoName;
    }

    public void run(){
        ProcessBuilder pb =  new ProcessBuilder("python",this.pythonFile,inputFile,keyToPredict,""+action,""+actionTime,algorithmName);
        super.run(pb);
    }
}
