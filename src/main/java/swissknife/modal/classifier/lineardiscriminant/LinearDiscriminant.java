package swissknife.modal.classifier.lineardiscriminant;

import swissknife.Resources;
import swissknife.modal.classifier.Classifier;

/**
 * Created by ramyeid on 6/10/17.
 */
public abstract class LinearDiscriminant extends Classifier {

    public LinearDiscriminant(String inputFile, String keyToPredict, int actionTime) {
        super(inputFile, keyToPredict, actionTime);
        super.fileToReadAccuracy = Resources.LD_PREDICTED_ACTUAL_ONLY_FILE;
        super.algorithmName = Resources.LINEAR_DISCRIMINANT_ANALYSIS;

    }

    public LinearDiscriminant() {
        super.fileToReadAccuracy = Resources.LD_PREDICTED_ACTUAL_ONLY_FILE;
        super.algorithmName = Resources.LINEAR_DISCRIMINANT_ANALYSIS;
    }

}

