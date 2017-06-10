package swissknife.modal.classifier.decisiontree;

import swissknife.modal.classifier.Classifier;

/**
 * Created by ramyeid on 6/10/17.
 */
public abstract class DecisionTree extends Classifier {

    public DecisionTree(String inputFile,String keyToPredict,int actionTime){
        super(inputFile,keyToPredict,actionTime);
    }
    public DecisionTree(){}

}
