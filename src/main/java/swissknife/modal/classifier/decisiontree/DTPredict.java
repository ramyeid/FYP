package swissknife.modal.classifier.decisiontree;

import swissknife.modal.classifier.RunClassifierPython;

/**
 * Created by ramyeid on 6/10/17.
 */
public class DTPredict extends DecisionTree{

    public DTPredict(){
        super();
        this.action = 2;
    }
    public DTPredict(String inputFile, String keyToPredict, int actionTime) {
        super(inputFile, keyToPredict, actionTime);
        this.action = 2;
    }

    @Override
    public void action() {
        new RunClassifierPython(inputFile, keyToPredict, action, actionTime,"DT").run();
    }
}