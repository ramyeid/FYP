package swissknife.modal.classifier.bernoullinaivebayes;

import swissknife.modal.classifier.RunClassifierPython;

/**
 * Created by ramyeid on 6/11/17.
 */
public class BNBPredictVsActual extends BernoulliNaiveBayes {

    public BNBPredictVsActual(){
        super();
        this.action = 1;
    }
    public BNBPredictVsActual(String inputFile, String keyToPredict, int actionTime) {
        super(inputFile, keyToPredict, actionTime);
        this.action = 1;
    }

    @Override
    public void action() {
        new RunClassifierPython(inputFile, keyToPredict, action, actionTime,"BNB",actionKeys).run();
    }
}