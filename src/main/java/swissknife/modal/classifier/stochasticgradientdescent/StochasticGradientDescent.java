package swissknife.modal.classifier.stochasticgradientdescent;

import swissknife.Resources;
import swissknife.modal.classifier.Classifier;

/**
 * Created by ramyeid on 6/10/17.
 */
public abstract class StochasticGradientDescent extends Classifier{

    public StochasticGradientDescent(String inputFile,String keyToPredict,int actionTime){
        super(inputFile,keyToPredict,actionTime);
        super.fileToReadAccuracy = Resources.SGD_PREDICTED_ACTUAL_ONLY_FILE;
        super.algorithmName = Resources.STOCHASTIC_GRADIENT_DESCENT;

    }
    public StochasticGradientDescent(){
        super.fileToReadAccuracy = Resources.SGD_PREDICTED_ACTUAL_ONLY_FILE;
        super.algorithmName = Resources.STOCHASTIC_GRADIENT_DESCENT;
    }

}
