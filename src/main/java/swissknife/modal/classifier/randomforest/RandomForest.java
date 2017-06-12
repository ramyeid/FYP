package swissknife.modal.classifier.randomforest;

import swissknife.Resources;
import swissknife.modal.classifier.Classifier;

/**
 * Created by ramyeid on 6/10/17.
 */
public abstract class RandomForest extends Classifier{
    public RandomForest(String inputFile,String keyToPredict,int actionTime){
        super(inputFile,keyToPredict,actionTime);
        super.fileToReadAccuracy = Resources.RF_PREDICTED_ACTUAL_ONLY_FILE;
        super.fileToReadPredicted = Resources.RF_PREDICTED_RESULT_FILE;
        super.fileAllResultsPvsA = Resources.RF_PREDICTED_ACTUAL_RESULT_FILE;
        super.algorithmName = Resources.RANDOM_FOREST;

    }
    public RandomForest(){
        super.fileToReadAccuracy = Resources.RF_PREDICTED_ACTUAL_ONLY_FILE;
        super.fileToReadPredicted = Resources.RF_PREDICTED_RESULT_FILE;
        super.fileAllResultsPvsA = Resources.RF_PREDICTED_ACTUAL_RESULT_FILE;
        super.algorithmName = Resources.RANDOM_FOREST;
    }
}

