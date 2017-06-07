package swissknife.modal.classifiers.naivebayes.modal;

import swissknife.modal.classifiers.naivebayes.RunNBPython;

/**
 * Created by ramyeid on 6/2/17.
 */
public class NBPredictVsActual extends NaiveBayes {


    public NBPredictVsActual(){
    }
    public NBPredictVsActual(String inputFile, String keyToPredict, int actionTime) {
        super(inputFile, keyToPredict, actionTime);
    }

    @Override
    public void action() {
        new RunNBPython(inputFile, keyToPredict, 1, actionTime).run();
    }
}