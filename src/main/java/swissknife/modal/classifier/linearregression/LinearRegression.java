package swissknife.modal.classifier.linearregression;

import swissknife.Resources;
import swissknife.modal.classifier.Classifier;

/**
 * Created by ramyeid on 6/15/17.
 */
public abstract class LinearRegression extends Classifier {

    public LinearRegression(){
        super();
        super.fileToReadAccuracy = Resources.LR_PREDICTED_ACTUAL_ONLY_FILE;
        super.fileToReadPredicted = Resources.LR_PREDICTED_RESULT_FILE;
        super.fileAllResultsPvsA = Resources.LR_PREDICTED_ACTUAL_RESULT_FILE;
        super.algorithmName = Resources.LINEAR_REGRESSION;
    }

    public LinearRegression(String inputFile,String keyToPredict,int actionTime){
        super(inputFile,keyToPredict,actionTime);
        super.fileToReadAccuracy = Resources.LR_PREDICTED_ACTUAL_ONLY_FILE;
        super.fileToReadPredicted = Resources.LR_PREDICTED_RESULT_FILE;
        super.fileAllResultsPvsA = Resources.LR_PREDICTED_ACTUAL_RESULT_FILE;
        super.algorithmName = Resources.LINEAR_REGRESSION;
    }
}