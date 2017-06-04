package swissknife.modal.linearregression;

import swissknife.Resources;
import swissknife.RunPythonFile;


public class RunLRPython extends RunPythonFile {

    String inputFile;
    String keyToPredict;
    int action;
    int actionTime;
    String pythonFile = Resources.LINEAR_REGRESSION_PYTHON_FILE;


    public RunLRPython(String inputFile, String keyToPredict, int action, int actionTime) {
        this.inputFile = inputFile;
        this.action = action;
        this.actionTime = actionTime;
        this.keyToPredict = keyToPredict;
    }

    public void run() {
        ProcessBuilder pb = new ProcessBuilder("python", this.pythonFile, inputFile, keyToPredict, "" + action, "" + actionTime);
        super.run(pb);
    }
}
