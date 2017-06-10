package swissknife.modal.classifier.decisiontree;

import swissknife.modal.classifier.RunClassifierPython;

/**
 * Created by ramyeid on 6/10/17.
 */
public class DTPredictVsActual extends DecisionTree {

    public DTPredictVsActual(){
        super();
        this.action = 1;
    }
    public DTPredictVsActual(String inputFile, String keyToPredict, int actionTime) {
        super(inputFile, keyToPredict, actionTime);
        this.action = 1;
    }

    @Override
    public void action() {
        new RunClassifierPython(inputFile, keyToPredict, action, actionTime,"DT",actionKeys).run();
    }
}