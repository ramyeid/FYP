package swissknife.modal.classifier.gradientboosting;

import swissknife.Resources;
import swissknife.modal.classifier.Classifier;

/**
 * Created by ramyeid on 6/10/17.
 */
public abstract class GradientBoosting extends Classifier {

    public GradientBoosting(String inputFile, String keyToPredict, int actionTime) {
        super(inputFile, keyToPredict, actionTime);
        super.fileToReadAccuracy = Resources.GB_PREDICTED_ACTUAL_ONLY_FILE;
        super.algorithmName = Resources.GRADIENT_BOOSTING_CLASSIFIER;
    }

    public GradientBoosting() {
        super.fileToReadAccuracy = Resources.GB_PREDICTED_ACTUAL_ONLY_FILE;
        super.algorithmName = Resources.GRADIENT_BOOSTING_CLASSIFIER;
    }
}




