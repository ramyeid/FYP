package swissknife.modal.classifier.decisiontree;

import swissknife.Resources;
import swissknife.modal.classifier.Classifier;

/**
 * Created by ramyeid on 6/10/17.
 */
public abstract class DecisionTree extends Classifier {

    public DecisionTree(String inputFile,String keyToPredict,int actionTime){
        super(inputFile,keyToPredict,actionTime);
        super.fileToReadAccuracy = Resources.DT_PREDICTED_ACTUAL_ONLY_FILE;
        super.fileToReadPredicted = Resources.DT_PREDICTED_RESULT_FILE;
        super.fileAllResultsPvsA = Resources.DT_PREDICTED_ACTUAL_RESULT_FILE;
        super.algorithmName = Resources.DECISION_TREE;
    }
    public DecisionTree(){
        super.fileToReadAccuracy = Resources.DT_PREDICTED_ACTUAL_ONLY_FILE;
        super.fileToReadPredicted = Resources.DT_PREDICTED_RESULT_FILE;
        super.fileAllResultsPvsA = Resources.DT_PREDICTED_ACTUAL_RESULT_FILE;
        super.algorithmName = Resources.DECISION_TREE;

    }

}