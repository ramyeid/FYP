package swissknife.modal.classifier.linearsvm;

import swissknife.Resources;
import swissknife.modal.classifier.Classifier;

/**
 * Created by ramyeid on 6/10/17.
 */
public abstract class LinearSVC extends Classifier {

    public LinearSVC(){
        super.fileToReadAccuracy = Resources.LSVC_PREDICTED_ACTUAL_ONLY_FILE;
        super.algorithmName = "Linear SVC";
    }

    public LinearSVC(String inputFile,String keyToPredict,int actionTime){
        super(inputFile,keyToPredict,actionTime);
        super.fileToReadAccuracy = Resources.LSVC_PREDICTED_ACTUAL_ONLY_FILE;
        super.algorithmName = "Linear SVC";
    }
}