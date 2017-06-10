package swissknife.modal.classifier.svm;

import swissknife.Resources;
import swissknife.modal.classifier.Classifier;

/**
 * Created by ramyeid on 6/10/17.
 */
public abstract class SupportVectorMachine extends Classifier{
    public SupportVectorMachine(String inputFile,String keyToPredict,int actionTime){
        super(inputFile,keyToPredict,actionTime);
        super.fileToReadAccuracy = Resources.SVM_PREDICTED_ACTUAL_ONLY_FILE;
        super.algorithmName = "Support Vector Machine";

    }
    public SupportVectorMachine(){
        super.fileToReadAccuracy = Resources.SVM_PREDICTED_ACTUAL_ONLY_FILE;
        super.algorithmName = "Support Vector Machine";
    }
}

