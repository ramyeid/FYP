package swissknife.modal.classifier.neuralnetwork;

import swissknife.modal.classifier.RunClassifierPython;

/**
 * Created by ramyeid on 6/15/17.
 */
public class NNPredict  extends NeuralNetwork{

    public NNPredict() {
        super();
        super.action = 2;
    }

    public NNPredict(String inputFile, String keyToPredict, int actionTime){
        super(inputFile,keyToPredict,actionTime);
        super.action = 2;
    }

    @Override
    public void action() {
        new RunClassifierPython(inputFile,keyToPredict,action,actionTime,"NN",actionKeys,hiddenDepth).run();
    }

}
