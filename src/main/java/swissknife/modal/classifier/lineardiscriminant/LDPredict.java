package swissknife.modal.classifier.lineardiscriminant;

import swissknife.modal.classifier.RunClassifierPython;

/**
 * Created by ramyeid on 6/10/17.
 */
public class LDPredict extends LinearDiscriminant{

    public LDPredict() {
        super();
        super.action = 2;
    }

    public LDPredict(String inputFile, String keyToPredict, int actionTime){
        super(inputFile,keyToPredict,actionTime);
        super.action = 2;
    }

    @Override
    public void action() {
        new RunClassifierPython(inputFile,keyToPredict,action,actionTime,"LDA",actionKeys).run();
    }

}

