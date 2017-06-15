package swissknife.modal.classifier.neuralnetwork;

import swissknife.modal.classifier.RunClassifierPython;

/**
 * Created by ramyeid on 6/15/17.
 */
public class NNPredictVsActual extends NeuralNetwork {
    public NNPredictVsActual() {
        super();
        super.action=1;
    }

    public NNPredictVsActual(String inputFile, String keyToPredict, int actionTime){
        super(inputFile,keyToPredict,actionTime);
        super.action =1;
    }


    @Override
    public void action() {
        new RunClassifierPython(inputFile,keyToPredict,action,actionTime,"NN",actionKeys,hiddenDepth).run();
    }

}
