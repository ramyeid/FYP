package swissknife.modal.classifier.bernoullinaivebayes;

import swissknife.modal.classifier.RunClassifierPython;

/**
 * Created by ramyeid on 6/11/17.
 */
public class BNBPredict extends BernoulliNaiveBayes {
    public BNBPredict(){
        super();
        this.action = 2;
    }
    public BNBPredict(String inputFile, String keyToPredict, int actionTime) {
        super(inputFile, keyToPredict, actionTime);
        this.action = 2;
    }

    @Override
    public void action() {
        new RunClassifierPython(inputFile, keyToPredict, action, actionTime,"BNB",actionKeys).run();
    }
}

