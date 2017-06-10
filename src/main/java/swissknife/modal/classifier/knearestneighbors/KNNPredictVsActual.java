package swissknife.modal.classifier.knearestneighbors;

import swissknife.modal.classifier.RunClassifierPython;

/**
 * Created by ramyeid on 6/10/17.
 */
public class KNNPredictVsActual extends KNearestNeighbors {

    public KNNPredictVsActual(){
        super();
        this.action = 1;
    }
    public KNNPredictVsActual(String inputFile, String keyToPredict, int actionTime) {
        super(inputFile, keyToPredict, actionTime);
        this.action = 1;
    }

    @Override
    public void action() {
        new RunClassifierPython(inputFile, keyToPredict, action, actionTime,"KNN").run();
    }
}
