package swissknife.modal.classifier.stochasticgradientdescent;

import swissknife.modal.classifier.RunClassifierPython;

/**
 * Created by ramyeid on 6/10/17.
 */
public class SGDPredict extends StochasticGradientDescent {
    public SGDPredict() {
        super();
        this.action = 2;
    }

    public SGDPredict(String inputFile,String keyToPredict,int actionTime){
        super(inputFile,keyToPredict,actionTime);
        this.action = 2;
    }

    @Override
    public void action() {
        new RunClassifierPython(inputFile,keyToPredict,action,actionTime,"SGD",actionKeys).run();
    }

}