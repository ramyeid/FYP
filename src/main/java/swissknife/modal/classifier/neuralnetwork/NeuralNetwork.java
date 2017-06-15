package swissknife.modal.classifier.neuralnetwork;

import swissknife.Resources;
import swissknife.modal.classifier.Classifier;

/**
 * Created by ramyeid on 6/15/17.
 */
public abstract class NeuralNetwork extends Classifier {

    protected String hiddenDepth;

    public NeuralNetwork(String inputFile,String keyToPredict,int actionTime){
        super(inputFile,keyToPredict,actionTime);
        super.fileToReadAccuracy = Resources.NN_PREDICTED_ACTUAL_ONLY_FILE;
        super.fileToReadPredicted = Resources.NN_PREDICTED_RESULT_FILE;
        super.fileAllResultsPvsA = Resources.NN_PREDICTED_ACTUAL_RESULT_FILE;
        super.algorithmName = Resources.NEURAL_NETWORK;
    }
    public NeuralNetwork(){
        super();
        super.fileToReadAccuracy = Resources.NN_PREDICTED_ACTUAL_ONLY_FILE;
        super.fileToReadPredicted = Resources.NN_PREDICTED_RESULT_FILE;
        super.fileAllResultsPvsA = Resources.NN_PREDICTED_ACTUAL_RESULT_FILE;
        super.algorithmName = Resources.NEURAL_NETWORK;
    }

    public void setHiddenDepth(String hiddenDepth) {
        this.hiddenDepth = hiddenDepth;
    }
}

