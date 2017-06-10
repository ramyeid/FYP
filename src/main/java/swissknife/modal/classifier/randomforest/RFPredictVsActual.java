package swissknife.modal.classifier.randomforest;

import swissknife.modal.classifier.RunClassifierPython;


/**
 * Created by ramyeid on 6/10/17.
 */
public class RFPredictVsActual extends RandomForest {

    public RFPredictVsActual() {
        super();
        this.action = 1;
    }

    public RFPredictVsActual(String inputFile, String keyToPredict, int actionTime) {
        super(inputFile, keyToPredict, actionTime);
        this.action = 1;
    }


    @Override
    public void action() {
        new RunClassifierPython(inputFile, keyToPredict, action, actionTime, "RF").run();


    }
}
