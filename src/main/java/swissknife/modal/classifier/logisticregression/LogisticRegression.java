package swissknife.modal.classifier.logisticregression;

import swissknife.Resources;
import swissknife.modal.classifier.Classifier;

/**
 * Created by ramyeid on 6/10/17.
 */
public abstract class LogisticRegression extends Classifier {
    public LogisticRegression(String inputFile,String keyToPredict,int actionTime){
        super(inputFile,keyToPredict,actionTime);
        super.fileToReadAccuracy = Resources.LOGR_PREDICTED_ACTUAL_ONLY_FILE;
        super.fileToReadPredicted = Resources.LOGR_PREDICTED_RESULT_FILE;
        super.fileAllResultsPvsA = Resources.LOGR_PREDICTED_ACTUAL_RESULT_FILE;
        super.algorithmName = Resources.LOGISTIC_REGRESSION;
    }
    public LogisticRegression(){
        super.fileToReadAccuracy = Resources.LOGR_PREDICTED_ACTUAL_ONLY_FILE;
        super.fileToReadPredicted = Resources.LOGR_PREDICTED_RESULT_FILE;
        super.fileAllResultsPvsA = Resources.LOGR_PREDICTED_ACTUAL_RESULT_FILE;
        super.algorithmName = Resources.LOGISTIC_REGRESSION;
    }
}


