package swissknife.modal.classifier.extratreeclassifier;

import swissknife.modal.classifier.RunClassifierPython;

/**
 * Created by ramyeid on 6/10/17.
 */
public class ETPredictVsActual  extends ExtraTreeClassifier{

    public ETPredictVsActual(){
        super();
        this.action = 1;
    }
    public ETPredictVsActual(String inputFile, String keyToPredict, int actionTime) {
        super(inputFile, keyToPredict, actionTime);
        this.action = 1;
    }

    @Override
    public void action() {
        new RunClassifierPython(inputFile, keyToPredict, action, actionTime,"ET",actionKeys).run();
    }
}

