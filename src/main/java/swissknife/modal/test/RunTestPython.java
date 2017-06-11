package swissknife.modal.test;

import swissknife.Resources;
import swissknife.RunPythonFile;


public class RunTestPython extends RunPythonFile {

    String inputFile;
    String keyToPredict;
    int action;
    int actionTime;
    String pythonFile = Resources.LINEAR_REGRESSION_PYTHON_FILE;
    String algorithmName;

    public RunTestPython(String inputFile, String keyToPredict, int action, int actionTime, String algorithmName) {
        this.inputFile = inputFile;
        this.action = action;
        this.actionTime = actionTime;
        this.keyToPredict = keyToPredict;
        this.algorithmName = algorithmName;
    }

    public void run() {
        ProcessBuilder pb = new ProcessBuilder("python", this.pythonFile, inputFile, keyToPredict, "" + action, "" + actionTime,algorithmName);
        super.run(pb);
    }
}
