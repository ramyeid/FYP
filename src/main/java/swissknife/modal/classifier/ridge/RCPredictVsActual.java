package swissknife.modal.classifier.ridge;

import swissknife.modal.classifier.RunClassifierPython;

/**
 * Created by ramyeid on 6/10/17.
 */
public class RCPredictVsActual extends RidgeClassifier {

    public RCPredictVsActual() {
        super();
        this.action = 1;
    }

    public RCPredictVsActual(String inputFile, String keyToPredict, int actionTime) {
        super(inputFile, keyToPredict, actionTime);
        this.action = 1;
    }


    @Override
    public void action() {
        new RunClassifierPython(inputFile, keyToPredict, action, actionTime, "RC",actionKeys).run();


    }
}

