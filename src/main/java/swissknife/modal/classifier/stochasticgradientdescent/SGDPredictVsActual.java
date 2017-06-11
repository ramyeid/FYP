package swissknife.modal.classifier.stochasticgradientdescent;

import swissknife.modal.classifier.RunClassifierPython;

/**
 * Created by ramyeid on 6/10/17.
 */
public class SGDPredictVsActual extends StochasticGradientDescent{
    public SGDPredictVsActual() {
        super();
        this.action = 1;
    }

    public SGDPredictVsActual(String inputFile, String keyToPredict, int actionTime) {
        super(inputFile, keyToPredict, actionTime);
        this.action = 1;
    }


    @Override
    public void action() {
        new RunClassifierPython(inputFile, keyToPredict, action, actionTime, "SGD",actionKeys).run();


    }
}

