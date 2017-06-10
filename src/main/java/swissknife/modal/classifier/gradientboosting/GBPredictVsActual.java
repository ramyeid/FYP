package swissknife.modal.classifier.gradientboosting;

import swissknife.modal.classifier.RunClassifierPython;

/**
 * Created by ramyeid on 6/10/17.
 */
public class GBPredictVsActual extends GradientBoosting {

    public GBPredictVsActual(){
        super();
        this.action = 1;
    }
    public GBPredictVsActual(String inputFile, String keyToPredict, int actionTime) {
        super(inputFile, keyToPredict, actionTime);
        this.action = 1;
    }

    @Override
    public void action() {
        new RunClassifierPython(inputFile, keyToPredict, action, actionTime,"GB").run();
    }
}


