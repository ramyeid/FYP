package swissknife.modal.classifier.naivebayes;

import swissknife.modal.classifier.RunClassifierPython;

/**
 * Created by ramyeid on 6/2/17.
 */
public class NBPredictVsActual extends NaiveBayes {


    public NBPredictVsActual(){
        super();
        super.action = 1;
    }
    public NBPredictVsActual(String inputFile, String keyToPredict, int actionTime) {
        super(inputFile, keyToPredict, actionTime);
        super.action = 1;
    }

    @Override
    public void action() {
        new RunClassifierPython(inputFile, keyToPredict, action, actionTime,"NB",actionKeys).run();
    }
}