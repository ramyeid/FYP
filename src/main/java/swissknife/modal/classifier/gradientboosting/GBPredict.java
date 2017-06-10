package swissknife.modal.classifier.gradientboosting;

import swissknife.modal.classifier.RunClassifierPython;

/**
 * Created by ramyeid on 6/10/17.
 */
public class GBPredict extends GradientBoosting {

    public GBPredict(){
        super();
        this.action = 2;
    }
    public GBPredict(String inputFile, String keyToPredict, int actionTime) {
        super(inputFile, keyToPredict, actionTime);
        this.action = 2;
    }

    @Override
    public void action() {

        new RunClassifierPython(inputFile, keyToPredict, action, actionTime,"GB").run();
    }
}


