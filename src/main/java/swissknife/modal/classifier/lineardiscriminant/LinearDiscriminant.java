package swissknife.modal.classifier.lineardiscriminant;

import swissknife.modal.classifier.Classifier;

/**
 * Created by ramyeid on 6/10/17.
 */
public abstract class LinearDiscriminant extends Classifier {

    public LinearDiscriminant(String inputFile, String keyToPredict, int actionTime) {
        super(inputFile, keyToPredict, actionTime);
    }

    public LinearDiscriminant() {
    }

}
