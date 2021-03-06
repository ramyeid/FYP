package swissknife.modal.classifier.extratreeclassifier;

import swissknife.Resources;
import swissknife.modal.classifier.Classifier;

/**
 * Created by ramyeid on 6/10/17.
 */
public abstract class ExtraTreeClassifier extends Classifier {

    public ExtraTreeClassifier(String inputFile,String keyToPredict,int actionTime){
        super(inputFile,keyToPredict,actionTime);
        super.fileToReadAccuracy = Resources.ET_PREDICTED_ACTUAL_ONLY_FILE;
        super.fileToReadPredicted = Resources.ET_PREDICTED_RESULT_FILE;
        super.fileAllResultsPvsA = Resources.ET_PREDICTED_ACTUAL_RESULT_FILE;

        super.algorithmName = Resources.EXTRA_TREE_CLASSIFIER;
    }
    public ExtraTreeClassifier(){
        super.fileToReadAccuracy = Resources.ET_PREDICTED_ACTUAL_ONLY_FILE;
        super.fileToReadPredicted = Resources.ET_PREDICTED_RESULT_FILE;
        super.fileAllResultsPvsA = Resources.ET_PREDICTED_ACTUAL_RESULT_FILE;
        super.algorithmName = Resources.EXTRA_TREE_CLASSIFIER;


    }
}
