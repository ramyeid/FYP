package swissknife.modal.classifier.linearregression;

import swissknife.modal.test.RunTestPython;

/**
 * Created by ramyeid on 6/15/17.
 */
public class LinRPredictVsActual extends LinearRegression{
    public LinRPredictVsActual(){
        super();
        this.action = 1;
    }

    public LinRPredictVsActual(String inputFile, String keyToPredict, int actionTime) {
        super(inputFile, keyToPredict, actionTime);
        this.action = 1;
    }

    @Override
    public void action() {
        new RunTestPython(inputFile,keyToPredict,action,actionTime,"LR").run();
    }
}
