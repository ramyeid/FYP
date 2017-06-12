package swissknife.modal.classifier.gradientboosting;

import swissknife.Resources;
import swissknife.modal.classifier.Classifier;

/**
 * Created by ramyeid on 6/10/17.
 */
public abstract class GradientBoosting extends Classifier {

    public GradientBoosting(String inputFile, String keyToPredict, int actionTime) {
        super(inputFile, keyToPredict, actionTime);
        super.algorithmName = Resources.GRADIENT_BOOSTING_CLASSIFIER;
        super.fileToReadAccuracy = Resources.GB_PREDICTED_ACTUAL_ONLY_FILE;
        super.fileToReadPredicted = Resources.GB_PREDICTED_RESULT_FILE;
        super.fileAllResultsPvsA = Resources.GB_PREDICTED_ACTUAL_RESULT_FILE;

    }

    public GradientBoosting() {

        super.fileToReadAccuracy = Resources.GB_PREDICTED_ACTUAL_ONLY_FILE;
        super.fileToReadPredicted = Resources.GB_PREDICTED_RESULT_FILE;
        super.fileAllResultsPvsA = Resources.GB_PREDICTED_ACTUAL_RESULT_FILE;
        super.algorithmName = Resources.GRADIENT_BOOSTING_CLASSIFIER;
    }
}




