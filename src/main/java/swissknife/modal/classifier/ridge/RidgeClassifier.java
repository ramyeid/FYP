package swissknife.modal.classifier.ridge;

import swissknife.Resources;
import swissknife.modal.classifier.Classifier;

/**
 * Created by ramyeid on 6/10/17.
 */
public abstract class RidgeClassifier extends Classifier{
    public RidgeClassifier(String inputFile,String keyToPredict,int actionTime){
        super(inputFile,keyToPredict,actionTime);
        super.fileToReadAccuracy = Resources.RC_PREDICTED_ACTUAL_ONLY_FILE;
        super.algorithmName = Resources.RIDGE_CLASSIFIER;

    }
    public RidgeClassifier(){
        super.fileToReadAccuracy = Resources.RC_PREDICTED_ACTUAL_ONLY_FILE;
        super.algorithmName = Resources.RIDGE_CLASSIFIER;
    }
}
