package swissknife.modal.classifier.svm;

import swissknife.modal.classifier.Classifier;

/**
 * Created by ramyeid on 6/10/17.
 */
public abstract class SupportVectorMachine extends Classifier{
    public SupportVectorMachine(String inputFile,String keyToPredict,int actionTime){
        super(inputFile,keyToPredict,actionTime);
    }
    public SupportVectorMachine(){}
}
