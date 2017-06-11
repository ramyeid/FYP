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
        super.algorithmName = "Extra Tree Classifier";
    }
    public ExtraTreeClassifier(){
        super.fileToReadAccuracy = Resources.ET_PREDICTED_ACTUAL_ONLY_FILE;
        super.algorithmName = "Extra Tree Classifier";

    }
}
