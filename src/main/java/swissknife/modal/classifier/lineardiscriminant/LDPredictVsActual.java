package swissknife.modal.classifier.lineardiscriminant;

import swissknife.modal.classifier.RunClassifierPython;

/**
 * Created by ramyeid on 6/10/17.
 */
public class LDPredictVsActual extends LinearDiscriminant {

    public LDPredictVsActual() {
        super();
        super.action = 1;
    }

    public LDPredictVsActual(String inputFile, String keyToPredict, int actionTime) {
        super(inputFile, keyToPredict, actionTime);
        super.action = 1;
    }


    @Override
    public void action() {
        new RunClassifierPython(inputFile, keyToPredict, action, actionTime, "LDA").run();
    }

}
