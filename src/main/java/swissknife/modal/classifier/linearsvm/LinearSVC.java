package swissknife.modal.classifier.linearsvm;

import swissknife.Resources;
import swissknife.modal.classifier.Classifier;

/**
 * Created by ramyeid on 6/10/17.
 */
public abstract class LinearSVC extends Classifier {

    public LinearSVC(){
        super();
        super.fileToReadAccuracy = Resources.LSVC_PREDICTED_ACTUAL_ONLY_FILE;
        super.fileToReadPredicted = Resources.LSVC_PREDICTED_RESULT_FILE;
        super.fileAllResultsPvsA = Resources.LSVC_PREDICTED_ACTUAL_RESULT_FILE;
        super.algorithmName = Resources.LINEAR_SVC;
    }

    public LinearSVC(String inputFile,String keyToPredict,int actionTime){
        super(inputFile,keyToPredict,actionTime);
        super.fileToReadAccuracy = Resources.LSVC_PREDICTED_ACTUAL_ONLY_FILE;
        super.fileToReadPredicted = Resources.LSVC_PREDICTED_RESULT_FILE;
        super.fileAllResultsPvsA = Resources.LSVC_PREDICTED_ACTUAL_RESULT_FILE;
        super.algorithmName = Resources.LINEAR_SVC;
    }
}