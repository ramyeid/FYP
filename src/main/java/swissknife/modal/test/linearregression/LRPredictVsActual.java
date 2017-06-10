package swissknife.modal.test.linearregression;

import swissknife.modal.test.RunTestPython;

/**
 * Created by ramyeid on 6/4/17.
 */
public class LRPredictVsActual extends LinearRegression {



    public LRPredictVsActual(){
        super();
        this.action = 1;
    }

    public LRPredictVsActual(String inputFile, String keyToPredict, int actionTime) {
        super(inputFile, keyToPredict, actionTime);
        this.action = 1;
    }

    @Override
    public void action() {
        new RunTestPython(inputFile,keyToPredict,action,actionTime,"LR").run();
    }

}

