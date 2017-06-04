package swissknife.modal.linearregression.modal;

import swissknife.CSVReader;
import swissknife.Resources;
import swissknife.modal.linearregression.RunLRPython;
import swissknife.modal.naivebayes.RunNBPython;
import swissknife.modal.naivebayes.modal.NaiveBayes;

/**
 * Created by ramyeid on 6/4/17.
 */
public class LRPredictVsActual extends LinearRegression {


    private float error;

    public LRPredictVsActual(){

    }

    public LRPredictVsActual(String inputFile, String keyToPredict, int actionTime) {
        super(inputFile, keyToPredict, actionTime);
    }

    @Override
    public void action() {
        new RunLRPython(inputFile,keyToPredict,1,actionTime).run();
        error = CSVReader.readError(Resources.LR_PREDICTED_ACTUAL_ONLY_FILE,"ERROR MSE: ");
    }

    public float getError() {
        return error;
    }
}

