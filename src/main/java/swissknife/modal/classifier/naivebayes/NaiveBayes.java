package swissknife.modal.classifier.naivebayes;

import swissknife.modal.classifier.Classifier;

/**
 * Created by ramyeid on 6/2/17.
 */
public abstract class NaiveBayes extends Classifier {

    public NaiveBayes(String inputFile,String keyToPredict,int actionTime){
        super(inputFile,keyToPredict,actionTime);
    }
    public NaiveBayes(){}

}
