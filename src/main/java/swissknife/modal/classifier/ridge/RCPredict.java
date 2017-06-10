package swissknife.modal.classifier.ridge;

import swissknife.modal.classifier.RunClassifierPython;

/**
 * Created by ramyeid on 6/10/17.
 */
public class RCPredict extends RidgeClassifier {

    public RCPredict() {
        super();
        this.action = 2;
    }

    public RCPredict(String inputFile, String keyToPredict, int actionTime) {
        super(inputFile, keyToPredict, actionTime);
        this.action = 2;
    }

    @Override
    public void action() {
        new RunClassifierPython(inputFile, keyToPredict, action, actionTime, "RC",actionKeys).run();
    }

}

