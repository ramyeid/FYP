package swissknife.modal.classifier.svm;

import swissknife.modal.classifier.RunClassifierPython;

/**
 * Created by ramyeid on 6/10/17.
 */
public class SVMPredictVsActual extends SupportVectorMachine {

    public SVMPredictVsActual(){
        super();
        super.action  = 1;
    }
    public SVMPredictVsActual(String inputFile, String keyToPredict, int actionTime) {
        super(inputFile, keyToPredict, actionTime);
        super.action = 1;

    }

    @Override
    public void action() {
        new RunClassifierPython(inputFile, keyToPredict, action, actionTime,"SVM",actionKeys).run();
    }
}