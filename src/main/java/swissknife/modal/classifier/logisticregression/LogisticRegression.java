package swissknife.modal.classifier.logisticregression;

import swissknife.modal.classifier.Classifier;

/**
 * Created by ramyeid on 6/10/17.
 */
public abstract class LogisticRegression extends Classifier {
    public LogisticRegression(String inputFile,String keyToPredict,int actionTime){
        super(inputFile,keyToPredict,actionTime);
    }
    public LogisticRegression(){}
}
