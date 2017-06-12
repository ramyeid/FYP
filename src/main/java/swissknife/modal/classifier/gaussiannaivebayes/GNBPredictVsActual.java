package swissknife.modal.classifier.gaussiannaivebayes;

import swissknife.modal.classifier.RunClassifierPython;

/**
 * Created by ramyeid on 6/2/17.
 */
public class GNBPredictVsActual extends GaussianNaiveBayes {


    public GNBPredictVsActual(){
        super();
        super.action = 1;
    }
    public GNBPredictVsActual(String inputFile, String keyToPredict, int actionTime) {
        super(inputFile, keyToPredict, actionTime);
        super.action = 1;
    }

    @Override
    public void action() {
        new RunClassifierPython(inputFile, keyToPredict, action, actionTime,"GNB",actionKeys).run();
    }
}