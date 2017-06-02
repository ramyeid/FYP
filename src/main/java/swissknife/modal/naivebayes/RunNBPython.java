package swissknife.modal.naivebayes;

import swissknife.Resources;
import swissknife.RunPythonFile;

/**
 * Created by ramyeid on 6/2/17.
 */
public class RunNBPython extends RunPythonFile{

    String inputFile ;
    String keyToPredict;
    int action ;
    int actionTime ;
    String pythonFile = Resources.NAIVE_BAYES_PYTHON_FILE;



    public RunNBPython(String inputFile,String keyToPredict,int action,int actionTime){
        this.inputFile = inputFile;
        this.action = action;
        this.actionTime = actionTime;
        this.keyToPredict =keyToPredict;
    }

    public void run(){
        ProcessBuilder pb =  new ProcessBuilder("python",this.pythonFile,inputFile,keyToPredict,""+action,""+actionTime);
        super.run(pb);
    }
}
